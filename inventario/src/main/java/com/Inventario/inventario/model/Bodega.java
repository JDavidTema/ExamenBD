package com.Inventario.inventario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bodega")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    private Long idBodega;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "cantidad_producto", nullable = false)
    private int cantidadProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_compra")
    private DetalleCompra detalleCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta")
    private DetalleVenta detalleVenta;
}
