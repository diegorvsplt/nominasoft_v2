package com.calidad.nominasoft.Persistencia.Repositorio;

import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPeriodoDePagoRepo extends JpaRepository<PeriodoDePago, Long> {
    public Optional<PeriodoDePago> findFirstByEstadoTrueOrderByFechaInicioDesc();

    public PeriodoDePago findFirstByOrderByIdDesc();
}
