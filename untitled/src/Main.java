import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Creación del Administrador por defecto (no se puede crear ni eliminar)
        Administrador admin = new Administrador("Administrador");

        // Simulando algunos usuarios creados por el Administrador
        admin.crearUsuario("Julia", "Gestor");
        admin.crearUsuario("Victor", "Programador");
        admin.crearUsuario("Dennis", "Programador");
        //admin.crearUsuario("Administrador1","Administrador");
        Scanner scanner = new Scanner(System.in);

        // Solicita el nombre del usuario
        System.out.print("Ingresa tu nombre de usuario o 0 para salir: ");
        String nombreUsuario = scanner.nextLine();

        while(!nombreUsuario.equals("0"))
        {
            Usuario usuarioActual = buscarUsuario(admin, nombreUsuario);
            if (Objects.equals(nombreUsuario, "Administrador"))
            {
                usuarioActual= admin;
            }
            if (usuarioActual == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }

            System.out.println("Bienvenido, " + usuarioActual.getNombre() + " (" + usuarioActual.getRol() + ")");
            usuarioActual.mostrarOpciones();

            // Opciones para cada tipo de usuario según el rol
            if (usuarioActual instanceof Administrador) {
                manejarAdministrador((Administrador) usuarioActual, scanner);
            } else if (usuarioActual instanceof Gestor) {
                manejarGestor((Gestor) usuarioActual, admin, scanner);
            } else if (usuarioActual instanceof Programador) {
                manejarProgramador((Programador) usuarioActual, scanner);
            }
            // Solicita el nombre del usuario
            System.out.print("Ingresa tu nombre de usuario o 0 para salir: ");
            nombreUsuario = scanner.nextLine();
        }
        if(nombreUsuario.equals("0"))
        {
            scanner.close();
        }
    }

    // Método para buscar un usuario por su nombre
    private static Usuario buscarUsuario(Administrador admin, String nombre) {
        for (Usuario usuario : admin.getUsuarios()) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {
                return usuario;
            }
        }
        return null;
    }

    // Manejar las acciones de un Administrador
    private static void manejarAdministrador(Administrador admin, Scanner scanner) {
        while (true) {
            System.out.println("\nOpciones de Administrador:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del nuevo usuario: ");
                    String nombreNuevo = scanner.nextLine();
                    System.out.print("Rol (Gestor o Programador): ");
                    String rolNuevo = scanner.nextLine().trim();
                    if (!rolNuevo.equalsIgnoreCase("Gestor") && !rolNuevo.equalsIgnoreCase("Programador")) {
                        System.out.println("Rol no válido. Intenta de nuevo.");
                        continue; // Para volver a pedir la entrada
                    }
                    admin.crearUsuario(nombreNuevo, rolNuevo);
                    break;
                case 2:
                    System.out.print("Nombre del usuario a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    admin.eliminarUsuario(nombreEliminar);
                    break;
                case 3:
                    admin.listarUsuarios();
                    break;
                case 4:
                    return; // Salir del menú de administrador
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Manejar las acciones de un Gestor
    private static void manejarGestor(Gestor gestor, Administrador admin, Scanner scanner) {
        while (true) {
            System.out.println("\nOpciones de Gestor:");
            System.out.println("1. Crear proyecto");
            System.out.println("2. Listar mis proyectos");
            System.out.println("3. Listar programadores");
            System.out.println("4. Asignar programador a proyecto");
            System.out.println("5. Crear tarea y asignar a programador");
            System.out.println("6. Ver programadores asignados a un proyecto");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyecto = scanner.nextLine();
                    gestor.crearProyecto(nombreProyecto);
                    break;
                case 2:
                    gestor.listarProyectos();
                    break;
                case 3:
                    admin.listarProgramadores(); // Asumiendo que los programadores ya fueron creados por el Admin
                    break;
                case 4:
                    System.out.print("Nombre del proyecto para asignar: ");
                    String nombreProyectoAsignar = scanner.nextLine();
                    Proyecto proyecto = buscarProyecto(gestor, nombreProyectoAsignar);
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre del programador: ");
                    String nombreProgramador = scanner.nextLine();
                    Usuario usuario = buscarUsuario(admin, nombreProgramador);
                    if (usuario instanceof Programador) {
                        gestor.asignarProgramador(proyecto, (Programador) usuario);
                    } else {
                        System.out.println("Programador no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Nombre del proyecto: ");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyecto(gestor, nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre de la tarea: ");
                    String nombreTarea = scanner.nextLine();
                    System.out.print("Nombre del programador: ");
                    nombreProgramador = scanner.nextLine();
                    usuario = buscarUsuario(admin, nombreProgramador);
                    if (usuario instanceof Programador) {
                        gestor.crearTareaEnProyecto(proyecto, nombreTarea, (Programador) usuario);
                    } else {
                        System.out.println("Programador no encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Nombre del proyecto para ver programadores asignados: ");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyecto(gestor, nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    proyecto.listarProgramadoresAsignados();
                    break;
                case 7:
                    return; // Salir del menú de gestor
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    // Manejar las acciones de un Programador
    private static void manejarProgramador(Programador programador, Scanner scanner) {
        while (true) {
            System.out.println("\nOpciones de Programador:");
            System.out.println("1. Consultar proyectos asignados");
            System.out.println("2. Consultar tareas asignadas en un proyecto");
            System.out.println("3. Marcar tarea como finalizada");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
                case 1:
                    programador.listarProyectos();
                    break;
                case 2:
                    System.out.print("Nombre del proyecto: ");
                    String nombreProyecto = scanner.nextLine();
                    Proyecto proyecto = buscarProyectoProgramador(programador, nombreProyecto);
                    if (proyecto != null) {
                        programador.listarTareas(proyecto);
                    } else {
                        System.out.println("Proyecto no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Nombre del proyecto: ");
                    nombreProyecto = scanner.nextLine();
                    proyecto = buscarProyectoProgramador(programador, nombreProyecto);
                    if (proyecto == null) {
                        System.out.println("Proyecto no encontrado.");
                        break;
                    }
                    System.out.print("Nombre de la tarea: ");
                    String nombreTarea = scanner.nextLine();
                    Tarea tarea = buscarTarea(proyecto, nombreTarea, programador);
                    if (tarea != null) {
                        programador.marcarTareaComoFinalizada(tarea);
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                    break;
                case 4:
                    return; // Salir del menú de programador
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Buscar proyecto por nombre en el gestor
    private static Proyecto buscarProyecto(Gestor gestor, String nombreProyecto) {
        return gestor.getProyectos()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombreProyecto))
                .findFirst()
                .orElse(null);
    }

    // Buscar proyecto por nombre en el programador
    private static Proyecto buscarProyectoProgramador(Programador programador, String nombreProyecto) {
        return programador.getProyectosAsignados()
                .stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombreProyecto))
                .findFirst()
                .orElse(null);
    }

    // Buscar tarea en un proyecto por nombre
    private static Tarea buscarTarea(Proyecto proyecto, String nombreTarea, Programador programador) {
        return proyecto.getTareasPorProgramador(programador)
                .stream()
                .filter(t -> t.getNombre().equalsIgnoreCase(nombreTarea))
                .findFirst()
                .orElse(null);
    }
}

// Usuario abstracto
abstract class Usuario {
    private String nombre;
    private String rol;

    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    // Método abstracto que definirá acciones específicas por tipo de usuario
    public abstract void mostrarOpciones();
}

// Clase Administrador
class Administrador extends Usuario {
    private List<Usuario> usuarios;

    public Administrador(String nombre) {
        super(nombre, "Administrador");
        usuarios = new ArrayList<>();
    }

    public void crearUsuario(String nombre, String rol) {
        Usuario nuevoUsuario = null;
        if (rol.equalsIgnoreCase("Gestor")) {
            nuevoUsuario = new Gestor(nombre);
        } else if (rol.equalsIgnoreCase("Programador")) {
            nuevoUsuario = new Programador(nombre);
        }else if (rol.equalsIgnoreCase("Administrador")) {
            nuevoUsuario = new Administrador(nombre);
        }

        if (nuevoUsuario != null) {
            usuarios.add(nuevoUsuario);
            System.out.println("Usuario " + rol + " creado: " + nombre);
        }
    }

    public void listarProgramadores() {
        System.out.println("Lista de programadores:");
        for (Usuario usuario : usuarios) {
            if (usuario.getRol().equals("Programador")) {
                System.out.println("- " + usuario.getNombre() + " (" + usuario.getRol() + ")");
            }
        }
    }

    public void eliminarUsuario(String nombre) {
        usuarios.removeIf(usuario -> usuario.getNombre().equalsIgnoreCase(nombre));
        System.out.println("Usuario eliminado: " + nombre);
    }

    public void listarUsuarios() {
        System.out.println("Lista de usuarios:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNombre() + " (" + usuario.getRol() + ")");
        }
    }


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de administrador: Crear, eliminar usuarios, etc.");
    }
}

// Clase Gestor
class Gestor extends Usuario {
    private List<Proyecto> proyectos;

    public Gestor(String nombre) {
        super(nombre, "Gestor");
        proyectos = new ArrayList<>();
    }

    public void crearProyecto(String nombre) {
        Proyecto proyecto = new Proyecto(nombre);
        proyectos.add(proyecto);
        System.out.println("Proyecto creado: " + nombre);
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
        Tarea tarea = new Tarea(nombreTarea);
        proyecto.asignarTareaAProgramador(tarea, programador);
        System.out.println("Tarea " + nombreTarea + " asignada al programador " + programador.getNombre());
    }

    @Override
    public void mostrarOpciones() {
        System.out.println("Opciones de gestor: Crear proyectos, asignar programadores, etc.");
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }
}

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
            if(!tarea.isFinalizada()) {
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

// Clase Tarea
class Tarea {
    private String nombre;
    private boolean finalizada;

    public Tarea(String nombre) {
        this.nombre = nombre;
        this.finalizada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada() {
        this.finalizada = true;
    }
}