package dominio;

public class Usuario {
    
    private int id_usuario;
    private String email;
    private String password;
    private double saldo;
    private boolean administrador;
    
    //Contructores
    public Usuario() {
        
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public Usuario(String email, String password, double saldo, boolean administrador) {
        this.email = email;
        this.password = password;
        this.saldo = saldo;
        this.administrador = administrador;
    }

    public Usuario(int id_usuario, String email, String password, double saldo, boolean administrador) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
        this.administrador = administrador;
    }
    
    //Getter and Setter
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    //ToString
    @Override
    public String toString() {
        return "Usuario{" + "id_usuario=" + id_usuario + ", email=" + email + ", password=" + password + ", saldo=" + saldo + ", administrador=" + administrador + '}';
    }
    
    
}
