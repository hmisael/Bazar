package com.example.bazar.service;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.model.Producto;
import java.util.List;

public interface IProductoService {
    
    //traer a todos los productos
    public List<ProductoSinVentasDTO> getProductos();
    
    //traer un producto mediante su id
    public Producto getProducto(Long id);
    
    //guardar un producto
    public void saveProducto(Producto producto);
    
    //baja de producto mediante su id
    public void deleteProducto(Long id);
    
    //modificación de producto mediante su id
    public void editProducto(Long id, String nombre, String marca, Double costo, Double cantidad, boolean borrado);
    
    
    //PUNTO 4
    //traer los productos con cantidad_disponible < 5 
    public List<ProductoSinVentasDTO> getFaltaStock();
    
    
    
}
