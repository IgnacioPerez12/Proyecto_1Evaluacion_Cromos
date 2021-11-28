package dominio;

public class CartaUsuario {

    private int id_carta_en_usuario;
    private int id_usuario;
    private int id_carta;
    
    //Constructor
    public CartaUsuario() {
    }

    public CartaUsuario(int id_usuario, int id_carta) {
        this.id_usuario = id_usuario;
        this.id_carta = id_carta;
    }

    public CartaUsuario(int id_carta_en_usuario, int id_usuario, int id_carta) {
        this.id_carta_en_usuario = id_carta_en_usuario;
        this.id_usuario = id_usuario;
        this.id_carta = id_carta;
    }
    
    //Getter and Setter
    public int getId_carta_en_usuario() {
        return id_carta_en_usuario;
    }

    public void setId_carta_en_usuario(int id_carta_en_usuario) {
        this.id_carta_en_usuario = id_carta_en_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_carta() {
        return id_carta;
    }

    public void setId_carta(int id_carta) {
        this.id_carta = id_carta;
    }
    
    //ToString
    @Override
    public String toString() {
        return "CartaUsuario{" + "id_carta_en_usuario=" + id_carta_en_usuario + ", id_usuario=" + id_usuario + ", id_carta=" + id_carta + '}';
    }
    
}
