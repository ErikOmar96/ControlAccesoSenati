package interfaces;

import java.util.List;
import modelo.EstudiantesModelo;

/* Aquí se va crear los métodos CRUD que va trabajada con la base de datos*/

public interface DAOEstudiantes {
    
    // Registrar
    public void registrar(EstudiantesModelo estudiante) throws Exception;
    // Editar
    public void editar(EstudiantesModelo estudiante) throws Exception;
    // Eliminar
    public void eliminar(int idEstudiante) throws Exception;
    // Lista de estudiantes
    public List<EstudiantesModelo> listar(String nombreEstudiante) throws Exception;
    // Obtener un estudiante en específico
    public EstudiantesModelo getEstudianteById(int idEstudiante) throws Exception;
    
}
