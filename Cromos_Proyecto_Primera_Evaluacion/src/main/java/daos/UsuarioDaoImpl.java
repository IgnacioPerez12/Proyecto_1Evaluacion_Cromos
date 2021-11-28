package daos;

import static datos.Conexion.*;
import dominio.Usuario;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDaoImpl implements IntUsuarioDao{

    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;


    //Trasanciones
    private Connection connTransacional;
    public UsuarioDaoImpl() {
        
    }

    public UsuarioDaoImpl(Connection connTransacional) {
        this.connTransacional = connTransacional;
    }
    
    @Override
    public Usuario findByEmail(String email){
        Usuario usu = null;
        sql = "SELECT * FROM usuario WHERE email = ?;";
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                String correo = rs.getString("email");
                String psw = rs.getString("psw");
                double saldo = rs.getDouble("saldo");
                boolean administrador = rs.getBoolean("administrador");
                
                usu = new Usuario(id_usuario, email, psw, saldo, administrador);
            }
            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            try {
                conn.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
            
        }
        return usu;
    }

    @Override
    public int crearCuenta(Usuario usuario) {
        
        sql = "INSERT INTO usuario (email, psw, saldo, administrador) VALUES (?, ?, ?, false);";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setDouble(3, usuario.getSaldo());
            
            registros = ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public List<Usuario> listarUsuario() {
        sql = "SELECT * FROM usuario";
        List<Usuario> usuario = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int idpersona = rs.getInt("id_usuario");
                String email = rs.getString("email");
                String psw = rs.getString("psw");
                double saldo = rs.getDouble("saldo");
                boolean administrador = rs.getBoolean("administrador");
                
                usuario.add(new Usuario(idpersona, email, psw, saldo, administrador));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
                close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return usuario;
        
        /*
        sql = "SELECT * FROM usuario";
        List<Usuario> usuario = new ArrayList<>();
        
        try {
            conn = this.connTransacional != null ? this.connTransacional : getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int idpersona = rs.getInt("id_usuario");
                String email = rs.getString("email");
                String psw = rs.getString("psw");
                double saldo = rs.getDouble("saldo");
                boolean administrador = rs.getBoolean("administrador");
                
                usuario.add(new Usuario());
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                if (this.connTransacional == null){
                    close(conn);
                }
                close(ps);
                close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return usuario;
        */
    }

    @Override
    public int modificar(Usuario usuario) throws SQLException{
        sql = "UPDATE usuario SET email = ?, psw = ?, saldo = ?, administrador = ? WHERE id_usuario = ?";
        int registros = 0;
        
        try {
            conn = this.connTransacional != null ? this.connTransacional : getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getPassword());
            ps.setDouble(3, usuario.getSaldo());
            ps.setBoolean(4, usuario.isAdministrador());
            ps.setInt(5, usuario.getId_usuario());
            
            registros = ps.executeUpdate();
            
        } finally {
            if (this.connTransacional == null){   
                close(conn);
            }
            close(ps);
        return registros;
        }
    }

    @Override
    public int borrar(Usuario usuario) throws SQLException{
        sql = "DELETE FROM usuario WHERE id_usuario = ?";
        int registros = 0;
        
        try {
            conn =  getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, usuario.getId_usuario());
            registros = ps.executeUpdate();
            
        } finally {
            if (this.connTransacional == null){   
                close(conn);
            }
            close(ps);
        }
        return registros;
        
        /*
            sql = "DELETE FROM usuario WHERE id_usuario = ?";
            int registros = 0;

            try {
                conn = this.connTransacional != null ? this.connTransacional : getConnection();
                ps = conn.prepareStatement(sql);

                Usuario usuario = new Usuario();
                ps.setInt(1, usuario.getId_usuario());
                registros = ps.executeUpdate();

            } finally {
                if (this.connTransacional == null){   
                    close(conn);
                }
                close(ps);
            }
            return registros;
        */
    }

    @Override
    public List<Usuario> findById(int id_usuario) {
        sql = "SELECT * FROM usuario WHERE id_usuario = ?";
        List<Usuario> usuario = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int idpersona = rs.getInt("id_usuario");
                String email = rs.getString("email");
                String psw = rs.getString("psw");
                double saldo = rs.getDouble("saldo");
                boolean administrador = rs.getBoolean("administrador");
                
                usuario.add(new Usuario(idpersona, email, psw, saldo, administrador));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
                close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return usuario;
    }
    
}
