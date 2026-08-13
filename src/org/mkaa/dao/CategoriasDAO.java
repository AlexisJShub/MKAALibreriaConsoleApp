package org.mkaa.dao;

import java.util.List;
import org.mkaa.model.Categorias;

public interface CategoriasDAO {

    List<Categorias> listarTodos();
    Categorias buscarPorId(int idCategoria);
    boolean insertar(Categorias categoria);
    boolean actualizar(Categorias categoria);
    boolean eliminar(int idCategoria);
}
