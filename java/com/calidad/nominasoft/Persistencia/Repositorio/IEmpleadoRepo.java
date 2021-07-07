package com.calidad.nominasoft.Persistencia.Repositorio;

import com.calidad.nominasoft.Dominio.Entidades.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEmpleadoRepo extends JpaRepository<Empleado, Long> {
    public Optional<Empleado> findByDni(Long dni);

}
