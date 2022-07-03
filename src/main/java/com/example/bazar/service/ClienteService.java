/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bazar.service;

import com.example.bazar.model.Cliente;
import com.example.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hernán Misael
 */
@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    
    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = clienteRepository.findAll();
        return listaClientes;     
    }

    @Override
    public Cliente getCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return cliente;
    }

    @Override
    public void saveCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void editCliente(Cliente cliente) {

        //guardar objeto con modificaciones
        this.saveCliente(cliente);
        
    }
    
}
