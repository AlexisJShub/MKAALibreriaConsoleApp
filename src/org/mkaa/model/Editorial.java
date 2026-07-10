package org.mkaa.model;

public class Editorial {
    
    // Atributos de clase
    private int idEditorial;
    private String nombreEditorial;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    // Constructores
    public Editorial() {
    }

    public Editorial(int idEditorial, String nombreEditorial, String direccion, String telefono) {
        this.idEditorial = idEditorial;
        this.nombreEditorial = nombreEditorial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    // Getters and Setters
    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}