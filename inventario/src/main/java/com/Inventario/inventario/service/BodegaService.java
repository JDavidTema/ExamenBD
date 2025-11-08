package com.Inventario.inventario.service;

import com.Inventario.inventario.model.Bodega;
import java.util.List;
import java.util.Optional;

public interface BodegaService {
    List<Bodega> findAll();
    Optional<Bodega> findById(Long id);
    Bodega save(Bodega bodega);
    void deleteById(Long id);
}
