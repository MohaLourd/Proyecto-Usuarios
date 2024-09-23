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
