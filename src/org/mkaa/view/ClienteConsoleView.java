package org.mkaa.view;

import java.util.List;
import java.util.Scanner;
import org.mkaa.model.Clientes;
 
public class ClienteConsoleView {
    
    private final Scanner leer = new Scanner(System.in);
 
    // método para mostrar las opciones de este menú
    public int mostrarMenu() {
        int opcion = 0;
        // todo el menú
        System.out.println("--- GESTION DE CLIENTES ---");
        System.out.println("-1 CREAR nuevo Cliente ---");
        System.out.println("-2 LISTAR todos los Clientes ---");
        System.out.println("-3 BUSCAR Cliente por ID ---");
        System.out.println("-4 MODIFICAR Cliente ---");
        System.out.println("-5 ELIMINAR nuevo Cliente ---");
        System.out.println("-6 REGRESAR a menú PRINCIPAL ---");
        System.out.println("SELECCIONE UNA OPCION --->");
        opcion = Integer.parseInt(leer.nextLine());
        return opcion;
    }
 
    public long solicitarCUI() {
        System.out.println("Ingres el CUI del cliente: ");
        return Long.parseLong(leer.nextLine());
    }
 
    // nombreCliente
    public String solicitarNombreCliente() {
        System.out.println("Ingres el NOMBRE del cliente");
        return leer.nextLine();
    }
 
    // apellidoCliente
    public String solicitarApellidoCliente() {
        System.out.println("Ingres el APELLIDO del cliente");
        return leer.nextLine();
    }
 
    // correoElectronico
    public String solicitarCorreoElectronico() {
        System.out.println("Ingres el CORREO electrónico del cliente");
        return leer.nextLine();
    }
 
    // mostrar el detalle de un CLIENTE
    public void mostrarCliente(Clientes cliente) {
        System.out.println("--- DATOS DEL CLIENTE ---");
        System.out.println("CUI: " + cliente.getCui());
        System.out.println("NOMBRE: " + cliente.getNombre());
        System.out.println("APELLIDO: " + cliente.getApellido());
        System.out.println("CORREO E: " + cliente.getCorreoElectronico());
    }
 
    // mostrar la lista de CLIENTES -- lista de objeto List<T>, ArrayList<Cliente>
    public void mostrarListaClientes(List<Clientes> clientes) {
        System.out.println("--- LISTA DE CLIENTES ---");
        // tabla usando la propiedad %-[tamaño de columna]s
        // Nota: Se añade \n al final de los formatos string para que realice el salto de línea por cada fila.
        System.out.printf("%-10s %-10s %-10s %-10s\n", "CUI", "NOMBRE", "APELLIDO", "CORREO");
        
        for (Clientes cliente : clientes) {
            System.out.printf("%-10s %-10s %-10s %-10s\n",
                cliente.getCui(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getCorreoElectronico()
            );
        }
    }
 
    // para mostrar mensaje personalizado
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}