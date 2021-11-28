package daos;

import dominio.Carta;
import dominio.Usuario;
import excepciones.*;
import java.util.*;

public interface IntCartaDao {
    public List<Carta> listarCartas() throws LecturaEx; //Listar todas las cartas
    public List<Carta> FindById(int id_carta) throws LecturaEx; //Listar por ID Carta
    public int crearCarta(Carta carta) throws EscrituraEx;
    public int modificar(Carta carta) throws EscrituraEx;
    public int eliminar(Carta carta) throws EscrituraEx;
    public List<Carta> listarCartasUsuario(Usuario usuario) throws LecturaEx; //Cartas de x usuario
    public int listMaxID() throws AccesoDatosEx; //Sacar el max(ID)
}
