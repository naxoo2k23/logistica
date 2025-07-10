package com.Logistica.logistica.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Logistica.logistica.model.Envio;
import com.Logistica.logistica.model.Proveedor;
import com.Logistica.logistica.model.RutaEntrega;
import com.Logistica.logistica.repository.EnvioRepository;
import com.Logistica.logistica.repository.ProveedorRepository;
import com.Logistica.logistica.repository.RutaEntregaRepository;

@Service


public class LogisticaService {

    @Autowired private EnvioRepository envioRepository;
    @Autowired private ProveedorRepository proveedorRepository;
    @Autowired private RutaEntregaRepository rutaEntregaRepository;

    //Envios
    public List<Envio> obtenerTodosLosEnvios() {return envioRepository.findAll();}
    public Envio guardarEnvio(Envio envio) {
        Proveedor proveedor = proveedorRepository.findById(envio.getProveedor().getIdProveedor())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        RutaEntrega ruta = rutaEntregaRepository.findById(envio.getRutaEntrega().getIdRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        envio.setProveedor(proveedor);
        envio.setRutaEntrega(ruta);

        return envioRepository.save(envio);
    }
    public void eliminarEnvio(int id) {envioRepository.deleteById(id);}

    //Proveedores
    public List<Proveedor> obtenerProveedores() {return proveedorRepository.findAll();}
    public Proveedor guardarProveedor(Proveedor proveedor) {return proveedorRepository.save(proveedor);}

    //Rutas
    public List<RutaEntrega> obtenerRutas() {return rutaEntregaRepository.findAll();}
    public RutaEntrega guardarRuta(RutaEntrega ruta) {return rutaEntregaRepository.save(ruta);}

}
