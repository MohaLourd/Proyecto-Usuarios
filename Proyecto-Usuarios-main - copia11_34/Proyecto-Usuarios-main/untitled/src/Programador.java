import java.util.ArrayList;
import java.util.List;

// Clase Programador
class Programador extends Usuario {
    private List<Proyecto> proyectosAsignados;

    public Programador(String nombre) {
        super(nombre, "Programador");
        proyectosAsignados = new ArrayList<>();
    }

    public void listarProyectos() {
        System.out.println("Proyectos asignados:");
        for (Proyecto proyecto : proyectosAsignados) {
            System.out.println("- " + proyecto.getNombre());
        }
    }

    public void listarTareas(Proyecto proyecto) {
        System.out.println("Tareas asignadas en el proyecto " + proyecto.getNombre() + ":");
        for (Tarea tarea : proyecto.getTareasPorProgramador(this)) {
            if (!tarea.isFinalizada()) {
                System.out.println("- " + tarea.getNombre());
            }
        }
    }

    public void marcarTareaComoFinalizada(Tarea tarea) {
        tarea.setFinalizada();
        System.out.println("Tarea " + tarea.getNombre() + " marcada como finalizada.");
    }

    public List<Proyecto> getProyectosAsignados() {
        return proyectosAsignados;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de programador: Consultar proyectos, marcar tareas como finalizadas, etc.");
    }
}
