package com.calidad.nominasoft.Presentacion.Controlador;


import com.calidad.nominasoft.Dominio.Entidades.Afp;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.AfpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/afp")
public class AfpController {

    @Autowired
    private AfpDAO afpDAO;

    @GetMapping("/todos")
    public ResponseEntity<List<Afp>> getAllAfp(){
        List<Afp> listAfp = afpDAO.buscarTodos();
        return new ResponseEntity<List<Afp>>(listAfp, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Afp> addAfp(@RequestBody Afp afp){
        Afp nuevaAfp = afpDAO.guardar(afp);
        return new ResponseEntity<>(nuevaAfp, HttpStatus.CREATED);
    }
}
