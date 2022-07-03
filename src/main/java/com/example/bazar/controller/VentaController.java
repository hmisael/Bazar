package com.example.bazar.controller;

import com.example.bazar.dto.ProductoSinVentasDTO;
import com.example.bazar.dto.VentaMayorDTO;
import com.example.bazar.dto.VentaSinProductosDTO;
import com.example.bazar.dto.VentasPorDiaDTO;
import com.example.bazar.model.Cliente;
import com.example.bazar.model.Producto;
import com.example.bazar.model.Venta;
import com.example.bazar.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
 
    @Autowired
    private IVentaService ventaService;
    
    //Traer todas las ventas
    @GetMapping ("/ventas")
    public List<VentaSinProductosDTO> getVentas() {
        return ventaService.getVentas();
    }
    
    //Traer una venta según su código
    @GetMapping ("/ventas/{codigo_venta}")
    public VentaSinProductosDTO getVenta(@PathVariable Long codigo_venta) {
        return ventaService.getVenta(codigo_venta);
    }

    //Crear una venta
    @PostMapping ("/ventas/crear")
    public String saveVenta (@RequestBody Venta venta) {
        ventaService.saveVenta(venta);
        return "La venta fue creada correctamente";
    }
    
    //Editar una venta
    @PutMapping ("/ventas/editar/{codigo_original}")
    public VentaSinProductosDTO editVenta (@PathVariable Long codigo_original,
            @RequestParam(required = false, name = "fecha") LocalDate nuevaFecha,
            @RequestParam(required = false, name = "total") Double nuevoTotal,
            @RequestParam(required = false, name = "borrado") Boolean nuevoBorrado,
            @RequestParam(required = false, name = "borrado") List<Producto> nuevosProductos,
            @RequestParam(required = false, name = "borrado") Cliente nuevoCliente
            ) {
            
        
        ventaService.editVenta(codigo_original, nuevaFecha, nuevoTotal, nuevoBorrado, nuevosProductos, nuevoCliente);
        
        VentaSinProductosDTO venta = ventaService.getVenta(codigo_original);
        
        return venta;

    }
    
    
    //PUNTO 5: Traer los productos de una venta según su código
    
    @GetMapping ("/ventas/productos/{codigo_venta}")
    public List<ProductoSinVentasDTO> getProductosDeVenta(@PathVariable Long codigo_venta) {
        return ventaService.getProductosDeVenta(codigo_venta);
    }
    
    
    //PUNTO 6: Traer monto total y cantidad de Ventas en un determinado día
    
    @GetMapping ("/ventas/fecha/{fecha_venta}")
    public VentasPorDiaDTO getMontoCantVentasPorDia(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ventaService.getMontoCantVentasPorDia(fecha);
    }
    
    //PUNTO 7
    @GetMapping ("/ventas/mayor_venta")
    public VentaMayorDTO getMayorVenta () {
        return ventaService.getMayorVenta();
    }

    
}
