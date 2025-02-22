package interfaces;

import modelo.RegistroAccesoModelo;
import java.util.List;
import modelo.EstudiantesModelo;
import modelo.TrabajadoresModelo;

public interface DAORegistroAcceso {
    public void registrar(RegistroAccesoModelo registro) throws Exception;
    public void modificar(RegistroAccesoModelo registro) throws Exception;
    // Obtener registro
    public RegistroAccesoModelo getRegistro(EstudiantesModelo estudiante, TrabajadoresModelo trabajador) throws Exception;
    // public void eliminar(RegistroAcceso registro) throws Exception;
    public List<RegistroAccesoModelo> listar() throws Exception;
}
