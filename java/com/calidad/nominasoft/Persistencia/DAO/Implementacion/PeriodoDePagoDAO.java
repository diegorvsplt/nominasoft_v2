package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import com.calidad.nominasoft.Dominio.Contratos.IPeriodoContrato;
import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;
import com.calidad.nominasoft.Persistencia.Repositorio.IPeriodoDePagoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PeriodoDePagoDAO extends CRUDImple<PeriodoDePago, Long> implements IPeriodoContrato {

  @Autowired
  private IPeriodoDePagoRepo periodoRepo;

  @Override
  public PeriodoDePago buscarActivo() {
    return periodoRepo.findFirstByEstadoTrueOrderByFechaInicioDesc().orElse(null);
  }

  @Override
  public JpaRepository<PeriodoDePago, Long> getDao() {
    return periodoRepo;
  }

  public PeriodoDePago buscarUno(Long id) {
    return periodoRepo.findById(id).orElse(null);
  }

  @Override
  public PeriodoDePago buscarUltimo() {
    return periodoRepo.findFirstByOrderByIdDesc();
  }

}