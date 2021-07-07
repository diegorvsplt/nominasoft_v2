package com.calidad.nominasoft.Dominio.Contratos;

import java.util.List;

import com.calidad.nominasoft.Dominio.Entidades.Pago;

public interface IPagoContrato {
  public List<Pago> buscarPagosPorPeriodo_Id(Long id);
}
