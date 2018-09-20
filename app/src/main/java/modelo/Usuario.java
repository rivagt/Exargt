package modelo;

public class Usuario {
    private Integer _id;
    private String nombre;
    private String clave;

    public Usuario() {
    }

    public Usuario(Integer _id, String nombre, String clave) {
        this._id = _id;
        this.nombre = nombre;
        this.clave = clave;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
