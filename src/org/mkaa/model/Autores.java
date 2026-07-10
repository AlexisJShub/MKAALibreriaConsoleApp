package org.mkaa.model;

public class Autores {
   
    /*
    create table autores(
	id_autor int primary key auto_increment,
    nombre_autor varchar(100) not null,
    apellido_autor varchar(100) not null,
    nacionalidad varchar(100),
    biografia text

    */
    
    //atributos de clase
    int id_autor;
    String nombre_autor;
    String nacionalidad;
    String apellido_autor;
    String biografia;
    
    
    //constructores:Asignacion de datos, instanciar objetos
    // vacio
    
    public Autores() {
    }
   
    // lleno con parametros
    public Autores(int id_autor, String nombre_autor, String nacionalidad, String apellido_autor, String biografia) {
        this.id_autor = id_autor;
        this.nombre_autor = nombre_autor;
        this.nacionalidad = nacionalidad;
        this.apellido_autor = apellido_autor;
        this.biografia = biografia;
    }
    //personalizados
    
    
     //getter and setters

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre_autor() {
   
        return nombre_autor;
    }

    public void setNombre_autor(String nombre_autor) {
             // formatear a mayusculas
            //formatear a inicial con myusculas
            String nombreMayuscualas = nombre_autor.toUpperCase();
        this.nombre_autor = nombre_autor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getApellido_autor() {
        return apellido_autor;
    }

    public void setApellido_autor(String apellido_autor) {
        this.apellido_autor = apellido_autor;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
}
