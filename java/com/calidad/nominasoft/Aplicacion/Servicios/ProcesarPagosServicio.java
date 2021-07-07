package com.calidad.nominasoft.Aplicacion.Servicios;

import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import com.calidad.nominasoft.Dominio.Entidades.OtrosConceptos;
import com.calidad.nominasoft.Dominio.Entidades.Pago;
import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.ContratoDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.OtrosConceptosDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.PagoDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.PeriodoDePagoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcesarPagosServicio {

    @Autowired
    private PeriodoDePagoDAO periodoDao;
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private OtrosConceptosDAO conceptosDAO;
    @Autowired
    private PagoDAO pagoDAO;

    public PeriodoDePago buscarPeriodoDePagoActivo() {
        return periodoDao.buscarActivo();
    }

    public List<Contrato> consultarContratosAProcesar(PeriodoDePago periodo) {
        List<Contrato> contratosAProcesar = new ArrayList<>();
        try {
            if (periodo != null) {
                if (periodo.esFechaActualMayor()) {
                    List<Contrato> contratosActivos = contratoDAO.buscarContratosActivos();
                    for (Contrato contrato : contratosActivos) {
                        if (contrato.getFechaFin().isAfter(periodo.getFechaInicio()))
                            contratosAProcesar.add(contrato);
                    }
                }
            }
            return contratosAProcesar;
        } catch (Exception err) {
            throw err;
        }
    }

    public List<Pago> registrarPagos(PeriodoDePago periodo, List<Contrato> contratosActivos) {
        System.out.print("Contratos: ");
        System.out.print(contratosActivos.toString());
        List<Pago> pagos = new ArrayList<>();
        OtrosConceptos conceptos;
        try {
            for (Contrato contrato : contratosActivos) {
                conceptos = conceptosDAO.buscarPorContrato_Id(contrato.getId());
                pagos.add(new Pago(periodo, contrato, conceptos));
            }
            return pagos;
        } catch (Exception err) {
            throw err;
        }
    }

    public void guardarPagos(List<Pago> listaPagos, PeriodoDePago periodo) {
        System.out.print("Pagos: ");
        System.out.print(listaPagos.toString());
        for (Pago pago : listaPagos) {
            pagoDAO.guardar(pago);
        }
        periodo.setEstado(false);
        periodoDao.guardar(periodo);
    }

    public PeriodoDePago buscarPeriodo() {
        return periodoDao.buscarUltimo();
    }

    public List<Pago> buscarPagos(Long id) {
        return pagoDAO.buscarPagosPorPeriodo_Id(id);
    }
}