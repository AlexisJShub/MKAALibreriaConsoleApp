package org.mkaa.dao;

import java.util.List;
import org.mkaa.model.Autores;
import java.util.List;

public interface AutoresDAO { 
    boolean insertar(Autores autor); 
    List<Autores> listarTodos();
    Autores buscarPorId(int id); 
    boolean actualizar(Autores autor);
    boolean eliminar(int id); 
}