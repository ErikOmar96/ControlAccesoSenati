/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import modelo.Asistencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AsistenciaDAO {
    
    // CREATE
    public void insertarAsistencia(Asistencia asistencia) throws SQLException {
        String sql = "INSERT INTO Asistencia (fecha, hora, tipo, usuario_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Conexion.abrirConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, asistencia.getFecha());
            pstmt.setTime(2, asistencia.getHora());
            pstmt.setString(3, asistencia.getTipo());
            pstmt.setInt(4, asistencia.getUsuarioId());
            pstmt.executeUpdate();
        }
    }
    
    // READ (Obtener por ID)
    public Asistencia obtenerAsistenciaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Asistencia WHERE id = ?";
        Asistencia asistencia = null;
        try (Connection conn = Conexion.abrirConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                      
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    asistencia = new Asistencia(
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getString("tipo"),
                        rs.getInt("usuario_id")
                    );
                    asistencia.setId(rs.getInt("id"));
                }
            }
        }
        return asistencia;
    }
    
    // UPDATE
    public void actualizarAsistencia(Asistencia asistencia) throws SQLException {
        String sql = "UPDATE Asistencia SET fecha = ?, hora = ?, tipo = ? WHERE id = ?";
        try (Connection conn = Conexion.abrirConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, asistencia.getFecha());
            pstmt.setTime(2, asistencia.getHora());
            pstmt.setString(3, asistencia.getTipo());
            pstmt.setInt(4, asistencia.getId());
            pstmt.executeUpdate();
        }
        
    }
    
    // DELETE
    public void eliminarAsistencia(int id) throws SQLException {
        String sql = "DELETE FROM Asistencia WHERE id = ?";
        
        try (Connection conn = Conexion.abrirConexion();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    
    public List<Asistencia> listar(String idUsuario) throws Exception {
    List<Asistencia> lista = new ArrayList<>();
    // Usar nombre de tabla y campo correctos (MySQL es case-sensitive en algunos sistemas)
    String query = idUsuario.isEmpty() 
            ? "SELECT * FROM Asistencia;" 
            : "SELECT * FROM Asistencia WHERE usuario_id LIKE '%" + idUsuario + "%';";
    try (Connection conn = Conexion.abrirConexion();
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            Asistencia asistencia = new Asistencia(
                rs.getDate("fecha"),
                rs.getTime("hora"),
                rs.getString("tipo"),
                rs.getInt("usuario_id") // Campo corregido
            );
            asistencia.setId(rs.getInt("id"));
            lista.add(asistencia);
        }
    } catch (Exception ex) {
        throw ex;
    }
    return lista;
    }
}
