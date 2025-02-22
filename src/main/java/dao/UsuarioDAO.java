package dao;

import modelo.Usuario;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioDAO {
    
    // Insertar Usuario
    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nombre, rol, huella) VALUES (?, ?, ?)";
    try (Connection conn = Conexion.abrirConexion();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
            // Hashear la huella antes de guardarla
            String huellaHash = hashHuella(usuario.getHuella());

            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getRol());
            pstmt.setString(3, huellaHash); // Usar el hash en lugar del texto plano
            pstmt.executeUpdate();

            // Obtener el ID generado
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }
    
    // READ
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        Usuario usuario = null;
        try (Connection conn = Conexion.abrirConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("rol"),
                        rs.getString("huella")
                    );
                    usuario.setId(rs.getInt("id"));
                }
            }
        }
        return usuario;
    }
    
    // Update
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        // Query SQL
        String sql = "UPDATE Usuario SET nombre = ?, rol = ?, huella = ? WHERE id = ?";
        try (Connection conn = Conexion.abrirConexion();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            String huellaHash = hashHuella(usuario.getHuella());
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getRol());
            pstmt.setString(3, huellaHash);
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate(); // Error
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setId(rs.getInt(1));
                }
            }
        }
    }
    
    // DELETE
    public void eliminarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = Conexion.abrirConexion()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    // Hashear huella
    private String hashHuella(String huellaPlana) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(huellaPlana.getBytes());
            
            // Convertir bytes a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash SHA-256", e);
        }
    }
    
    // Método para autenticar por usuario/clave
    
    public Usuario autenticar(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ?";
        try (Connection conn = Conexion.abrirConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password));
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario(); // Error
                    usuario.setId(rs.getInt("id"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setUsername(rs.getString("username"));
                    return usuario;
                }
            }
        }
        return null;
    }
    
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}
