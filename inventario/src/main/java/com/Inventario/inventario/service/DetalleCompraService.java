package com.Inventario.inventario.service;

import com.Inventario.inventario.model.DetalleCompra;
import java.util.List;
import java.util.Optional;

public interface DetalleCompraService {
    List<DetalleCompra> findAll();
    Optional<DetalleCompra> findById(Long id);
    DetalleCompra save(DetalleCompra detalleCompra);
    void deleteById(Long id);
}
