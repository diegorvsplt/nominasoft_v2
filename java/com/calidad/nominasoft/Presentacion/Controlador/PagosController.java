package com.calidad.nominasoft.Presentacion.Controlador;

import java.util.List;

import com.calidad.nominasoft.Aplicacion.Servicios.ProcesarPagosServicio;
import com.calidad.nominasoft.Dominio.Entidades.Contrato;
import com.calidad.nominasoft.Dominio.Entidades.Pago;
import com.calidad.nominasoft.Dominio.Entidades.PeriodoDePago;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pagos")
public class PagosController {

  @Autowired
  private ProcesarPagosServicio servicio;

  PeriodoDePago periodo;
  List<Contrato> contratos;

  @GetMapping("/")
  public String index(Model model) {

    periodo = servicio.buscarPeriodoDePagoActivo();
    contratos = servicio.consultarContratosAProcesar(periodo);

    /*
     * System.out.print("Contratos CONTROLADOR: ");
     * System.out.print(contratos.toString());
     * System.out.print("PERIODO CONTROLADOR: ");
     * System.out.print(periodo.toString());
     */
    model.addAttribute("periodo", periodo);
    model.addAttribute("contratos", contratos);
    return "procesarPago/index";
  }

  @PostMapping("/procesar")
  public String registrarPagos() {
    List<Pago> pagos = servicio.registrarPagos(periodo, contratos);
    servicio.guardarPagos(pagos, periodo);
    return "redirect:/pagos/registrados";
  }

  @GetMapping("/registrados")
  public String pagosRegistrados(Model model) {
    PeriodoDePago periodo = servicio.buscarPeriodo();
    List<Pago> pagos = servicio.buscarPagos(periodo.getId());

    model.addAttribute("listaPagos", pagos);
    return "procesarPago/registrados";
  }

  /*
   * @GetMapping("/periodo_activo") public ResponseEntity<PeriodoDePago>
   * getPeriodoActivo() { PeriodoDePago periodo =
   * servicio.buscarPeriodoDePagoActivo(); return new
   * ResponseEntity<PeriodoDePago>(periodo, HttpStatus.OK); }
   * 
   * @PostMapping("/periodo_activo/registrar_pagos") public
   * ResponseEntity<List<Pago>> registrarPagos(@RequestBody PeriodoDePago periodo)
   * { List<Pago> pagos = servicio.registrarPagos(periodo,
   * servicio.consultarContratosAProcesar(periodo)); servicio.guardarPagos(pagos,
   * periodo); return new ResponseEntity<>(pagos, HttpStatus.OK); }
   */
}
