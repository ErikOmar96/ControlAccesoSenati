/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author OVALTECH
 */
public class Asistencia {
    // Atributos
    private int id;
    private Date fecha;
    private Time hora;
    private String tipo;
    private int usuarioId;
    
    // Constructor, getters y setters
    public Asistencia(Date fecha, Time hora, String tipo, int usuarioId) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.usuarioId = usuarioId;
    }
    
    public Asistencia() {
    
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Time getHora() { return hora; }
    public void setHora(Time hora) { this.hora = hora; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}
