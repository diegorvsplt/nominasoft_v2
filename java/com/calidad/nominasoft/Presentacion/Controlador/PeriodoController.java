package com.calidad.nominasoft.Presentacion.Controlador;

import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;
import com.calidad.nominasoft.Persistencia.DAO.Implementacion.PeriodoDePagoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/periodo")
public class PeriodoController {

  @Autowired
  private PeriodoDePagoDAO periodoDao;

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("listaPeriodos", periodoDao.buscarTodos());
    model.addAttribute("periodo", new PeriodoDePago());
    // model.addAttribute("listaContratos", new ArrayList<Contrato>());
    return "periodo/index";
  }

  @PostMapping("/form")
  public String agregar(PeriodoDePago periodo) {
    periodoDao.guardar(periodo);
    return "redirect:/periodo/";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminar(@PathVariable Long id) {
    periodoDao.eliminar(id);
    return "redirect:/periodo/";
  }

  @GetMapping("/buscarUno")
  @ResponseBody
  public PeriodoDePago buscarUno(Long id) {
    return periodoDao.buscarUno(id);
  }

  /*
   * @GetMapping("/todos") public ResponseEntity<List<PeriodoDePago>>
   * getAllPeriodos() { List<PeriodoDePago> periodos = periodoDao.buscarTodos();
   * return new ResponseEntity<List<PeriodoDePago>>(periodos, HttpStatus.OK); }
   * 
   * @PostMapping("/agregar") public ResponseEntity<PeriodoDePago>
   * agregarPeriodoo(@RequestBody PeriodoDePago periodo) { PeriodoDePago
   * nuevoPeriodo = periodoDao.guardar(periodo); return new
   * ResponseEntity<PeriodoDePago>(nuevoPeriodo, HttpStatus.CREATED); }
   */
}
