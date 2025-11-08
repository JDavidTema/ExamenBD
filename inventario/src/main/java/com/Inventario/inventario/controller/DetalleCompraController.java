package com.Inventario.inventario.controller;

import com.Inventario.inventario.model.DetalleCompra;
import com.Inventario.inventario.service.DetalleCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-compra")
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @GetMapping
    public List<DetalleCompra> getAllDetallesCompra() {
        return detalleCompraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompra> getDetalleCompraById(@PathVariable Long id) {
        Optional<DetalleCompra> detalleCompra = detalleCompraService.findById(id);
        return detalleCompra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleCompra createDetalleCompra(@RequestBody DetalleCompra detalleCompra) {
        return detalleCompraService.save(detalleCompra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompra> updateDetalleCompra(@PathVariable Long id, @RequestBody DetalleCompra detalleCompraDetails) {
        Optional<DetalleCompra> detalleCompraOptional = detalleCompraService.findById(id);
        if (detalleCompraOptional.isPresent()) {
            DetalleCompra detalleCompra = detalleCompraOptional.get();
            detalleCompra.setCantidad(detalleCompraDetails.getCantidad());
            detalleCompra.setNombreProducto(detalleCompraDetails.getNombreProducto());
            detalleCompra.setProveedor(detalleCompraDetails.getProveedor());
            detalleCompra.setProducto(detalleCompraDetails.getProducto());
            return ResponseEntity.ok(detalleCompraService.save(detalleCompra));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleCompra(@PathVariable Long id) {
        if (detalleCompraService.findById(id).isPresent()) {
            detalleCompraService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
