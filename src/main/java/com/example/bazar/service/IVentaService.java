/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bazar.service;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.dto.VentaMayorDTO;
import com.example.bazar.dto.VentaSinProductosDTO;
import com.example.bazar.dto.VentasPorDiaDTO;
import com.example.bazar.model.Cliente;
import com.example.bazar.model.Producto;
import com.example.bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Hernán Misael
 */
public interface IVentaService {
    
    //traer todas las ventas 
    public List<VentaSinProductosDTO> getVentas();
    
    //traer una venta mediante su codigo
    public VentaSinProductosDTO getVenta(Long codigo);
    
    //guardar una venta 
    public void saveVenta(Venta producto);
    
    //baja de venta mediante su codigo
    public void deleteVenta(Long codigo);
    
    //modificación de venta mediante su id
    public void editVenta(Long codigo, LocalDate fecha_venta, Double total, boolean borrado, List<Producto> productos, Cliente cliente);
    
    //PUNTO 5
    //traer los productos de una venta mediante su codigo
    public List<ProductoSinVentasDTO> getProductosDeVenta(Long codigo);
    
    //PUNTO 6
    public VentasPorDiaDTO getMontoCantVentasPorDia(LocalDate fecha);

    //PUNTO 7
    public VentaMayorDTO getMayorVenta();
   
}
