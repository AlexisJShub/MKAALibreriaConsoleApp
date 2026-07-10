package org.mkaa.dao;

import java.util.List;
import org.mkaa.model.Categoria;

public interface CategoriaDAO {
    //firmas de metodos
    //CRUD
    boolean insertar(Categoria categoria);
    List<Categoria> Listar();
    Categoria buscar(long cui);
    boolean actualizar(Categoria categoria);
    boolean eliminar(long cui);
    
    
}

