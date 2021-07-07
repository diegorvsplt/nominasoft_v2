package com.calidad.nominasoft.Presentacion.Controlador;

import com.calidad.nominasoft.Aplicacion.Servicios.GestionarContratoServicio;
import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import com.calidad.nominasoft.Dominio.Entidades.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/contrato")
public class GestionarContratoController {

    @Autowired
    private GestionarContratoServicio servicio;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("ultimoContrato", new Contrato());
        return "gestionarContrato/index";
    }

    @GetMapping("/empleado")
    public String buscarEmpleado(@RequestParam String dni, Model model) {
        Empleado empleado = servicio.buscarEmpleado(Long.parseLong(dni));
        Contrato ultimoContrato = servicio.obtenerUltimoContrato(Long.parseLong(dni));
        if (ultimoContrato == null)
            ultimoContrato = new Contrato();

        model.addAttribute("empleado", empleado);
        model.addAttribute("ultimoContrato", ultimoContrato);
        model.addAttribute("listaAfp", servicio.buscarTodosAfp());
        return "gestionarContrato/index";
    }

    @PostMapping("/form")
    public String agregar(Contrato contrato) {
        servicio.guardarContrato(contrato);
        return "redirect:/contrato/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id) {
        servicio.anularContrato(id);
        return "redirect:/contrato/";
    }
    /*
     * @PostMapping("/{dni}/agregar") public
     * ResponseEntity<Contrato>addContrato(@RequestBody Contrato
     * contrato, @PathVariable Long dni) { Contrato nuevoContrato; Contrato
     * ultimoContrato = servicio.obtenerUltimoContrato(dni);
     * contrato.setEmpleado(servicio.buscarEmpleado(dni)); try { if
     * (ultimoContrato== null) { nuevoContrato = servicio.guardarContrato(contrato);
     * } else { if(!servicio.estaVigente(ultimoContrato) &&
     * servicio.validarFechaInicio(contrato, ultimoContrato)){ nuevoContrato =
     * servicio.guardarContrato(contrato); } else { return new
     * ResponseEntity<>(contrato, HttpStatus.NOT_ACCEPTABLE); } } } catch (Exception
     * err) { throw err; } return new ResponseEntity<>(nuevoContrato,
     * HttpStatus.CREATED); }
     * 
     * @PutMapping("/editar") public ResponseEntity<Contrato>
     * updateContrato(@RequestBody Contrato contrato) { Contrato nuevoContrato =
     * servicio.editarContrato(contrato); return new ResponseEntity<>(nuevoContrato,
     * HttpStatus.OK); }
     * 
     * @DeleteMapping("/anular/{id}") public ResponseEntity<?>
     * deleteContrato(@PathVariable Long id) { servicio.anularContrato(id); return
     * new ResponseEntity<>(HttpStatus.OK); }
     * 
     * @PutMapping("/agregar_afp/{id_afp}/{id_contrato}") public
     * ResponseEntity<Contrato> agregarAfp(@PathVariable Long id_afp, @PathVariable
     * Long id_contrato) { Contrato contrato = servicio.buscarContrato(id_contrato);
     * contrato.setAfp(servicio.buscarAfp(id_afp)); contrato =
     * servicio.guardarContrato(contrato); return new ResponseEntity<>(contrato,
     * HttpStatus.OK); }
     */
}
