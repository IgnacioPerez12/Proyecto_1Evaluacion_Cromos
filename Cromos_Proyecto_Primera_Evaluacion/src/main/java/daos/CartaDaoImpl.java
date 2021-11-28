package daos;

import static datos.Conexion.*;
import dominio.Carta;
import dominio.Usuario;
import java.sql.*;
import java.util.*;

public class CartaDaoImpl implements IntCartaDao{

    String sql;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public List<Carta> listarCartas() {  
        sql = "SELECT * FROM carta";
        List<Carta> carta = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_carta = rs.getInt("id_carta");
                String nombreJugador = rs.getString("nombreJugador");
                String equipo = rs.getString("equipo");
                String posicion = rs.getString("posicion");
                int media = rs.getInt("media");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                String edicion = rs.getString("edicion");
                
                carta.add(new Carta(id_carta, nombreJugador, equipo, posicion, media, ataque, defensa, edicion));
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
        return carta;
    }

    @Override
    public int crearCarta(Carta carta) {
        sql = "INSERT INTO carta (nombreJugador, equipo, posicion, media, ataque, defensa, edicion) VALUES (?, ?, ?, ?, ?, ?, ?);";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, carta.getNombreJugador());
            ps.setString(2, carta.getEquipo());
            ps.setString(3, carta.getPosicion());
            ps.setInt(4, carta.getMedia());
            ps.setInt(5, carta.getAtaque());
            ps.setInt(6, carta.getDefensa());
            ps.setString(7, carta.getEdicion());
            
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
    public int modificar(Carta carta) {
        sql = "UPDATE carta SET nombreJugador = ?, equipo = ?, posicion = ?, media = ?, ataque = ?, defensa = ?, edicion = ? WHERE id_carta = ?";
        int registros = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, carta.getNombreJugador());
            ps.setString(2, carta.getEquipo());
            ps.setString(3, carta.getPosicion());
            ps.setInt(4, carta.getMedia());
            ps.setInt(5, carta.getAtaque());
            ps.setInt(6, carta.getDefensa());
            ps.setString(7, carta.getEdicion());
            ps.setInt(8, carta.getId_carta());
            
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
    public int eliminar(Carta carta) {
        sql = "DELETE FROM carta WHERE id_carta = ?";
        int registros = 0;
        
        try {
            conn =  getConnection();
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, carta.getId_carta());
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
    public List<Carta> listarCartasUsuario(Usuario usuario) {
        sql = "SELECT carta.*  FROM carta INNER JOIN carta_en_usuario ON carta.id_carta = carta_en_usuario.id_carta INNER JOIN usuario ON carta_en_usuario.id_usuario = usuario.id_usuario WHERE email = ?;";
        List<Carta> carta = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            rs = ps.executeQuery();
            
            while (rs.next()) {
                int id_carta = rs.getInt("id_carta");
                String nombreJugador = rs.getString("nombreJugador");
                String equipo = rs.getString("equipo");
                String posicion = rs.getString("posicion");
                int media = rs.getInt("media");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                String edicion = rs.getString("edicion");
                
                carta.add(new Carta(id_carta, nombreJugador, equipo, posicion, media, ataque, defensa, edicion));
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
        return carta;
    }

    @Override
    public int listMaxID() {
        sql = "SELECT max(id_carta) FROM carta;";
        int maxID = 0;
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                //System.out.println("MAX ID: " + rs.getInt("MAX(id_carta)"));
                maxID = rs.getInt("MAX(id_carta)");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(conn);
                close(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return maxID;
    }

    @Override
    public List<Carta> FindById(int id_carta) {
        sql = "SELECT * FROM carta WHERE id_carta = ?";
        List<Carta> carta = new ArrayList<>();
        
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_carta);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                id_carta = rs.getInt("id_carta");
                String nombreJugador = rs.getString("nombreJugador");
                String equipo = rs.getString("equipo");
                String posicion = rs.getString("posicion");
                int media = rs.getInt("media");
                int ataque = rs.getInt("ataque");
                int defensa = rs.getInt("defensa");
                String edicion = rs.getString("edicion");
                
                carta.add(new Carta(id_carta, nombreJugador, equipo, posicion, media, ataque, defensa, edicion));
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
        return carta;
    }
    
}
