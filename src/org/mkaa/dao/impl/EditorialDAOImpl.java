package org.mkaa.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import org.mkaa.model.Editorial;
import org.mkaa.dao.EditorialDAO;
import org.mkaa.util.Conexion;

public class EditorialDAOImpl implements EditorialDAO {

    @Override
    public boolean insertar(Editorial editorial) {
        String consulta = "{call sp_insertareditorial(?, ?, ?, ?)}";
        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
        ) {
            consultaCall.setString(1, editorial.getNit());
            consultaCall.setString(2, editorial.getNombreEditorial());
            consultaCall.setString(3, editorial.getTelefono());
            consultaCall.setString(4, editorial.getDireccion());
            return consultaCall.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al insertar editorial: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Editorial> listarTodos() {
        List<Editorial> editoriales = new ArrayList<>();
        String consulta = "{call sp_listareditoriales()}";

        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
            ResultSet tablaResultado = consultaCall.executeQuery()
        ) {
            while (tablaResultado.next()) {
                editoriales.add(new Editorial(
                    tablaResultado.getString("nit"),
                    tablaResultado.getString("nombre_editorial"),
                    tablaResultado.getString("direccion_editoria"),
                    tablaResultado.getString("telefono_editorial")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al listar editoriales: " + e.getMessage());
        }
        return editoriales;
    }

    @Override
    public Editorial buscar(String nit) {
        String consulta = "{call sp_buscareditorial(?)}";
        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
        ) {
            consultaCall.setString(1, nit);
            try (ResultSet tablaResultado = consultaCall.executeQuery()) {
                if (tablaResultado.next()) {
                    return new Editorial(
                        tablaResultado.getString("nit"),
                        tablaResultado.getString("nombre_editorial"),
                        tablaResultado.getString("direccion_editoria"),
                        tablaResultado.getString("telefono_editorial")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar editorial: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean actualizar(Editorial editorial) {
        String consulta = "{call sp_actualizareditorial(?, ?, ?, ?)}";
        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
        ) {
            consultaCall.setString(1, editorial.getNit());
            consultaCall.setString(2, editorial.getNombreEditorial());
            consultaCall.setString(3, editorial.getTelefono());
            consultaCall.setString(4, editorial.getDireccion());
            return consultaCall.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al actualizar editorial: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(String nit) {
        String consulta = "{call sp_eliminareditorial(?)}";
        try (
            Connection conexion = Conexion.getInstancia().conectar();
            CallableStatement consultaCall = conexion.prepareCall(consulta);
        ) {
            consultaCall.setString(1, nit);
            return consultaCall.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar editorial: " + e.getMessage());
            return false;
        }
    }
}