package com.calidad.nominasoft.Dominio.Contratos;

import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;

public interface IPeriodoContrato {
  public PeriodoDePago buscarActivo();

  public PeriodoDePago buscarUltimo();
}