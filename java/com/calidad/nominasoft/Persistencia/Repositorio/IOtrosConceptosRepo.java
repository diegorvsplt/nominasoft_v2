package com.calidad.nominasoft.Persistencia.Repositorio;

import com.calidad.nominasoft.Dominio.Entidades.OtrosConceptos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOtrosConceptosRepo extends JpaRepository<OtrosConceptos, Long> {
    public Optional<OtrosConceptos> findOtrosConceptosByContrato_Id(Long id);
    //public Optional<OtrosConceptos> findOtrosConceptosByPeriodo_Id(Long id);
}
