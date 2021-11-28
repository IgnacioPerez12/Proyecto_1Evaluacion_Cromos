package daos;

import dominio.*;
import excepciones.EscrituraEx;
import excepciones.LecturaEx;
import java.util.List;

public interface IntPedidoDao {
    public int comprarSobre(Pedidos pedidos) throws EscrituraEx;
    public List<Pedidos> listarPedidos(Usuario usuario) throws LecturaEx;
}
