package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import com.calidad.nominasoft.Dominio.Contratos.IOtrosConceptosContrato;
import com.calidad.nominasoft.Dominio.Entidades.OtrosConceptos;
import com.calidad.nominasoft.Persistencia.Repositorio.IOtrosConceptosRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class OtrosConceptosDAO extends CRUDImple<OtrosConceptos, Long> implements IOtrosConceptosContrato {

    @Autowired
    private IOtrosConceptosRepo conceptosRepo;

    @Override
    public JpaRepository<OtrosConceptos, Long> getDao() {
        return conceptosRepo;
    }

    @Override
    public OtrosConceptos buscarPorContrato_Id(Long id) {
        return conceptosRepo.findOtrosConceptosByContrato_Id(id).orElse(null);
    }
}
