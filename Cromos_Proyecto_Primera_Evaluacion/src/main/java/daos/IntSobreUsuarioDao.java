package daos;

import dominio.SobreUsuario;
import excepciones.EscrituraEx;
import excepciones.LecturaEx;
import java.util.List;

public interface IntSobreUsuarioDao {

    public List<SobreUsuario> listSobreUsuario(int id_sobre, int id_usuario) throws LecturaEx;
    public List<SobreUsuario> findByUsuario(int id_usuario) throws LecturaEx;
    public int insertSobreUsuario(SobreUsuario sobreUsuario) throws EscrituraEx;
    public int updateSobreUsuario(SobreUsuario sobreUsuario) throws EscrituraEx;
}
