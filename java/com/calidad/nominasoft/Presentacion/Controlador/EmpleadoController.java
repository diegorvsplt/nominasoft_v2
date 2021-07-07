package com.calidad.nominasoft.Presentacion.Controlador;

import java.util.List;

import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import com.calidad.nominasoft.Dominio.Entidades.Empleado;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.ContratoDAO;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoDAO empleadoDAO;
    @Autowired
    private ContratoDAO contratoDAO;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaEmpleados", empleadoDAO.buscarTodos());
        model.addAttribute("empleado", new Empleado());
        // model.addAttribute("listaContratos", new ArrayList<Contrato>());
        return "empleado/index";
    }

    @PostMapping("/form")
    public String agregar(Empleado empleado) {
        empleadoDAO.guardar(empleado);
        return "redirect:/empleado/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        empleadoDAO.eliminar(id);
        return "redirect:/empleado/";
    }

    @GetMapping("/buscarUno")
    @ResponseBody
    public Empleado buscarUno(Long id) {
        return empleadoDAO.buscarUno(id);
    }

    @GetMapping("/contratos")
    @ResponseBody
    public List<Contrato> listarContratos(Long id) {
        return contratoDAO.buscarTodosPorEmpleado_Id(id);
    }

    /*
     * @GetMapping("/todos") public ResponseEntity<List<Empleado>>
     * getAllEmpleados(){ List<Empleado> listaEmpleados = empleadoDAO.buscarTodos();
     * return new ResponseEntity<>(listaEmpleados, HttpStatus.OK); }
     * 
     * @PostMapping("/agregar") public ResponseEntity<Empleado>
     * addEmpleado(@RequestBody Empleado empleado){ Empleado nuevoEmpleado =
     * empleadoDAO.guardar(empleado); return new ResponseEntity<>(nuevoEmpleado,
     * HttpStatus.CREATED); }
     */
}
