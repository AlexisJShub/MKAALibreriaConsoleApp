package org.andresaquino.model;

public class Empleado {
//atributos
private String id;
private String nombre;

//constructores: vacio y lleno
public Empleado () {

}

public Empleado (String id, String nombre) {
this.id = id;
this.nombre = nombre;
}

//Empleado empleado = new Empleado("1","Juan Perez");
//Empleado empleado = new Empleado();

//metodos getter y setter
public void setId(String id) {
this.id = id;
}
public String getId(){
    return this.id;
}
//empleado.Id = "0000001" X
// sout(empleado.id) x

//empleado.setId("002")
//string id = empleado.getId()

public void setNombre(String nombre){
this.nombre = nombre;
}
public String getNombre(){
return this.nombre;
}

}