package com.calidad.nominasoft.Persistencia.Repositorio;

import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {
    public Optional<Contrato> findFirstByEmpleado_DniAndAnuladoFalse(Long dni);

    public List<Contrato> findAllByAnuladoFalse();

    public List<Contrato> findAllByEmpleado_Id(Long id);
}
