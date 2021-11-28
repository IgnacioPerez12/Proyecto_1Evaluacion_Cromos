package dominio;

public class Carta {
    
    private int id_carta;
    private String nombreJugador;
    private String equipo;
    private String posicion;
    private int media;
    private int ataque;
    private int defensa;
    private String edicion;
    
    //Constructor
    public Carta() {
        
    }

    public Carta(int id_carta) {
        this.id_carta = id_carta;
    }
    
    public Carta(String nombreJugador, String equipo, String posicion, int media, int ataque, int defensa, String edicion) {
        this.nombreJugador = nombreJugador;
        this.equipo = equipo;
        this.posicion = posicion;
        this.media = media;
        this.ataque = ataque;
        this.defensa = defensa;
        this.edicion = edicion;
    }

    public Carta(int id_carta, String nombreJugador, String equipo, String posicion, int media, int ataque, int defensa, String edicion) {
        this.id_carta = id_carta;
        this.nombreJugador = nombreJugador;
        this.equipo = equipo;
        this.posicion = posicion;
        this.media = media;
        this.ataque = ataque;
        this.defensa = defensa;
        this.edicion = edicion;
    }
    
    //Getter and Setter
    public int getId_carta() {
        return id_carta;
    }

    public void setId_carta(int id_carta) {
        this.id_carta = id_carta;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }
    
    //ToString
    @Override
    public String toString() {
        return "Carta{" + "id_carta=" + id_carta + ", nombreJugador=" + nombreJugador + ", equipo=" + equipo + ", posicion=" + posicion + ", media=" + media + ", ataque=" + ataque + ", defensa=" + defensa + ", edicion=" + edicion + '}';
    }
    
}