package com.example.bazar.dto;

import lombok.Getter;
import lombok.Setter;

//DTO para mostrar un Producto sin sus Ventas asociadas
@Getter @Setter
public class ProductoSinVentasDTO {
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    private boolean borrado;

    public ProductoSinVentasDTO() {
    }

    public ProductoSinVentasDTO(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible, boolean borrado) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
        this.borrado = borrado;
    }
    
    
    
}
