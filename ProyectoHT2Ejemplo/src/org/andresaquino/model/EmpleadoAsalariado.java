package org.andresaquino.model;

public class EmpleadoAsalariado extends Empleado{

private double salarioMensual;
public EmpleadoAsalariado(){

}
    public EmpleadoAsalariado(String id, String nombre, double salarioMensual){
super(id, nombre);
this.salarioMensual = salarioMensual;
}

@Override
public double calculaTotal() {
return 0;
}

public void setSalarioMensual(double salarioMensual){
    this.salarioMensual = salarioMensual;
}
public