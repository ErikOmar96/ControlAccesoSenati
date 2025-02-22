package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Variable global de tipo Connection
    public static Connection conexion;
    // Propiedades para la conexion
    // private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_asistencia";
    private static final String USER = "root";
    private static final String PASS = "";
    
//    // Método para abrir la conexion
//    public void conectar() throws ClassNotFoundException {
//        try {
//            // Variable que va almacenar los parámetros de conexion
//            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Conexión exitosa a la BD");
//        } catch (SQLException ex) {
//            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Error de conexión, verifica si el servidor local XAMPP está activado los módulos Apache y MySQL");
//        }
//        Class.forName(JDBC_DRIVER);
//    }
//    
//    public void cerrarConexion() throws SQLException {
//        if (conexion != null) {
//            if (!conexion.isClosed()) {
//                conexion.close();
//            }
//        }
//    }
    
    // Conexión Prototipo
    // Abrir Conexión
    public static Connection abrirConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASS);
        }
        return conexion;
    }
    
    // Cerrar Conexión
    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
