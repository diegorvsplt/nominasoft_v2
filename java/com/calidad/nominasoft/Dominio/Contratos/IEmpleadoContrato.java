package com.calidad.nominasoft.Dominio.Contratos;

import com.calidad.nominasoft.Dominio.Entidades.Empleado;

public interface IEmpleadoContrato {
    public Empleado buscarPorDni(Long dni);
}
