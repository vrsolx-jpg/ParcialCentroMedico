package com.example.parcial2.entities;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_paciente", length = 40, nullable = false)
    private String nombrePaciente;

    @Column(name = "motivo_consulta", length = 100, nullable = false)
    private String motivo;

    @Column(name = "numero_consultorio")
    private Integer numeroConsultorio;

    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    // Constructores
    public Consulta() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public Integer getNumeroConsultorio() { return numeroConsultorio; }
    public void setNumeroConsultorio(Integer numeroConsultorio) { this.numeroConsultorio = numeroConsultorio; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}