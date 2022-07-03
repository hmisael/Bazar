package com.example.bazar.dto;


import lombok.Getter;
import lombok.Setter;

//DTO para el PUNTO 6: monto y antidad de ventas un determinado día

@Getter @Setter
public class VentasPorDiaDTO {
    private Double monto;
    private int cantidad;

    public VentasPorDiaDTO() {
    }

    public VentasPorDiaDTO(Double monto, int cantidad) {
        this.monto = monto;
        this.cantidad = cantidad;
    }
    
    
    
}
