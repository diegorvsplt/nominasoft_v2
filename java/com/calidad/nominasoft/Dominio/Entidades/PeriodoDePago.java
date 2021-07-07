package com.calidad.nominasoft.Dominio.Entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "periodos")
public class PeriodoDePago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean estado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;

    public PeriodoDePago() {
    }

    @PrePersist
    public void estado() {
        estado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getSemanasDelPeriodo() {
        return calcularSemanasDelPeriodo();
    }

    // REGLAS DE NEGOCIO

    public boolean esFechaActualMayor() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaFin.isAfter(fechaActual) || (fechaFin.until(fechaActual, ChronoUnit.DAYS) == 0)) {
            return true;
        }
        return false;
    }

    private int calcularSemanasDelPeriodo() {
        int semanas = (int) fechaInicio.until(fechaFin, ChronoUnit.WEEKS);
        return semanas;
    }
}
