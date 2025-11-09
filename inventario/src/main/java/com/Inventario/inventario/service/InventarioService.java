package com.Inventario.inventario.service;

import com.Inventario.inventario.dto.MovimientoRequest;

public interface InventarioService {
    void registrarEntrada(MovimientoRequest movimientoRequest);
    void registrarSalida(MovimientoRequest movimientoRequest);
}
