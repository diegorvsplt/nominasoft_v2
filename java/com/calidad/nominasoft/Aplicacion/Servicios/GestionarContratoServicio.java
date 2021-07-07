package com.calidad.nominasoft.Aplicacion.Servicios;

import com.calidad.nominasoft.Aplicacion.Exceptions.ErrorDeDatosException;
import com.calidad.nominasoft.Dominio.Entidades.Afp;
import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import com.calidad.nominasoft.Dominio.Entidades.Empleado;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.AfpDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.ContratoDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionarContratoServicio {

    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private AfpDAO afpDAO;

    public List<Contrato> buscarTodos() {
        return contratoDAO.buscarTodos();
    }

    public Contrato obtenerUltimoContrato(Long dni) {
        return contratoDAO.buscarUltimoContrato_Empleado(dni);
    }

    public boolean validarFechaInicio(Contrato nuevo, Contrato anterior) {
        return nuevo.esValidaFechaInicio(anterior);
    }

    public boolean estaVigente(Contrato ultimoContrato) {
        return ultimoContrato.estaVigente();
    }

    public Contrato guardarContrato(Contrato contrato) {
        if (contrato.esValidaFechaFin() && contrato.esValidoHorasContratadas() && contrato.esValidoValorHora())
            return contratoDAO.guardar(contrato);
        else
            throw new ErrorDeDatosException("Los datos ingresados no cumplen con los parametros");
    }

    public Contrato editarContrato(Contrato contrato) {
        try {
            return guardarContrato(contrato);
        } catch (Exception err) {
            throw new ErrorDeDatosException("Los datos ingresados no cumplen con los parametros");
        }
    }

    public void anularContrato(Long id) {
        Contrato contrato = contratoDAO.buscarPorId(id);
        contrato.setAnulado(true);
        contratoDAO.guardar(contrato);
    }

    public Empleado buscarEmpleado(Long dni) {
        return empleadoDAO.buscarPorDni(dni);
    }

    public Afp buscarAfp(Long id) {
        return afpDAO.buscarPorId(id);
    }

    public List<Afp> buscarTodosAfp() {
        return afpDAO.buscarTodos();
    }

    public Contrato buscarContrato(Long id) {
        return contratoDAO.buscarPorId(id);
    }
}
