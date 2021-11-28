package daos;

import static datos.Conexion.*;
import java.sql.*;
import dominio.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDaoImpl implements IntPedidoDao{
    
    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public int comprarSobre(Pedidos pedidos) {
        sql = "INSERT INTO pedidos (id_usuario, id_sobres, cantidad, fecha_alta) VALUES (?, ?, ?, current_date());";
        int registro = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, pedidos.getId_usuario());
            ps.setInt(2, pedidos.getId_sobres());
            ps.setInt(3, pedidos.getCantidad());
            //ps.setDate(4, (Date) pedidos.getFecha_alta());
            
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
    public List<Pedidos> listarPedidos(Usuario usuario) {
        sql  = "SELECT * FROM pedidos WHERE id_usuario = ?";
        List<Pedidos> pedidos = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, usuario.getId_usuario());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_pedido = rs.getInt("id_pedido");
                int id_usuario = rs.getInt("id_usuario");
                int id_sobres = rs.getInt("id_sobres");
                int cantidad = rs.getInt("cantidad");
                Date fecha_alta = rs.getDate("fecha_alta");
                
                
                pedidos.add(new Pedidos(id_pedido, id_usuario, id_sobres, cantidad, fecha_alta));
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
        return pedidos;
    }
    
}
