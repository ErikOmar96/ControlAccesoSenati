package dao;

import interfaces.DAOEstudiantes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.EstudiantesModelo;

// 
public class DAOEstudiantesImpl extends Conexion implements DAOEstudiantes {

    // Sobreescritura de metodos de la interfaz
    @Override
    public void registrar(EstudiantesModelo estudiante) throws Exception {
        try {
            // Referencia al metodo conectar de la clase herededada Conexion
            this.abrirConexion();
            // Trabajar con la BD
            // Hacemos referencia a la variable conexion inicializada en el metodo conectar
            // Variable de query sql
            String query = 
                    "INSERT INTO estudiantes(nombre, carrera, semestre)" +
                    "VALUES(?, ?, ?);"
                    ;
            // en el método prepareStamente colocamos el comando sql
            // En values colocamos signos de interrogación del mismo numero de veces de columnas definimos
            PreparedStatement st = this.conexion.prepareStatement(query);
            // NOTA: Como el id es auto incrementable en la BD, no es necesario colocarlo, la BD lo genera
            // Establecer los parámetros en los signos de interrogacion
            st.setString(1, estudiante.getNombre());
            st.setString(2, estudiante.getCarrera());
            st.setInt(3, estudiante.getSemestre());
            // Ejecutar actualizacion
            st.executeUpdate();
            // Cerrar el statement
            st.close();
        } catch(Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void editar(EstudiantesModelo estudiante) throws Exception {
        try {
            // Referencia al metodo conectar de la clase herededada Conexion
            this.abrirConexion();
            // Trabajar con la BD
            // Hacemos referencia a la variable conexion inicializada en el metodo conectar

            // en el método prepareStamente colocamos el comando sql
            // En values colocamos signos de interrogación del mismo numero de veces de columnas definimos
            PreparedStatement st = this.conexion.prepareStatement("UPDATE estudiantes SET nombre = ?, carrera = ?, semestre = ? "
                    + "WHERE id_estudiante = ?;");
            // NOTA: Como el id es auto incrementable en la BD, no es necesario colocarlo, la BD lo genera
            // Establecer los parámetros en los signos de interrogacion
            st.setString(1, estudiante.getNombre());
            st.setString(2, estudiante.getCarrera());
            st.setInt(3, estudiante.getSemestre());
            st.setInt(4, estudiante.getIdEstudiante());
            // Ejecutar actualizacion
            st.executeUpdate();
            // Cerrar el statement
            st.close();
        } catch(Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(int idEstudiante) throws Exception {
        try {
            this.abrirConexion();
            String query = 
                    "DELETE FROM estudiantes " +
                    "WHERE id_estudiante = ?;"
                    ;
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idEstudiante);
            st.executeUpdate();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public List<EstudiantesModelo> listar(String nombreEstudiante) throws Exception {
        List<EstudiantesModelo> lista = null;
        try {
            // Conectar a la BD
            this.abrirConexion();
            String query = nombreEstudiante.isEmpty() ? "SELECT * FROM estudiantes;" : "SELECT * FROM estudiantes"
                    + " WHERE nombre LIKE '%" + nombreEstudiante + "%';";
            PreparedStatement st = this.conexion.prepareStatement(query);
            // Inicializar lista como Arraylist
            lista = new ArrayList();
            // ResultSet de preparedStatement
            ResultSet rs = st.executeQuery();
            // Iterar registros de la BD
            while (rs.next()) {
                EstudiantesModelo estudiante = new EstudiantesModelo();
                // Establecer propiedades del ResultSet en todas las columnas de la tabla estudiantes
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setCarrera(rs.getString("carrera"));
                estudiante.setSemestre(rs.getInt("semestre"));
                // Agregar a lista
                lista.add(estudiante);
            }
            // Cerrar recursos
            rs.close();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
        return lista;
    }

    @Override
    public EstudiantesModelo getEstudianteById(int idEstudiante) throws Exception {
        EstudiantesModelo estudiante = new EstudiantesModelo();
        try {
            // Conectar a la BD
            this.abrirConexion();
            // Query SQL
            String query = 
                    "SELECT * " +
                    "FROM estudiantes " +
                    "WHERE id_estudiante = ? LIMIT 1;"
                    ;
            // statement
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idEstudiante);
            // ResultSet de preparedStatement
            ResultSet rs = st.executeQuery();
            // Iterar registros de la BD
            while (rs.next()) {
                // Establecer propiedades del ResultSet en todas las columnas de la tabla estudiantes
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setCarrera(rs.getString("carrera"));
                estudiante.setSemestre(rs.getInt("semestre"));
            }
            // Cerrar recursos
            rs.close();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
        return estudiante;
    }
    
}
