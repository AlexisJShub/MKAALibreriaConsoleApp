package org.mkaa.dao;
 
import java.util.List;
import org.mkaa.model.Clientes;
 
 
public interface ClienteDAO {
    //firmas de metodos
    //CRUD
    boolean insertar(Clientes cliente);
    List<Clientes> listarTodos();
    Clientes buscar(long cui);
    boolean actualizar(Clientes cliente);
    boolean eliminar(long cui);
}