package dao;

import interfaces.DAOTrabajadores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.TrabajadoresModelo;

/**
 *
 * @author Erick Vergara Lopez <your.name at your.org>
 */
public class DAOTrabajadoresImpl extends Conexion implements DAOTrabajadores {

    @Override
    public void registrar(TrabajadoresModelo trabajador) throws Exception {
        try {
            // Referencia al metodo conectar de la clase herededada Conexion
            this.abrirConexion();
            // Trabajar con la BD
            // Hacemos referencia a la variable conexion inicializada en el metodo conectar
            // Variable de query sql
            String query = 
                    "INSERT INTO trabajadores(nombre, ocupacion)" +
                    "VALUES(?, ?);"
                    ;
            // en el método prepareStamente colocamos el comando sql
            // En values colocamos signos de interrogación del mismo numero de veces de columnas definimos
            PreparedStatement st = this.conexion.prepareStatement(query);
            // NOTA: Como el id es auto incrementable en la BD, no es necesario colocarlo, la BD lo genera
            // Establecer los parámetros en los signos de interrogacion
            st.setString(1, trabajador.getNombre());
            st.setString(2, trabajador.getOcupacion());
            // Ejecutar actualizacion
            st.executeUpdate();
            // Cerrar el statement
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void editar(TrabajadoresModelo trabajador) throws Exception {
        try {
            // Referencia al metodo conectar de la clase herededada Conexion
            this.abrirConexion();
            // Trabajar con la BD
            // Hacemos referencia a la variable conexion inicializada en el metodo conectar

            // en el método prepareStamente colocamos el comando sql
            // En values colocamos signos de interrogación del mismo numero de veces de columnas definimos
            PreparedStatement st = this.conexion.prepareStatement("UPDATE trabajadores SET nombre = ?, ocupacion = ? "
                    + "WHERE id_trabajador = ?;");
            // NOTA: Como el id es auto incrementable en la BD, no es necesario colocarlo, la BD lo genera
            // Establecer los parámetros en los signos de interrogacion
            st.setString(1, trabajador.getNombre());
            st.setString(2, trabajador.getOcupacion());
            st.setInt(3, trabajador.getIdTrabajador());
            // Ejecutar actualizacion
            st.executeUpdate();
            // Cerrar el statement
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void eliminar(int idTrabajador) throws Exception {
        try {
            this.abrirConexion();
            String query = "DELETE FROM trabajadores WHERE id_trabajador = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idTrabajador);
            st.executeUpdate();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public List<TrabajadoresModelo> listar(String nombreTrabajador) throws Exception {
        List<TrabajadoresModelo> lista = null;
        try {
            // Conectar a la BD
            this.abrirConexion();
            String query = nombreTrabajador.isEmpty() ? "SELECT * FROM trabajadores;" : "SELECT * FROM trabajadores"
                    + " WHERE nombre LIKE '%" + nombreTrabajador + "%';";
            PreparedStatement st = this.conexion.prepareStatement(query);
            // Inicializar lista como Arraylist
            lista = new ArrayList();
            // ResultSet de preparedStatement
            ResultSet rs = st.executeQuery();
            // Iterar registros de la BD
            while (rs.next()) {
                TrabajadoresModelo trabajador = new TrabajadoresModelo();
                // Establecer propiedades del ResultSet en todas las columnas de la tabla trabajador
                trabajador.setIdTrabajador(rs.getInt("id_trabajador"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setOcupacion(rs.getString("ocupacion"));
                // Agregar a lista
                lista.add(trabajador);
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
    public TrabajadoresModelo getTrabajadorById(int idTrabajador) throws Exception {
        TrabajadoresModelo trabajador = new TrabajadoresModelo();
        try {
            this.abrirConexion();
            String query = "SELECT * FROM trabajadores WHERE id_trabajador = ? LIMIT 1;";
            // st
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idTrabajador);
            // ResultSet de preparedStatement
            ResultSet rs = st.executeQuery();
            // Iterar registros de la BD
            while (rs.next()) {
                // Establecer propiedades del ResultSet en todas las columnas de la tabla trabajadores
                trabajador.setIdTrabajador(rs.getInt("id_trabajador"));
                trabajador.setNombre(rs.getString("nombre"));
                trabajador.setOcupacion(rs.getString("ocupacion"));
            }
            // Cerrar recursos
            rs.close();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
        return trabajador;
    }
    
}
