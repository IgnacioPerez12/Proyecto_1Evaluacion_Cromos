package negocio;

import dominio.Usuario;

public interface IntPerfilNegocio {
 
    void Crear(String nombreArchivo);
    
    //ADMIN
    void Listar (String nombreArchivo);
    void Buscar(String nombreArchivo, String buscar);
    
    //Cliente
    void EncapsularById (String nombreArchivo, int buscar, String campo, Usuario checkLogin);
    void findById(String nombreArchivo, int buscar);
    void Escribir(String nombreArchivo, int id_usuario, String nombre, String apellidos, String correo, String psw, String ciudad, String equipo, String estado);
    void Borrar(String nombreArchivo, int buscar);
    public void Modificar(String nombreArchivo, String campo, int id_usuario);
}
