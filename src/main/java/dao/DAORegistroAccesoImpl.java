package dao;

import interfaces.DAORegistroAcceso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.EstudiantesModelo;
import modelo.RegistroAccesoModelo;
import modelo.TrabajadoresModelo;

public class DAORegistroAccesoImpl extends Conexion implements DAORegistroAcceso {

    @Override
    public void registrar(RegistroAccesoModelo registro) throws Exception {
        try {
            this.abrirConexion();

            // Modificar la sentencia SQL para insertar el registro con las nuevas columnas
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO registro_acceso(id_estudiante, id_trabajador, nombre_persona, fecha_hora_ingreso) VALUES (?, ?, ?, ?);");

            // Modificar la asignación de parámetros para reflejar las nuevas columnas
            st.setInt(1, registro.getIdEstudiante());
            st.setInt(2, registro.getIdTrabajador());
            st.setString(3, registro.getNombrePersona());
            st.setString(4, registro.getFechaHoraIngreso());

            st.executeUpdate();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public void modificar(RegistroAccesoModelo registro) throws Exception {
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE registro_acceso SET id_persona = ?, nombre_persona = ?, fecha_hora_ingreso = ?, fecha_hora_salida = ? WHERE id_registro = ?;");
            st.setInt(1, registro.getIdEstudiante());
            st.setString(2, registro.getNombrePersona());
            st.setString(3, registro.getFechaHoraIngreso());
            st.setString(4, registro.getFechaHoraSalida());
            st.setInt(5, registro.getIdRegistro());
            st.executeUpdate();
            st.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
    }

    @Override
    public List<RegistroAccesoModelo> listar() throws Exception {
        List<RegistroAccesoModelo> listaRegistros = null;
    try {
        this.abrirConexion();
        PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM registro_acceso ORDER BY id_registro DESC;");
        listaRegistros = new ArrayList<>();
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            RegistroAccesoModelo registro = new RegistroAccesoModelo();
            registro.setIdRegistro(rs.getInt("id_registro"));
            
            // Obtener id_persona y determinar si es estudiante o trabajador
            int idPersona = rs.getInt("id_persona");
            if (esEstudiante(idPersona)) {
                // Es un estudiante
                registro.setIdEstudiante(idPersona);
            } else {
                // Es un trabajador
                registro.setIdTrabajador(idPersona);
            }

            registro.setNombrePersona(rs.getString("nombre_persona"));
            registro.setFechaHoraIngreso(rs.getString("fecha_hora_ingreso"));
            registro.setFechaHoraSalida(rs.getString("fecha_hora_salida"));
            listaRegistros.add(registro);
        }
        rs.close();
        st.close();
    } catch (Exception ex) {
        throw ex;
    } finally {
        this.cerrarConexion();
    }
    return listaRegistros;
    }

    @Override
    public RegistroAccesoModelo getRegistro(EstudiantesModelo estudiante, TrabajadoresModelo trabajador) throws Exception {
        RegistroAccesoModelo registro = null;
        try {
            this.abrirConexion();
            PreparedStatement st = this.conexion.prepareStatement("SELECT * FROM registro_acceso WHERE id_estudiante = ? AND id_trabajador = ? AND fecha_hora_salida IS NULL ORDER BY id_registro DESC LIMIT 1;");

            // Modificar la asignación de parámetros para reflejar las nuevas columnas
            st.setInt(1, estudiante.getIdEstudiante());
            st.setInt(2, trabajador.getIdTrabajador());

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                registro = new RegistroAccesoModelo();
                registro.setIdRegistro(rs.getInt("id_registro"));
                registro.setIdEstudiante(rs.getInt("id_estudiante"));
                registro.setIdTrabajador(rs.getInt("id_trabajador"));
                registro.setNombrePersona(rs.getString("nombre_persona"));
                registro.setFechaHoraIngreso(rs.getString("fecha_hora_ingreso"));
                registro.setFechaHoraSalida(rs.getString("fecha_hora_salida"));
            }
            st.close();
            rs.close();
        } catch (Exception ex) {
            throw ex;
        } finally {
            this.cerrarConexion();
        }
        return registro;
    }

    private boolean esEstudiante(int idPersona) {
        return idPersona <= 100;
    }

}
