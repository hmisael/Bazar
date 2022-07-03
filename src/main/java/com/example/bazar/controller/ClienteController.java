package com.example.bazar.controller;

import com.example.bazar.model.Cliente;
import com.example.bazar.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    //Traer todos los clientes
    @GetMapping ("/clientes")
    public List<Cliente> getClientes() {
        return clienteService.getClientes();
    }
    
    //Traer un cliente según su id
    @GetMapping ("/clientes/{id_cliente}")
    public Cliente getCliente(@PathVariable Long id_cliente) {
        return clienteService.getCliente(id_cliente);
    }

    //Crear un cliente
    @PostMapping ("/clientes/crear")
    public String savePersona (@RequestBody Cliente cliente) {
        clienteService.saveCliente(cliente);
        return "El cliente fue creado correctamente";
    }
    
    //Editar un cliente
    @PutMapping ("/clientes/editar/{id_original}")
    public Cliente editCliente (@RequestBody Cliente cliente) {
        
        clienteService.editCliente(cliente);
       
        return cliente;

    }
    
    
    
    
    
    
    
    
    
}
