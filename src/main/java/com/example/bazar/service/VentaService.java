package com.example.bazar.service;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.dto.VentaMayorDTO;
import com.example.bazar.dto.VentaSinProductosDTO;
import com.example.bazar.dto.VentasPorDiaDTO;
import com.example.bazar.model.Cliente;
import com.example.bazar.model.Producto;
import com.example.bazar.model.Venta;
import com.example.bazar.repository.IClienteRepository;
import com.example.bazar.repository.IProductoRepository;
import com.example.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private IVentaRepository ventaRepository;
    
    @Autowired
    private IProductoRepository productoRepository;
     
     @Autowired
    private IClienteRepository clienteRepository;
    
    @Override
    public List<VentaSinProductosDTO> getVentas() {
        //Buscar todas las ventas de la BD
        List<Venta> listaVentas = ventaRepository.findAll();

        //Inicializo una lista de VentaSinProductosDTO
        List<VentaSinProductosDTO> listaVentaDTO = new ArrayList<VentaSinProductosDTO>();
            
        for (Venta v : listaVentas){
            
            VentaSinProductosDTO vDTO = new VentaSinProductosDTO();
            //Uso los datos obtenidos para settear cada objeto ProductoSinVentasDTO 
            //(excepto lista de Productos)
            
            vDTO.setBorrado(false);
            vDTO.setCodigo_venta(v.getCodigo_venta());
            vDTO.setFecha_venta(v.getFecha_venta());
            vDTO.setTotal(v.getTotal());
            listaVentaDTO.add(vDTO);
            
        }
        
        return listaVentaDTO;    
        
        
    }

    
    @Override
    public VentaSinProductosDTO getVenta(Long codigo) {
        Venta venta = ventaRepository.findById(codigo).orElse(null);
        
        VentaSinProductosDTO ventaDTO = new VentaSinProductosDTO(); 
        
        ventaDTO.setBorrado(false);
        ventaDTO.setCodigo_venta(venta.getCodigo_venta());
        ventaDTO.setFecha_venta(venta.getFecha_venta());
        ventaDTO.setTotal(venta.getTotal());
        
        return ventaDTO;    
    }

    @Override
    public void saveVenta(Venta venta) {

        List<Producto> listaProductos = venta.getLista_productos();
        Double total = 0.0;
        Long id = 0L;
        
        List<Producto> listaOther = new ArrayList<Producto>();
        
        //el total de la venta es la sumatoria del costo de los Productos 
        for (Producto producto : listaProductos){
            id = producto.getCodigo_producto();
           
            //¿Por qué me devuelve null? :(
            //Producto p = productoService.getProducto(id);
            //Pero productoRepository vino al rescate :)
            Producto p = productoRepository.findById(id).orElse(null);
            total = total + p.getCosto();
            
        }
        
        venta.setTotal(total);
        ventaRepository.save(venta);
        
    }

    @Override
    public void deleteVenta(Long codigo) {
        ventaRepository.deleteById(codigo);
    }

    @Override
    public void editVenta(Long codigo, LocalDate fecha_venta, Double total, boolean borrado, List<Producto> productos, Cliente cliente) {
        //busco objeto original
        Venta venta = ventaRepository.findById(codigo).orElse(null);
        //proceso de modificación a nivel lógico
       
        venta.setFecha_venta(fecha_venta);
        venta.setTotal(total);
        venta.setBorrado(borrado);
        venta.setLista_productos(productos);
        venta.setUn_cliente(cliente);

        //guardar objeto con modificaciones
        this.saveVenta(venta);
    }

    
    //PUNTO 5: Lista de Productos de una Venta
    @Override
    public List<ProductoSinVentasDTO> getProductosDeVenta(Long codigo) {
        
        Venta venta = ventaRepository.findById(codigo).orElse(null);
        List<Producto> listaProductos = venta.getLista_productos();
        
        List<ProductoSinVentasDTO> listaProductosDTO = new ArrayList<ProductoSinVentasDTO>();
        
        for (Producto p : listaProductos){
            ProductoSinVentasDTO productoDTO = new ProductoSinVentasDTO();
            productoDTO.setBorrado(false);
            productoDTO.setCantidad_disponible(p.getCantidad_disponible());
            productoDTO.setCodigo_producto(p.getCodigo_producto());
            productoDTO.setCosto(p.getCosto());
            productoDTO.setMarca(p.getMarca());
            productoDTO.setNombre(p.getNombre());
            
            listaProductosDTO.add(productoDTO);
        }
        
        return listaProductosDTO;
    }
        
     //PUNTO 6: monto y cantidad de ventas de un determinado día
    
    @Override
    public VentasPorDiaDTO getMontoCantVentasPorDia(LocalDate fecha) {
        List<Venta> listaVentas = ventaRepository.findAll();
        VentasPorDiaDTO ventaDia = new VentasPorDiaDTO(); 
                
        Double monto = 0.0;
        int cantidad = 0;
        
        for (Venta venta : listaVentas){
            if (venta.getFecha_venta().equals(fecha)){
                monto += venta.getTotal() ;
                cantidad+= 1;
            }
        }
        
        ventaDia.setCantidad(cantidad);
        ventaDia.setMonto(monto);
       
        return ventaDia;
    }

    //PUNTO 7: obtener codigo_venta, total, cantidad_productos, nombre y apellido de cliente 
    //de la venta con monto más alto de todas
    
    @Override
    public VentaMayorDTO getMayorVenta() {
        List<Venta> listaVentas = ventaRepository.findAll();
        
        Double mayor = 0.0;
        Venta ventaMayor = new Venta();
        VentaMayorDTO ventaDTO = new VentaMayorDTO(); 
               
        for (Venta venta : listaVentas){
            if (venta.getTotal() > mayor){
                //Guardo la Venta con el mayor total 
                ventaMayor = venta;
            } 
        }
        
        
        //Setteo el codigo de venta y el total 
        ventaDTO.setCodigo_venta(ventaMayor.getCodigo_venta());
        ventaDTO.setTotal(ventaMayor.getTotal());
        
        //Setteo la cantidad (tamaño de la lista de Productos)
        int cantidad = this.getProductosDeVenta(ventaMayor.getCodigo_venta()).size();
        ventaDTO.setCantidad(cantidad);
        
        //setteo los datos del cliente
        Cliente cliente = ventaMayor.getUn_cliente();
        ventaDTO.setApellido_cliente(cliente.getApellido());
        ventaDTO.setNombre_cliente(cliente.getNombre());
        
        return ventaDTO;
    }
    
}
