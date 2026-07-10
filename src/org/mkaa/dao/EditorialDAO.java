package org.mkaa.dao;

import java.util.List;
import org.mkaa.model.Editorial;

public interface EditorialDAO {
    public boolean insertar(Editorial editorial);
    public List<Editorial> listarTodos();
    public Editorial buscar(String nit);      // Cambiado de long a String
    public boolean actualizar(Editorial editorial);
    public boolean eliminar(String nit);     // Cambiado de long a String
}