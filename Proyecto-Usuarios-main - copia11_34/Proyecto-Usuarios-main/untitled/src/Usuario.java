// Usuario abstracto
abstract class Usuario {
    private String nombre;
    private String rol;

    public Usuario(String nombre, String rol) {
        // Convertimos el nombre a minúsculas para tratar siempre igual
        this.nombre = nombre.toLowerCase();
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