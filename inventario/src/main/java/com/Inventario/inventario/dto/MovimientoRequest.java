package com.Inventario.inventario.dto;

import lombok.Data;

@Data
public class MovimientoRequest {
    private Long productoId;
    private Long proveedorId; // Only for inbound
    private int cantidad;
    private String nombreBodega;
}
