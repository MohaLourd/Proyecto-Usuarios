import java.util.ArrayList;
import java.util.List;

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
        } else if (rol.equalsIgnoreCase("Administrador")) {
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
