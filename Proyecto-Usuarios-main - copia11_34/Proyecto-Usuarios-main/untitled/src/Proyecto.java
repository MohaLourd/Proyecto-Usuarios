import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        // Verificamos que el programador no esté ya asignado y que exista
        if (!programadoresAsignados.contains(programador)) {
            programadoresAsignados.add(programador);
            programador.getProyectosAsignados().add(this);
            System.out.println("Programador " + programador.getNombre() + " asignado al proyecto " + nombre);
        } else {
            System.out.println("El programador " + programador.getNombre() + " ya está asignado a este proyecto.");
        }
    }

    public void asignarTareaAProgramador(Tarea tarea, Programador programador) {
        // Verificamos si el programador está asignado al proyecto
        if (programadoresAsignados.contains(programador)) {
            tareas.add(tarea);
            System.out.println("Tarea asignada a " + programador.getNombre() + " en el proyecto " + nombre);
        } else {
            System.out.println("Error: El programador " + programador.getNombre() + " no está asignado a este proyecto.");
        }
    }

    public List<Tarea> getTareasPorProgramador(Programador programador) {
        return tareas;
    }

    public boolean compararTareas(String nombre) {
            for (Tarea tarea : tareas) {
                if (Objects.equals(tarea.getNombre(), nombre))
                    return true;
            }

        return false;
    }
}
