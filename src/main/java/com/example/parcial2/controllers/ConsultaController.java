package com.example.parcial2.controllers;

import com.example.parcial2.entities.Consulta;
import com.example.parcial2.services.ConsultaService; // O el nombre de tu service
import com.example.parcial2.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    // Listado principal
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaConsultas", consultaService.listarTodas());
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
    public String formularioEditar(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.obtenerPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("listaMedicos", medicoService.listarTodos());
        return "formulario";
    }

    // Guardar cambios (Crea o Actualiza)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Consulta consulta) {
        consultaService.guardar(consulta);
        return "redirect:/consultas";
    }

    // Eliminar consulta (ADMIN)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        consultaService.eliminar(id);
        return "redirect:/consultas";
    }
}