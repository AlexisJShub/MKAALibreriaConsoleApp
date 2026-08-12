package org.mkaa.dao.impl;


import java.util.ArrayList;
import org.mkaa.model.Cliente;
import org.mkaa.dao.ClienteDAO;

import org.mkaa.util.Conexion;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public List<Cliente> listarTodos() {
    
        List<Cliente> clientes = new ArrayList<>();//null

        String consulta = "{call sp_listarclientes()}";
       
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consulta); ResultSet tablaResultado = consultaCall.executeQuery();) {

            while (tablaResultado.next()) {
                clientes.add(new Cliente(
                        tablaResultado.getLong("cui"),
                        tablaResultado.getString("nombre_cliente"),
                        tablaResultado.getString("apellido_cliente"),
                        tablaResultado.getString("correo_electronico")
                ));
            }
        } catch (SQLException e) {
            System.err.print("Error al listar Clientes: " + e.getMessage());
        }

   
        return clientes;
    }

    @Override
    public boolean crear(Cliente cliente) {
        String consulta = "{call sp_insertarcliente(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {
            consultaCall.setLong(1, cliente.getCui());
            consultaCall.setString(2, cliente.getNombre());
            consultaCall.setString(3, cliente.getApellido());
            consultaCall.setString(4, cliente.getCorreoElectronico());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.print("Error al crear Cliente: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(long cui) {
      
        Cliente cliente = new Cliente();

    
        String consultaSQL = "{call sp_buscarcliente(?)}";
  
        try (Connection conexion = Conexion.getInstancia().conectar(); CallableStatement consultaCall = conexion.prepareCall(consultaSQL);) {
            consultaCall.setLong(1, cui);
            ResultSet tablaResultado = consultaCall.executeQuery();
            if (tablaResultado.next()) {
                cliente.setCui(tablaResultado.getLong("cui"));
                cliente.setNombre(tablaResultado.getString("nombre_cliente"));
                cliente.setApellido(tablaResultado.getString("apellido_cliente"));
                cliente.setCorreoElectronico(tablaResultado.getString("correo_electronico"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Error al buscar Cliente: " + e.getMessage());
        }
        //retornamos el objeto
        return cliente;
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        return false;
    }

    @Override
    public boolean eliminar(long cui) {
        return false;
    }

}