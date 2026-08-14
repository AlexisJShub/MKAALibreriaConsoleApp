package org.mkaa.model;

public class Editorial {
    private String nit; // Cambiado de int a String para reflejar tu base de datos
    private String nombreEditorial;
    private String direccion;
    private String telefono;

    public Editorial() {
    }

    public Editorial(String nit, String nombreEditorial, String direccion, String telefono) {
        this.nit = nit;
        this.nombreEditorial = nombreEditorial;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}