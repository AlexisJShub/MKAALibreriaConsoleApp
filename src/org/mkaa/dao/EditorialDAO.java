package org.mkaa.dao.impl; // O el paquete correcto de tus DAOs, por ejemplo: org.mkaa.dao

import java.util.List;
import org.mkaa.model.Editorial; // Asegúrate de importar tu clase de modelo Editorial

public interface EditorialDAO {
    
    // Firmas de métodos - CRUD para Editorial
    
    boolean insertar(Editorial editorial);
    
    List<Editorial> listarTodos();
    
    Editorial buscar(int id_editorial); // Puedes cambiar 'int' por 'long' si tu ID es muy grande
    
    boolean actualizar(Editorial editorial);
    
    boolean eliminar(int id_editorial);
}