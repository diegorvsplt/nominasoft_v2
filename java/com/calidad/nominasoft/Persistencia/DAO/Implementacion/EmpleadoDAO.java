package com.calidad.nominasoft.Persistencia.DAO.Implementacion;

import com.calidad.nominasoft.Dominio.Contratos.IEmpleadoContrato;
import com.calidad.nominasoft.Dominio.Entidades.Empleado;
import com.calidad.nominasoft.Persistencia.Repositorio.IEmpleadoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class EmpleadoDAO extends CRUDImple<Empleado, Long> implements IEmpleadoContrato {

    @Autowired
    private IEmpleadoRepo empleadoRepo;

    @Override
    public JpaRepository<Empleado, Long> getDao() {
        return empleadoRepo;
    }

    @Override
    public Empleado buscarPorDni(Long dni) {
        return empleadoRepo.findByDni(dni).orElse(null);

    }

    public Empleado buscarUno(Long id) {
        return empleadoRepo.findById(id).orElse(null);
    }
}
