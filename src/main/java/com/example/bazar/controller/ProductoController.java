/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bazar.controller;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.model.Producto;
import com.example.bazar.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hernán Misael
 */
@RestController
public class ProductoController {
    @Autowired
    private IProductoService productoService;
    
    //Traer todos los productos
    @GetMapping ("/productos")
    public List<ProductoSinVentasDTO> getProductos() {
        return productoService.getProductos();
    }
    
    //Traer un producto según su id
    @GetMapping ("/productos/{id_producto}")
    public Producto getProducto(@PathVariable Long id_producto) {
        return productoService.getProducto(id_producto);
    }

    //Crear un producto
    @PostMapping ("/productos/crear")
    public String saveProducto (@RequestBody Producto producto) {
        productoService.saveProducto(producto);
        return "El producto fue creado correctamente";
    }
    
    //Editar un producto
    @PutMapping ("/productos/editar/{codigo_original}")
    public Producto editProducto (@PathVariable Long codigo_original,
            @RequestParam(required = false, name = "nombre") String nuevoNombre,
            @RequestParam(required = false, name = "marca") String nuevaMarca,
            @RequestParam(required = false, name = "costo") Double nuevoCosto,
            @RequestParam(required = false, name = "cantidad") Double nuevaCantidad,
            @RequestParam(required = false, name = "borrado") Boolean nuevoBorrado) {
            
        productoService.editProducto(codigo_original, nuevoNombre, nuevaMarca, nuevoCosto, nuevaCantidad, nuevoBorrado);
        
        Producto producto = productoService.getProducto(codigo_original);
        
        return producto;
    }
    
    
    //4- Traer todos los productos con cantidad_disponible < 5
    @GetMapping ("/productos/faltastock")
    public List<ProductoSinVentasDTO> getFaltaStock() {
        return productoService.getFaltaStock();
    }
    
    
    
}
