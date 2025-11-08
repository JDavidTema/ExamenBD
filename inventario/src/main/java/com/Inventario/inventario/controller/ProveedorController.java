package com.Inventario.inventario.controller;

import com.Inventario.inventario.model.Proveedor;
import com.Inventario.inventario.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Proveedor> getAllProveedores() {
        return proveedorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedorById(@PathVariable Long id) {
        Optional<Proveedor> proveedor = proveedorService.findById(id);
        return proveedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Proveedor createProveedor(@RequestBody Proveedor proveedor) {
        return proveedorService.save(proveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> updateProveedor(@PathVariable Long id, @RequestBody Proveedor proveedorDetails) {
        Optional<Proveedor> proveedorOptional = proveedorService.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            proveedor.setNombre(proveedorDetails.getNombre());
            return ResponseEntity.ok(proveedorService.save(proveedor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProveedor(@PathVariable Long id) {
        if (proveedorService.findById(id).isPresent()) {
            proveedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
