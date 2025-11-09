package com.Inventario.inventario.service;

import com.Inventario.inventario.dto.MovimientoRequest;
import com.Inventario.inventario.model.*;
import com.Inventario.inventario.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private DetalleCompraRepository detalleCompraRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private BodegaRepository bodegaRepository;

    @Override
    @Transactional
    public void registrarEntrada(MovimientoRequest movimientoRequest) {
        Producto producto = productoRepository.findById(movimientoRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Proveedor proveedor = proveedorRepository.findById(movimientoRequest.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        // 1. Create DetalleCompra
        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setProducto(producto);
        detalleCompra.setProveedor(proveedor);
        detalleCompra.setCantidad(movimientoRequest.getCantidad());
        detalleCompra.setNombreProducto(producto.getNombre());
        DetalleCompra savedDetalleCompra = detalleCompraRepository.save(detalleCompra);

        // 2. Create Bodega entry
        Bodega bodega = new Bodega();
        bodega.setNombre(movimientoRequest.getNombreBodega());
        bodega.setProducto(producto);
        bodega.setCantidadProducto(movimientoRequest.getCantidad()); // Represents the moved quantity
        bodega.setDetalleCompra(savedDetalleCompra);
        bodegaRepository.save(bodega);
    }

    @Override
    @Transactional
    public void registrarSalida(MovimientoRequest movimientoRequest) {
        Producto producto = productoRepository.findById(movimientoRequest.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Note: Stock availability is not checked here as per the current data model.
        // This would require calculating the sum of all movements for the product.

        // 1. Create DetalleVenta
        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setProducto(producto);
        detalleVenta.setCantidad(movimientoRequest.getCantidad());
        detalleVenta.setNombreProducto(producto.getNombre());
        DetalleVenta savedDetalleVenta = detalleVentaRepository.save(detalleVenta);

        // 2. Create Bodega entry
        Bodega bodega = new Bodega();
        bodega.setNombre(movimientoRequest.getNombreBodega());
        bodega.setProducto(producto);
        bodega.setCantidadProducto(movimientoRequest.getCantidad()); // Represents the moved quantity
        bodega.setDetalleVenta(savedDetalleVenta);
        bodegaRepository.save(bodega);
    }
}
