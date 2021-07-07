package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import java.util.List;

import com.calidad.nominasoft.Dominio.Contratos.IPagoContrato;
import com.calidad.nominasoft.Dominio.Entidades.Pago;
import com.calidad.nominasoft.Persistencia.Repositorio.IPagoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PagoDAO extends CRUDImple<Pago, Long> implements IPagoContrato {

    @Autowired
    private IPagoRepo pagoRepo;

    @Override
    public JpaRepository<Pago, Long> getDao() {
        return pagoRepo;
    }

    @Override
    public List<Pago> buscarPagosPorPeriodo_Id(Long id) {
        return pagoRepo.findAllByPeriodoDePago_Id(id);
    }
}
