import java.util.ArrayList;
import java.util.List;

// Clase Proyecto
class Proyecto {
    private String nombre;
    private List<Tarea> tareas;
    private List<Programador> programadoresAsignados;

    public Proyecto(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
        this.programadoresAsignados = new ArrayList<>();
    }

    public List<Programador> getProgramadoresAsignados() {
        return programadoresAsignados;
    }

    public void listarProgramadoresAsignados() {
        if (programadoresAsignados.isEmpty()) {
            System.out.println("No hay programadores asignados a este proyecto.");
        } else {
            System.out.println("Programadores asignados al proyecto " + nombre + ":");
            for (Programador programador : programadoresAsignados) {
                System.out.println("- " + programador.getNombre());
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void asignarProgramador(Programador programador) {
        programadoresAsignados.add(programador);
        programador.getProyectosAsignados().add(this);
    }

    public void asignarTareaAProgramador(Tarea tarea, Programador programador) {
        tareas.add(tarea);
        programador.getProyectosAsignados().add(this);
    }

    public List<Tarea> getTareasPorProgramador(Programador programador) {
        return tareas;
    }
}
