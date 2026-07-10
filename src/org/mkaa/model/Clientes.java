package org.mkaa.model;
 
/**
*

*/
//POJO Nombre, atributos 
public class Clientes {
    /*
    create table clientes(
	cui bigint primary key,
    nombre_cliente varchar(100),
    apellido_cliente varchar(100),
    correo_electronico varchar(100)
);
    */
    // atributos de clase
    long cui;
    String nombre;
    String apellido;
    String correoElectronico;
    //constructores: asignacion de datos, instanciar e iniciar objetos
    // vacio
    public Clientes() {
    }
     //lleno con parametros
    public Clientes(long cui, String nombre, String apellido, String correoElectronico) {
        this.cui = cui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }
//personalizado
    //getter and setters
 
    public long getCui() {
        return cui;
    }
 
    public void setCui(long cui) {
        this.cui = cui;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        //formatear a Mayusculas
        //formatear a Inicia con mayusculas
        String nombreMayusculas =nombre.toUpperCase();
        this.nombre = nombreMayusculas;
    }
 
    public String getApellido() {
        return apellido;
    }
 
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
 
    public String getCorreoElectronico() {
        return correoElectronico;
    }
 
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
 
    public void setCorreo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
    
}
