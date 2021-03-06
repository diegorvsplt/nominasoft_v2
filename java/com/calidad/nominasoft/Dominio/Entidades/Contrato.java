package com.calidad.nominasoft.Dominio.Entidades;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

import java.time.LocalDate;

@Entity
@Table(name = "contratos")
public class Contrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private Boolean asignacionFamiliar;
    private String cargo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaFin;
    private int horasContratadasPorSemana;
    private int valorHora;
    private boolean anulado = false;

    @ManyToOne
    @JoinColumn(name = "afp_id", referencedColumnName = "id")
    private Afp afp;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Empleado empleado;

    public Contrato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public void setAsignacionFamiliar(Boolean asignacionFamiliar) {
        this.asignacionFamiliar = asignacionFamiliar;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public int getHorasContratadasPorSemana() {
        return horasContratadasPorSemana;
    }

    public void setHorasContratadasPorSemana(int horasContratadasPorSemana) {
        this.horasContratadasPorSemana = horasContratadasPorSemana;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    // REGLAS DE NEGOCIO

    public boolean estaVigente() {
        LocalDate fechaActual = LocalDate.now();

        if ((fechaFin.isAfter(fechaActual) || fechaFin.isEqual(fechaActual)) && !anulado) {
            return true;
        }
        return false;
    }

    public boolean esValidaFechaFin() {
        if (minimo3Meses() && maximo12Meses()) {
            return true;
        }
        return false;
    }

    public boolean esValidaFechaInicio(Contrato contratoAnterior) {
        if (fechaInicio.isAfter(contratoAnterior.getFechaFin())) {
            return true;
        }
        return false;
    }

    public boolean esValidoHorasContratadas() {
        if (horasContratadasPorSemana >= 8 && horasContratadasPorSemana <= 40) {
            if (horasContratadasPorSemana % 4 == 0)
                return true;
        }
        return false;
    }

    public boolean esValidoValorHora() {
        if (valorHora >= 10 && valorHora <= 60) {
            return true;
        }
        return false;
    }

    public float calcularMontoAsignacionFamiliar() {

        if (asignacionFamiliar)
            return horasContratadasPorSemana * valorHora * 0.1f;
        else
            return 0;
    }

    // OTROS METODOS

    private boolean minimo3Meses() {
        LocalDate fechaInicioCopia = fechaInicio.plusMonths(3);

        if (fechaInicioCopia.isBefore(fechaFin) || fechaInicioCopia.isEqual(fechaFin)) {
            return true;
        }
        return false;
    }

    private boolean maximo12Meses() {
        LocalDate fechaInicioCopia = fechaInicio.plusYears(1);

        if (fechaInicioCopia.isAfter(fechaFin) || fechaInicioCopia.isEqual(fechaFin)) {
            return true;
        }
        return false;
    }
    /*
     * private int calcularSemanasDelContrato(){ long milis =
     * fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis(); int dias =
     * (int)Math.floor((double)(milis / 86400000)); int semanas =
     * (int)Math.floor((dias / 7)); return semanas; }
     */
}
