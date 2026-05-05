package com.example.parcial2.controllers;

import com.example.parcial2.entities.Consulta;
import com.example.parcial2.services.ConsultaService; // O el nombre de tu service
import com.example.parcial2.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    // Listado principal
    @GetMapping
    public String listar(Model model, Authentication authentication) {
        if (tieneRol(authentication, "PACIENTE")) {
            String nombrePaciente = obtenerNombrePaciente(authentication.getName());
            model.addAttribute("listaConsultas", consultaService.listarPorPaciente(nombrePaciente));
        } else {
            model.addAttribute("listaConsultas", consultaService.listarTodas());
        }
        return "lista";
    }

    // Mostrar formulario para nueva consulta (ADMIN)
    @GetMapping("/nuevo")
    public String formularioNueva(Model model) {
        model.addAttribute("consulta", new Consulta());
        model.addAttribute("listaMedicos", medicoService.listarTodos());
        return "formulario";
    }

    // Mostrar formulario para editar (ADMIN y PACIENTE)
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, Authentication authentication) {
        Consulta consulta = obtenerConsultaAutorizada(id, authentication);
        model.addAttribute("consulta", consulta);
        model.addAttribute("listaMedicos", medicoService.listarTodos());
        return "formulario";
    }

    // Guardar cambios (Crea o Actualiza)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Consulta consulta, Authentication authentication) {
        if (tieneRol(authentication, "PACIENTE")) {
            Consulta consultaOriginal = obtenerConsultaAutorizada(consulta.getId(), authentication);
            consultaOriginal.setHoraInicio(consulta.getHoraInicio());
            consultaOriginal.setHoraFin(consulta.getHoraFin());
            consultaService.guardar(consultaOriginal);
        } else {
            consultaService.guardar(consulta);
        }
        return "redirect:/consultas";
    }

    // Eliminar consulta (ADMIN)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        consultaService.eliminar(id);
        return "redirect:/consultas";
    }

    private Consulta obtenerConsultaAutorizada(Long id, Authentication authentication) {
        if (tieneRol(authentication, "PACIENTE")) {
            String nombrePaciente = obtenerNombrePaciente(authentication.getName());
            return consultaService.obtenerPorIdYPaciente(id, nombrePaciente)
                    .orElseThrow(() -> new AccessDeniedException("No puede editar consultas de otros pacientes"));
        }

        Consulta consulta = consultaService.obtenerPorId(id);
        if (consulta == null) {
            throw new AccessDeniedException("Consulta no encontrada");
        }
        return consulta;
    }

    private boolean tieneRol(Authentication authentication, String rol) {
        List<String> roles = authentication.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList();
        return roles.contains("ROLE_" + rol);
    }

    private String obtenerNombrePaciente(String username) {
        return switch (username) {
            case "PAC-101" -> "Diego Perez";
            case "PAC-102" -> "Marta Lopez";
            case "PAC-103" -> "Juan Castro";
            default -> throw new AccessDeniedException("Paciente no reconocido");
        };
    }
}
