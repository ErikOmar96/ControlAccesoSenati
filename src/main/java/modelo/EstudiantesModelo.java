package modelo;

public class EstudiantesModelo {
    
    // Atributos en base a la tabla estudiantes
    private int idEstudiante;
    private String nombre;
    private String carrera;
    private int semestre;
    
    // Getters and Setters

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    
}
