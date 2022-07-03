package com.example.bazar.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private boolean borrado;
    
    @ManyToMany
    @JoinTable (name = "venta_producto",
                    joinColumns = @JoinColumn (name = "codigo_venta", nullable = false),
                    inverseJoinColumns = @JoinColumn (name = "codigo_producto", nullable = false)
                )
    private List<Producto> lista_productos = new ArrayList<Producto>();
    
    @OneToOne
    private Cliente un_cliente;
    
    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, List<Producto> lista_productos, Cliente un_cliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        
        this.total = 0.0;
        this.lista_productos = lista_productos;
        this.un_cliente = un_cliente;
        this.borrado = false;
    }
    
    
    
    
    
}
