package negocio;

import dominio.Usuario;

public interface IntNegocio {
    
    //Usuario
    public void iniciarSesion(String correo, String contraseña);
    public int crearCuenta (Usuario usuario);
}
