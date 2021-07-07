package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import com.calidad.nominasoft.Dominio.Contratos.IAfpContrato;
import com.calidad.nominasoft.Dominio.Entidades.Afp;
import com.calidad.nominasoft.Persistencia.Repositorio.IAfpRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public class AfpDAO extends CRUDImple<Afp,Long> implements IAfpContrato {

    @Autowired
    private IAfpRepositorio afpRepo;

    @Override
    public JpaRepository<Afp, Long> getDao() {
        return afpRepo;
    }
}
