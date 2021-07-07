package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import com.calidad.nominasoft.Dominio.Contratos.IContratoContrato;
import com.calidad.nominasoft.Dominio.Entidades.Contrato;

import com.calidad.nominasoft.Persistencia.Repositorio.IContratoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratoDAO extends CRUDImple<Contrato, Long> implements IContratoContrato {

    @Autowired
    private IContratoRepo contratoRepo;

    @Override
    public Contrato buscarUltimoContrato_Empleado(Long dni) {
        return contratoRepo.findFirstByEmpleado_DniAndAnuladoFalse(dni).orElse(null);
    }

    @Override
    public List<Contrato> buscarContratosActivos() {
        return contratoRepo.findAllByAnuladoFalse();
    }

    @Override
    public JpaRepository<Contrato, Long> getDao() {
        return contratoRepo;
    }

    @Override
    public List<Contrato> buscarTodosPorEmpleado_Id(Long id) {
        return contratoRepo.findAllByEmpleado_Id(id);
    }

}
