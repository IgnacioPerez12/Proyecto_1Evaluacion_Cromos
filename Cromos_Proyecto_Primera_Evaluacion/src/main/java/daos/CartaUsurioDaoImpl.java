package daos;

import static datos.Conexion.*;
import dominio.CartaUsuario;
import java.sql.*;
import java.util.*;

public class CartaUsurioDaoImpl implements IntCartaUsuarioDao{

    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public List<CartaUsuario> listCartaUsuario(int id_usuario, int id_carta) {
        sql  = "SELECT * FROM carta_en_usuario WHERE id_usuario = ? AND id_carta = ?;";
        List<CartaUsuario> cartaUsu = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_carta);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_carta_en_usuario = rs.getInt("id_carta_en_usuario");
                id_usuario = rs.getInt("id_usuario");
                int id_sobres = rs.getInt("id_carta");
                
                
                cartaUsu.add(new CartaUsuario(id_carta_en_usuario, id_usuario, id_carta));
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
        return cartaUsu;
    }

    @Override
    public int insertarCartaUsuario(CartaUsuario cartaUsuario) {
        sql = "INSERT INTO carta_en_usuario (id_usuario, id_carta) VALUES (?, ?);";
        int registro = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, cartaUsuario.getId_usuario());
            ps.setInt(2, cartaUsuario.getId_carta());
            
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
    
}
