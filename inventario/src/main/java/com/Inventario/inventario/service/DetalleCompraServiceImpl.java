package com.Inventario.inventario.service;

import com.Inventario.inventario.model.DetalleCompra;
import com.Inventario.inventario.repository.DetalleCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraServiceImpl implements DetalleCompraService {

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Override
    public List<DetalleCompra> findAll() {
        return detalleCompraRepository.findAll();
    }

    @Override
    public Optional<DetalleCompra> findById(Long id) {
        return detalleCompraRepository.findById(id);
    }

    @Override
    public DetalleCompra save(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    @Override
    public void deleteById(Long id) {
        detalleCompraRepository.deleteById(id);
    }
}
