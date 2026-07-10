package org.mkaa.dao.impl;

import org.mkaa.util.Conexion;
import org.mkaa.model.Categoria;
import org.mkaa.dao.CategoriaDAO;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class CategoriaDAOImpl implements CategoriaDAO {
    public List<Categoria> ListarTodos() {
        //crear lista 
        List<Categoria>  categoria = new ArrayList<>();//null 
        //crear nuestras consultas 
        String consulta = "{ call sp_listar_categoria()}";
        //maperar el resultado de la consulta a objeto y lo agregaamos a la lista 
        // Try with resources / intentar con recursos -----> cierra el recurso al completar el intetno
        try (Connection conexion = Conexion.getInstancia ().conectar();
                CallableStatement consultaCall = conexion.prepareCall(consulta);
                ResultSet tablaResultado = consultaCall.executeQuery(); )  {
                //ResultSet tabla = Conexion.getInstancia().conectar().prepareCall(consulta).executeQuery();
         //ciclo para rellenar mi lista 
                //verificar cada fila del result set 
                //va a guardar cada celda dentro de cada atributo de objeto
           while (tablaResultado.next()) {
               categoria.add(new Categoria(
                               tablaResultado.getInt("idCategoria"),
                               tablaResultado.getString("nombreCategoria")
                     ));
           }
        }catch (SQLException e) {
            System.out.println("Error al listar Categoria: " + e.getMessage());
        } 
        
        return categoria; 
    }
 
    public boolean crear(Categoria categoria) {
            return false; 
    }
 
    public Categoria buscar(int idCategoria) {
        return null; 
    }
 
    @Override
    public boolean actualizar(Categoria categoria) {
        return false; 
    }
 
    public boolean eliminar(int idCategoria) {
        return false; 
    }

    @Override
    public boolean insertar(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categoria> Listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Categoria buscar(long cui) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(long cui) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
