import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Clase Gestor
class Gestor extends Usuario {
    private List<Proyecto> proyectos;

    public Gestor(String nombre) {
        super(nombre, "Gestor");
        proyectos = new ArrayList<>();
    }

    public void crearProyecto(String nombre) {
        if(compararProyectos(nombre)){
            System.out.println("Este proyecto ya existe, elija otro nombre.");
        }
        else {
            Proyecto proyecto = new Proyecto(nombre);
            proyectos.add(proyecto);
            System.out.println("Proyecto creado: " + nombre);
        }
    }

    public void listarProyectos() {
        System.out.println("Proyectos gestionados:");
        for (Proyecto proyecto : proyectos) {
            System.out.println("- " + proyecto.getNombre());
        }
    }


    public void asignarProgramador(Proyecto proyecto, Programador programador) {
        proyecto.asignarProgramador(programador);
        System.out.println("Programador " + programador.getNombre() + " asignado al proyecto " + proyecto.getNombre());
    }

    public void crearTareaEnProyecto(Proyecto proyecto, String nombreTarea, Programador programador) {
        if(proyecto.compararTareas(nombreTarea)){
            System.out.println("Esta tarea ya existe en este proyecto, elija otro nombre.");
        }
        else{
            Tarea tarea = new Tarea(nombreTarea);
            proyecto.asignarTareaAProgramador(tarea, programador);
            System.out.println("Tarea " + nombreTarea + " asignada al programador " + programador.getNombre());
        }
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de gestor: Crear proyectos, asignar programadores, etc.");
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public boolean compararProyectos(String nombre) {
        for (Proyecto proyecto : proyectos) {
            if (Objects.equals(proyecto.getNombre(), nombre))
                return true;

        }
        return false;
    }
}
