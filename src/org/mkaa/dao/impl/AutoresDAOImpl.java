package org.mkaa.dao.impl;

import org.mkaa.util.Conexion;
import org.mkaa.dao.AutoresDAO;
import org.mkaa.model.Autores;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class AutoresDAOImpl implements AutoresDAO {

    @Override
    public boolean insertar(Autores autor) {
        return false; 
    }

    @Override
    public List<Autores> listarTodos() {
        List<Autores> listaAutores = new ArrayList<>(); 
     
        String consulta = "{call sp_listarautores()}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta); 
             ResultSet tablaResultado = consultaCall.executeQuery()) {

            while (tablaResultado.next()) {
                // Mapeo correcto según los campos de la tabla autores
                listaAutores.add(new Autores(
                        tablaResultado.getInt("id_autor"),
                        tablaResultado.getString("nombre_autor"),
                        tablaResultado.getString("apellido_autor"),
                        tablaResultado.getString("nacionalidad"),
                        tablaResultado.getString("biografia")
                )); 
            }  

        } catch (SQLException e) {
            System.err.println("Error al listar Autores: " + e.getMessage()); 
        }

        return listaAutores; 
    }

    public Autores buscarPorId(long id) {
        return null; 
    }

    @Override
    public boolean actualizar(Autores autores) {
        return false; 
    }

 
    
    @Override
    public boolean eliminar(int id_autores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Autores buscar(int id_autores) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
