package com.Inventario.inventario.service;

import com.Inventario.inventario.model.DetalleVenta;
import java.util.List;
import java.util.Optional;

public interface DetalleVentaService {
    List<DetalleVenta> findAll();
    Optional<DetalleVenta> findById(Long id);
    DetalleVenta save(DetalleVenta detalleVenta);
    void deleteById(Long id);
}
