package org.mkaa.dao.impl;

import org.mkaa.util.Conexion;
import org.mkaa.model.Categorias;
import org.mkaa.dao.CategoriasDAO;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAOImpl implements CategoriasDAO {

    @Override
    public List<Categorias> listarTodos() {
        List<Categorias> categorias = new ArrayList<>();
        String consulta = "{call sp_listarcategorias()}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta);
             ResultSet tablaResultado = consultaCall.executeQuery()) {

            while (tablaResultado.next()) {
                categorias.add(new Categorias(
                        tablaResultado.getInt("id_categoria"),
                        tablaResultado.getString("nombre_categoria")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Categorias: " + e.getMessage());
        }
        return categorias;
    }

    @Override
    public Categorias buscarPorId(int idCategoria) {
        Categorias categoria = new Categorias();
        String consultaSQL = "{call sp_buscarcategoria(?)}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consultaSQL)) {

            consultaCall.setInt(1, idCategoria);
            try (ResultSet tablaResultado = consultaCall.executeQuery()) {
                if (tablaResultado.next()) {
                    categoria.setIdCategoria(tablaResultado.getInt("id_categoria"));
                    categoria.setNombreCategoria(tablaResultado.getString("nombre_categoria"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Categoria: " + e.getMessage());
        }
        return categoria;
    }

    @Override
    public boolean insertar(Categorias categoria) {
        String consulta = "{call sp_insertarcategoria(?)}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta)) {

            consultaCall.setString(1, categoria.getNombreCategoria());
            return consultaCall.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear Categoria: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Categorias categoria) {
        return false;
    }

    @Override
    public boolean eliminar(int idCategoria) {
        return false;
    }
}
