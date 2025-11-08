package com.Inventario.inventario.controller;

import com.Inventario.inventario.model.DetalleVenta;
import com.Inventario.inventario.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> getAllDetallesVenta() {
        return detalleVentaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVenta> getDetalleVentaById(@PathVariable Long id) {
        Optional<DetalleVenta> detalleVenta = detalleVentaService.findById(id);
        return detalleVenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleVenta createDetalleVenta(@RequestBody DetalleVenta detalleVenta) {
        return detalleVentaService.save(detalleVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVenta> updateDetalleVenta(@PathVariable Long id, @RequestBody DetalleVenta detalleVentaDetails) {
        Optional<DetalleVenta> detalleVentaOptional = detalleVentaService.findById(id);
        if (detalleVentaOptional.isPresent()) {
            DetalleVenta detalleVenta = detalleVentaOptional.get();
            detalleVenta.setCantidad(detalleVentaDetails.getCantidad());
            detalleVenta.setNombreProducto(detalleVentaDetails.getNombreProducto());
            detalleVenta.setProducto(detalleVentaDetails.getProducto());
            return ResponseEntity.ok(detalleVentaService.save(detalleVenta));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleVenta(@PathVariable Long id) {
        if (detalleVentaService.findById(id).isPresent()) {
            detalleVentaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
