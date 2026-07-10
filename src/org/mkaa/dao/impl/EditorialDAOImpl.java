package org.mkaa.dao.impl;

import org.mkaa.model.Editorial;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.mkaa.util.Conexion;
// Asegúrate de que esta ruta a tu clase Conexion sea la correcta en tu proyecto

public class EditorialDAOImpl implements EditorialDAO {

    @Override
    public boolean insertar(Editorial editorial) {
        // Aquí implementarás la lógica para insertar una editorial
        return false;
    }

    @Override
    public List<Editorial> listarTodos() {
        List<Editorial> listaEditoriales = new ArrayList<>();
        String consulta = "{call sp_listareditoriales()}";

        try (Connection conexion = Conexion.getInstancia().conectar();
             CallableStatement consultaCall = conexion.prepareCall(consulta);
             ResultSet resultado = consultaCall.executeQuery()) {

            while (resultado.next()) {
                // Ajusta los nombres de las columnas ("id_editorial", "nombre", etc.) 
                // según cómo estén definidos exactamente en tu base de datos y constructor
                listaEditoriales.add(new Editorial(
                    resultado.getInt("id_editorial"),
                    resultado.getString("nombre_editorial"),
                    resultado.getString("direccion"),
                    resultado.getString("telefono")
                ));
            }

        } catch (Exception e) {
            System.err.println("Error al listar Editoriales: " + e.getMessage());
        }

        return listaEditoriales;
    }

    @Override
    public Editorial buscar(int id_editorial) {
        // Aquí implementarás la lógica para buscar una editorial por ID
        return null;
    }

    @Override
    public boolean actualizar(Editorial editorial) {
        // Aquí implementarás la lógica para actualizar una editorial
        return false;
    }

    @Override
    public boolean eliminar(int id_editorial) {
        // Aquí implementarás la lógica para eliminar una editorial
        return false;
    }
}