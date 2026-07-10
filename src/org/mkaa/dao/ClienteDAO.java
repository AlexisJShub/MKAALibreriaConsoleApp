package org.mkaa.dao;
 
import java.util.List; 
import org.mkaa.model.Cliente;
 
public interface ClienteDAO {

    boolean insertar(Cliente cliente);
    List<Cliente> listarTodos(); 
    Cliente buscar(long cui);
    boolean actualizar(Cliente cliente); 
    boolean eliminar(long cui); 

}
 
