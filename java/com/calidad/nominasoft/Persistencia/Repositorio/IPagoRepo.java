package com.calidad.nominasoft.Persistencia.Repositorio;

import java.util.List;

import com.calidad.nominasoft.Dominio.Entidades.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepo extends JpaRepository<Pago, Long> {
  public List<Pago> findAllByPeriodoDePago_Id(Long id);
}
