package org.mkaa.model;


public class Cliente {


    long cui;
    String nombre;
    String apellido;
    String correoElectronico;

 
    public Cliente() {
    }
   
    public Cliente(long cui, String nombre, String apellido, String correoElectronico) {
        this.cui = cui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }

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

        String nombreMayusculas = nombre.toUpperCase();
   
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
    
    
}