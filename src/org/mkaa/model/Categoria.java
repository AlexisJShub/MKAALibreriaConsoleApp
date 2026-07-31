package org.mkaa.model;

//POJO: Nombre, atributos, constructores, getters y setters
public class Categoria {

    /*
    create table categoria(
        id bigint primary key,
        nombre_categoria varchar(100),
       
    );
     */

    //atributos de clase
    long id;
    String nombre;
   

    //constructores: asignación de datos, instanciar objetos
    //vacio
    public Categoria() {
    }
    //lleno o con parametros
    public Categoria(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    //personalizador
    
    
    //getter and setters

    public long getId() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public String getNombre() {        
        return nombre;
    }

    public void setNombre(String nombre) {
        //formatear a Mayusucual
        String nombreMayusculas = nombre.toUpperCase();
        //formatear a Inicia con Mayusuculas
        this.nombre = nombreMayusculas;
    }

    public void setIdCategoria(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNombreCategoria(String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    
    
}