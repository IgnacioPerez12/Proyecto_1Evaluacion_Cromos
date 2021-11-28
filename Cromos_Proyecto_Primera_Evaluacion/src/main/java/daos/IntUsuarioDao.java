package daos;

import dominio.Usuario;
import excepciones.*;
import java.sql.*;
import java.util.List;

public interface IntUsuarioDao {
    
    public Usuario findByEmail (String emai) throws AccesoDatosEx;   //Buscar por correo (Iniciar sesion
    public int crearCuenta (Usuario usuario) throws EscrituraEx;   //Crear usuario
    public List<Usuario> listarUsuario() throws LecturaEx;   //Listar todos los usuarios
    public int modificar(Usuario usuario) throws SQLException;  //Modificar un usuario
    public int borrar(Usuario usuario) throws SQLException; //Eliminar un usuarios
    public List<Usuario> findById (int id_usuario) throws LecturaEx; //Buscamos el usu por id para sacar el saldo
}
