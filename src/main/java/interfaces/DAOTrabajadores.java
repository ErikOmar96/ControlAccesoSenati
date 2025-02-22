package interfaces;

import modelo.TrabajadoresModelo;
import java.util.List;

public interface DAOTrabajadores {
    // Registrar
    public void registrar(TrabajadoresModelo trabajador) throws Exception;
    // Editar
    public void editar(TrabajadoresModelo trabajador) throws Exception;
    // Eliminar
    public void eliminar(int idTrabajador) throws Exception;
    // Listar trabajadores
    public List<TrabajadoresModelo> listar(String nombreTrabajador) throws Exception;
    // Obtener un trabajador en específico
    public TrabajadoresModelo getTrabajadorById(int idTrabajador) throws Exception;
}
