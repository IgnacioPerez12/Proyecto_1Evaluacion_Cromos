package dominio;

public class SobreUsuario {

    private int id_sobre_en_usuario;
    private int id_usuario;
    private int id_sobres;
    private int cantidad;
    
    //Constructores
    public SobreUsuario() {
    }

    public SobreUsuario(int id_usuario, int id_sobres, int cantidad) {
        this.id_usuario = id_usuario;
        this.id_sobres = id_sobres;
        this.cantidad = cantidad;
    }
        
    public SobreUsuario(int id_sobre_en_usuario, int id_usuario, int id_sobres, int cantidad) {
        this.id_sobre_en_usuario = id_sobre_en_usuario;
        this.id_usuario = id_usuario;
        this.id_sobres = id_sobres;
        this.cantidad = cantidad;
    }

    //Getter and Setter
    public int getId_sobre_en_usuario() {
        return id_sobre_en_usuario;
    }

    public void setId_sobre_en_usuario(int id_sobre_en_usuario) {
        this.id_sobre_en_usuario = id_sobre_en_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_sobres() {
        return id_sobres;
    }

    public void setId_sobres(int id_sobres) {
        this.id_sobres = id_sobres;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    //ToString
    @Override
    public String toString() {
        return "SobreUsuario{" + "id_sobre_en_usuario=" + id_sobre_en_usuario + ", id_usuario=" + id_usuario + ", id_sobres=" + id_sobres + ", cantidad=" + cantidad + '}';
    }
    
}
