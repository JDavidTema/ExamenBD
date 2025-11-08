package com.Inventario.inventario.service;

import com.Inventario.inventario.model.Bodega;
import com.Inventario.inventario.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaServiceImpl implements BodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Override
    public List<Bodega> findAll() {
        return bodegaRepository.findAll();
    }

    @Override
    public Optional<Bodega> findById(Long id) {
        return bodegaRepository.findById(id);
    }

    @Override
    public Bodega save(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    @Override
    public void deleteById(Long id) {
        bodegaRepository.deleteById(id);
    }
}
