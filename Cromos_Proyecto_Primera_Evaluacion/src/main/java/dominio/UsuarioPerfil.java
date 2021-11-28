package dominio;

public class UsuarioPerfil {

    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String psw;
    private String ciudad;
    private String equipo;
    private String estado;
    
    //Constructores
    public UsuarioPerfil() {
    }

    public UsuarioPerfil(String nombre, String apellidos, String correo, String psw, String ciudad, String equipo, String estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.psw = psw;
        this.ciudad = ciudad;
        this.equipo = equipo;
        this.estado = estado;
    }

    public UsuarioPerfil(int id_usuario, String nombre, String apellidos, String correo, String psw, String ciudad, String equipo, String estado) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.psw = psw;
        this.ciudad = ciudad;
        this.equipo = equipo;
        this.estado = estado;
    }
    
    //Getter and Setter
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    //ToString
    @Override
    public String toString() {
        return "UsuarioPerfil{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", psw=" + psw + ", ciudad=" + ciudad + ", equipo=" + equipo + ", estado=" + estado + '}';
    }
    
    
}
