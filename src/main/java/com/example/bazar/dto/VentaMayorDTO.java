package com.example.bazar.dto;

import lombok.Getter;
import lombok.Setter;


//DTO para el PUNTO 7

@Getter @Setter
public class VentaMayorDTO {
    private Long codigo_venta;
    private Double total;
    private int cantidad;
    private String nombre_cliente;
    private String apellido_cliente;

    public VentaMayorDTO() {
    }

    public VentaMayorDTO(Long codigo_venta, Double total, int cantidad, String nombre_cliente, String apellido_cliente) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad = cantidad;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
    }


}