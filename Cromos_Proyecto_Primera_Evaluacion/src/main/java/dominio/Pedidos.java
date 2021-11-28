package dominio;

import java.util.Date;

public class Pedidos {
    private int id_pedido;
    private int id_usuario;
    private int id_sobres;
    private int cantidad;
    private Date fecha_alta;
    
    //Constructor
    public Pedidos() {
    
    }

    public Pedidos(int id_usuario, int id_sobres, int cantidad, Date fecha_alta) {
        this.id_usuario = id_usuario;
        this.id_sobres = id_sobres;
        this.cantidad = cantidad;
        this.fecha_alta = fecha_alta;
    }

    public Pedidos(int id_usuario, int id_sobres, int cantidad) {
        this.id_usuario = id_usuario;
        this.id_sobres = id_sobres;
        this.cantidad = cantidad;
    }

    public Pedidos(int id_pedido, int id_usuario, int id_sobres, int cantidad, Date fecha_alta) {
        this.id_pedido = id_pedido;
        this.id_usuario = id_usuario;
        this.id_sobres = id_sobres;
        this.cantidad = cantidad;
        this.fecha_alta = fecha_alta;
    }

    //Getter and Setter
    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
    
    //ToString
    @Override
    public String toString() {
        return "Pedidos{" + "id_pedido=" + id_pedido + ", id_usuario=" + id_usuario + ", id_sobres=" + id_sobres + ", cantidad=" + cantidad + ", fecha_alta=" + fecha_alta + '}';
    }
    
}
