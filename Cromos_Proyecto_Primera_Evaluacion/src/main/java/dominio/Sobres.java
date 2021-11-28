package dominio;

public class Sobres {

    private int id_sobres;
    private int cantidadCartas;
    private String nombreSobre;
    private double precio;
    
    //Constructores
    public Sobres() {
    
    }

    public Sobres(int id_sobres) {
        this.id_sobres = id_sobres;
    }
    
    public Sobres(int cantidadCartas, String nombreSobre, double precio) {
        this.cantidadCartas = cantidadCartas;
        this.nombreSobre = nombreSobre;
        this.precio = precio;
    }

    public Sobres(int id_sobres, int cantidadCartas, String nombreSobre, double precio) {
        this.id_sobres = id_sobres;
        this.cantidadCartas = cantidadCartas;
        this.nombreSobre = nombreSobre;
        this.precio = precio;
    }
    
    //Getter and Setter
    public int getId_sobres() {
        return id_sobres;
    }

    public void setId_sobres(int id_sobres) {
        this.id_sobres = id_sobres;
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    public void setCantidadCartas(int cantidadCartas) {
        this.cantidadCartas = cantidadCartas;
    }

    public String getNombreSobre() {
        return nombreSobre;
    }

    public void setNombreSobre(String nombreSobre) {
        this.nombreSobre = nombreSobre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    //ToString
    @Override
    public String toString() {
        return "Sobres{" + "id_sobres=" + id_sobres + ", cantidadCartas=" + cantidadCartas + ", nombreSobre=" + nombreSobre + ", precio=" + precio + '}';
    }
    
}
