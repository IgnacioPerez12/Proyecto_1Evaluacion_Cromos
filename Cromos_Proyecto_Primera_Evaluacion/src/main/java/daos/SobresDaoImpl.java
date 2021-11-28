package daos;

import static datos.Conexion.*;
import java.sql.*;
import java.util.*;
import dominio.Sobres;


public class SobresDaoImpl implements IntSobresDao{
    
    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Sobres> listarSobres() {
        sql = "SELECT * FROM sobres";
        List<Sobres> sobres = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_sobres = rs.getInt("id_sobres");
                int cantidadCartas = rs.getInt("cantidadCartas");
                String nombreSobre = rs.getString("nombreSobre");
                double precio = rs.getDouble("precio");
                
                sobres.add(new Sobres(id_sobres, cantidadCartas, nombreSobre, precio));
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
        return sobres;
    }

    @Override
    public int crearSobre(Sobres sobres) {
        sql = "INSERT INTO sobres (cantidadCartas, nombreSobre, precio) VALUES (?, ?, ?);";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, sobres.getCantidadCartas());
            ps.setString(2, sobres.getNombreSobre());
            ps.setDouble(3, sobres.getPrecio());
            
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
    public int modificar(Sobres sobres) {
        sql = "UPDATE sobres SET cantidadCartas = ?, nombreSobre = ?, precio = ? WHERE id_sobres = ?";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, sobres.getCantidadCartas());
            ps.setString(2, sobres.getNombreSobre());
            ps.setDouble(3, sobres.getPrecio());
            ps.setInt(4, sobres.getId_sobres());
            
            registros = ps.executeUpdate();
            
        }  catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {  
            try {
                close(conn);
                close(ps);
                return registros;
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    @Override
    public int eliminar(Sobres sobres) {
        sql = "DELETE FROM sobres WHERE id_sobres = ?";
        int registros = 0;
        
        try {
            conn =  getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, sobres.getId_sobres());
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
    public List<Sobres> findByIdSobre(int id_sobre) {
        sql = "SELECT * FROM sobres WHERE id_sobres = ?";
        List<Sobres> sobres = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_sobre);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_sobres = rs.getInt("id_sobres");
                int cantidadCartas = rs.getInt("cantidadCartas");
                String nombreSobre = rs.getString("nombreSobre");
                double precio = rs.getDouble("precio");
                
                sobres.add(new Sobres(id_sobres, cantidadCartas, nombreSobre, precio));
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
        return sobres;
    }

    @Override
    public List<Sobres> listarSobresUsuario(int id_usuario) {
        sql = "SELECT * FROM sobres";
        List<Sobres> sobres = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_sobres = rs.getInt("id_sobres");
                int cantidadCartas = rs.getInt("cantidadCartas");
                String nombreSobre = rs.getString("nombreSobre");
                double precio = rs.getDouble("precio");
                
                sobres.add(new Sobres(id_sobres, cantidadCartas, nombreSobre, precio));
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
        return sobres;
    }
    
}
