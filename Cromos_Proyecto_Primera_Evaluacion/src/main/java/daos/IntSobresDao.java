package daos;

import dominio.Sobres;
import excepciones.*;
import java.util.List;

public interface IntSobresDao {
    
    public List<Sobres> listarSobres() throws LecturaEx;
    public List<Sobres> listarSobresUsuario(int id_usuario) throws LecturaEx;
    public List<Sobres> findByIdSobre(int id_sobre) throws LecturaEx; //Buscamos por el id para sacar el precio del sobre
    public int crearSobre(Sobres sobres) throws EscrituraEx;
    public int modificar(Sobres sobres) throws EscrituraEx;
    public int eliminar(Sobres sobres) throws EscrituraEx;
    
}
