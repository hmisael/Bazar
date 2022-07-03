package com.example.bazar.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaSinProductosDTO {
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private boolean borrado;

    public VentaSinProductosDTO() {
    }

    public VentaSinProductosDTO(Long codigo_venta, LocalDate fecha_venta, Double total, boolean borrado) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.borrado = borrado;
    }

   
    
    
    
    
}
