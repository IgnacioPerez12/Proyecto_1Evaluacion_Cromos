package datos;

import dominio.UsuarioPerfil;
import excepciones.*;
import java.util.List;

public interface IntPerfilDatos {
    
    boolean Existe(String nombreArchivo) throws AccesoDatosEx; // Comprobar si existe el archivo
    void Crear (String nombreArchivo) throws AccesoDatosEx; // Crear el archivo
    
    // ¿ ADMIN ?
    List <UsuarioPerfil> Listar (String nombreArchivo) throws LecturaEx; // Listar todo el archivo
    String Buscar (String nombreArchivo, String buscar) throws LecturaEx; // Buscar mediante text
    
    // ¿ CLIENTE ?
    List <UsuarioPerfil> EncapsularById (String nombreArchivo, int buscar, String campo) throws LecturaEx; // Listar un perfilde un usuario mediante ID
    String findById (String nombreArchivo, int buscar) throws LecturaEx; // Listar un perfilde un usuario mediante ID
    void Escribir (UsuarioPerfil usuarioPerfil, String nombreArchivo, boolean anexar) throws EscrituraEx; // Escribir el perfil por primera ver
    void Borrar (String nombreArchivo, int buscar); // Borrar una linea especifica; Para si el usu quiere eliminar su perfil
    public void Modificar (String nombreArchivo, String campo, int id_usuario) throws EscrituraEx; //Modificar usuario por campos
}