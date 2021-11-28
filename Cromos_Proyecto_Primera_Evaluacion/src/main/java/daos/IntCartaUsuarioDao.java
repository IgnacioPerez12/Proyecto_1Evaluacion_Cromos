package daos;

import dominio.CartaUsuario;
import excepciones.*;
import java.util.List;

public interface IntCartaUsuarioDao {
    public List<CartaUsuario> listCartaUsuario(int id_usuario, int id_carta) throws LecturaEx; // Buscar por id usuario y id carta
    public int insertarCartaUsuario(CartaUsuario cartaUsuario) throws EscrituraEx; //insert
}
