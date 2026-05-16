package mx.edu.itson.brmr.reporteciudadano.models;

public class Reporte {

    private String nombre;
    private String colonia;
    private String direccion;
    private String celular;
    private String correo;
    private String tipoReporte;
    private String descripcion;

    public Reporte(String nombre,
                   String colonia,
                   String direccion,
                   String celular,
                   String correo,
                   String tipoReporte,
                   String descripcion) {

        this.nombre = nombre;
        this.colonia = colonia;
        this.direccion = direccion;
        this.celular = celular;
        this.correo = correo;
        this.tipoReporte = tipoReporte;
        this.descripcion = descripcion;
    }

}