package org.mkaa.dao;
 
import java.util.List;
import org.mkaa.model.Autores;
public interface AutoresDAO {
 
    boolean insertar(Autores autores);
    List<Autores> listarTodos();
    Autores buscar(int id_autores);
    boolean actualizar(Autores autores);
    boolean eliminar(int id_autores);


}