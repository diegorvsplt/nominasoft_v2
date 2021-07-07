package com.calidad.nominasoft.Dominio.Contratos;

import com.calidad.nominasoft.Dominio.Entidades.Contrato;

import java.util.List;

public interface IContratoContrato {

    public Contrato buscarUltimoContrato_Empleado(Long dni);

    public List<Contrato> buscarContratosActivos();

    public List<Contrato> buscarTodosPorEmpleado_Id(Long id);
}
