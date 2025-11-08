package com.Inventario.inventario.controller;

import com.Inventario.inventario.model.Bodega;
import com.Inventario.inventario.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public List<Bodega> getAllBodegas() {
        return bodegaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bodega> getBodegaById(@PathVariable Long id) {
        Optional<Bodega> bodega = bodegaService.findById(id);
        return bodega.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bodega createBodega(@RequestBody Bodega bodega) {
        return bodegaService.save(bodega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> updateBodega(@PathVariable Long id, @RequestBody Bodega bodegaDetails) {
        Optional<Bodega> bodegaOptional = bodegaService.findById(id);
        if (bodegaOptional.isPresent()) {
            Bodega bodega = bodegaOptional.get();
            bodega.setNombre(bodegaDetails.getNombre());
            bodega.setCantidadProducto(bodegaDetails.getCantidadProducto());
            bodega.setProducto(bodegaDetails.getProducto());
            bodega.setDetalleCompra(bodegaDetails.getDetalleCompra());
            bodega.setDetalleVenta(bodegaDetails.getDetalleVenta());
            return ResponseEntity.ok(bodegaService.save(bodega));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBodega(@PathVariable Long id) {
        if (bodegaService.findById(id).isPresent()) {
            bodegaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
