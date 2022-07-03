package com.example.bazar.service;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.model.Producto;
import com.example.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;
    
    @Override
    public List<ProductoSinVentasDTO> getProductos() {
        //Buscar todos los Productos de la BD
        List<Producto> listaProductos = productoRepository.findAll();
        //Inicializo una lista de ProductoSinVentasDTO
        List<ProductoSinVentasDTO> listaProductoDTO = new ArrayList<ProductoSinVentasDTO>();
            
        for (Producto p : listaProductos){
            
            ProductoSinVentasDTO pDTO = new ProductoSinVentasDTO();
            //Uso los datos obtenidos para settear cada objeto ProductoSinVentasDTO 
            //(excepto lista de Productos)
            pDTO.setBorrado(false);
            pDTO.setCantidad_disponible(p.getCantidad_disponible());
            pDTO.setCodigo_producto(p.getCodigo_producto());
            System.out.println("NOMBRE :"+p.getNombre());
            pDTO.setCosto(p.getCosto());
            pDTO.setMarca(p.getMarca());
            pDTO.setNombre(p.getNombre());
            
            listaProductoDTO.add(pDTO);
            
        }
        
        return listaProductoDTO;    
    }

    @Override
    public Producto getProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        return producto;
    }

    @Override
    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void editProducto(Long id, String nombre, String marca, Double costo, Double cantidad, boolean borrado) {
        //busco objeto original
        Producto producto = this.getProducto(id);
        //proceso de modificación a nivel lógico
       
        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setCosto(costo);
        producto.setCantidad_disponible(cantidad);
        producto.setBorrado(borrado);
        
        //guardar objeto con modificaciones
        this.saveProducto(producto);
    }
    
    
    //PUNTO 4
    
    @Override
    public List<ProductoSinVentasDTO> getFaltaStock(){
        List<Producto> listaProductos = productoRepository.findAll();
        
        List<ProductoSinVentasDTO> listaFaltaStock = new ArrayList<ProductoSinVentasDTO>();
        
        for (Producto producto : listaProductos) {
            if (producto.getCantidad_disponible() < 5 ) {
                
                
                ProductoSinVentasDTO productoDTO = new ProductoSinVentasDTO();
                
                productoDTO.setBorrado(false);
                productoDTO.setCantidad_disponible(producto.getCantidad_disponible());
                productoDTO.setCodigo_producto(producto.getCodigo_producto());
                productoDTO.setCosto(producto.getCosto());
                productoDTO.setMarca(producto.getMarca());
                productoDTO.setNombre(producto.getNombre());
                listaFaltaStock.add(productoDTO);
            }
        }
        return listaFaltaStock;
    }
}
