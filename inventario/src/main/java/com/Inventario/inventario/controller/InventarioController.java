package com.Inventario.inventario.controller;

import com.Inventario.inventario.dto.MovimientoRequest;
import com.Inventario.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/entrada")
    public ResponseEntity<Void> registrarEntrada(@RequestBody MovimientoRequest movimientoRequest) {
        inventarioService.registrarEntrada(movimientoRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/salida")
    public ResponseEntity<Void> registrarSalida(@RequestBody MovimientoRequest movimientoRequest) {
        inventarioService.registrarSalida(movimientoRequest);
        return ResponseEntity.ok().build();
    }
}
