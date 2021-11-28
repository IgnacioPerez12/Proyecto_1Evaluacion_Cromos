package daos;

import static datos.Conexion.*;
import dominio.SobreUsuario;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SobreUsuarioDaoImpl implements IntSobreUsuarioDao{

    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public List<SobreUsuario> listSobreUsuario(int id_sobre, int id_usuario) {
        sql  = "SELECT * FROM sobres_en_usuario WHERE id_sobres = ? AND id_usuario = ?;";
        List<SobreUsuario> sobreUsu = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_sobre);
            ps.setInt(2, id_usuario);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_sobre_en_usuario = rs.getInt("id_sobre_en_usuario");
                id_usuario = rs.getInt("id_usuario");
                int id_sobres = rs.getInt("id_sobres");
                int cantidad = rs.getInt("cantidad");
                
                
                sobreUsu.add(new SobreUsuario(id_sobre_en_usuario, id_usuario, id_sobres, cantidad));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
                close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return sobreUsu;
    }

    @Override
    public int insertSobreUsuario(SobreUsuario sobreUsuario) {
        sql = "INSERT INTO sobres_en_usuario (id_usuario, id_sobres, cantidad) VALUES (?, ?, ?);";
        int registro = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, sobreUsuario.getId_usuario());
            ps.setInt(2, sobreUsuario.getId_sobres());
            ps.setInt(3, sobreUsuario.getCantidad());
            
            registro = ps.executeUpdate();
            
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
        
        return registro;
    }

    @Override
    public int updateSobreUsuario(SobreUsuario sobreUsuario) {
        sql = "UPDATE sobres_en_usuario SET cantidad = ? WHERE id_usuario = ? AND id_sobres = ?;";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, sobreUsuario.getCantidad());
            ps.setInt(2, sobreUsuario.getId_usuario());
            ps.setInt(3, sobreUsuario.getId_sobres());
            
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
    public List<SobreUsuario> findByUsuario(int id_usuario) {
        sql  = "SELECT * FROM sobres_en_usuario WHERE id_usuario = ? ORDER BY id_sobres ASC;";
        List<SobreUsuario> sobreUsu = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_sobre_en_usuario = rs.getInt("id_sobre_en_usuario");
                id_usuario = rs.getInt("id_usuario");
                int id_sobres = rs.getInt("id_sobres");
                int cantidad = rs.getInt("cantidad");
                
                
                sobreUsu.add(new SobreUsuario(id_sobre_en_usuario, id_usuario, id_sobres, cantidad));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
                close(rs);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return sobreUsu;
    }
    
}
