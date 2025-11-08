package com.Inventario.inventario.service;

import com.Inventario.inventario.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
}
