/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author OVALTECH
 */
public class Usuario {
    // Atributos
    private int id;
    private String nombre;
    private String rol;
    private String huella;
    // Agregar username y password
    private String username;
    private String password;
    
    // Constructor, getters y setters
    public Usuario(String nombre, String rol, String huella) {
        this.nombre = nombre;
        this.rol = rol;
        this.huella = huella;
    }
    
    // Constructor sin parámetros
    public Usuario() {
        // Inicializa los campos si es necesario
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getHuella() { return huella; }
    public void setHuella(String huella) { this.huella = huella; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
}
