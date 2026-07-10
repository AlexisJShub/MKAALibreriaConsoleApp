package org.mkaa.dao.impl;
 
import org.mkaa.util.Conexion;
import org.mkaa.dao.ClienteDAO;
import org.mkaa.model.Clientes;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet; 

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public boolean insertar(Clientes cliente) {
        return false; 
    }

    @Override
    public List<Clientes> listarTodos() {
        // crear lista
        List<Clientes> clientes = new ArrayList<>(); 
        // crear nuestra consulta 
        String consulta = "{call sp_listarclientes()}"; 

        // try with resources -> cierra automáticamente los recursos al terminar
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta); 
             ResultSet tablaResultado = consultaCall.executeQuery()) {

            // ciclo para rellenar mi lista 
            while (tablaResultado.next()) {
                // Nota: Asegúrate de que tu clase Clientes tenga un constructor que acepte estos 4 parámetros
                clientes.add(new Clientes(
                        tablaResultado.getLong("cui"),
                        tablaResultado.getString("nombreCliente"),
                        tablaResultado.getString("apellidoCliente"),
                        tablaResultado.getString("correoElectronico")
                )); 
            }  

        } catch (SQLException e) {
            System.err.println("Error al listar Clientes: " + e.getMessage()); 
        }

        // retornamos la lista 
        return clientes; 
    }

    @Override
    public Clientes buscarPorId(long cui) {
        return null; 
    }

    @Override
    public boolean actualizar(Clientes cliente) {
        return false; 
    }

    @Override
    public boolean eliminar(long cui) {
        return false; 
    }

    
}