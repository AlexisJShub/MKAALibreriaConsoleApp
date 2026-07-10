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
        String consulta = "{call sp_insertarautor(?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement stmt = conexion.prepareCall(consulta)) {
             
            stmt.setString(1, autor.getNombreAutor());
            stmt.setString(2, autor.getApellidoAutor());
            stmt.setString(3, autor.getNacionalidad());
            stmt.setString(4, autor.getBiografia());
            
            // Si affectedRows es mayor a 0, se insertó correctamente
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar Autor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Autores> listarTodos() {
        List<Autores> listaAutores = new ArrayList<>(); 
        String consulta = "{call sp_listarautores()}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta); 
             ResultSet tablaResultado = consultaCall.executeQuery()) {

            while (tablaResultado.next()) {
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

    @Override
    public Autores buscarPorId(int id) {
        Autores autor = null;
        String consulta = "{call sp_buscarautor(?)}";
        
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement stmt = conexion.prepareCall(consulta)) {
             
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    autor = new Autores(
                        rs.getInt("id_autor"),
                        rs.getString("nombre_autor"),
                        rs.getString("apellido_autor"),
                        rs.getString("nacionalidad"),
                        rs.getString("biografia")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Autor: " + e.getMessage());
        }
        return autor; 
    }

    @Override
    public boolean actualizar(Autores autor) {
        String consulta = "{call sp_actualizarautor(?, ?, ?, ?, ?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement stmt = conexion.prepareCall(consulta)) {
             
            stmt.setInt(1, autor.getIdAutor());
            stmt.setString(2, autor.getNombreAutor());
            stmt.setString(3, autor.getApellidoAutor());
            stmt.setString(4, autor.getNacionalidad());
            stmt.setString(5, autor.getBiografia());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar Autor: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String consulta = "{call sp_eliminarautor(?)}";
        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement stmt = conexion.prepareCall(consulta)) {
             
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar Autor: " + e.getMessage());
            return false;
        }
    }
}