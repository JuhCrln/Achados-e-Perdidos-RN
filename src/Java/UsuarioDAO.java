package Java;
import javax.persistence.EntityManager;

public class UsuarioDAO extends GenericoDAO<String, Usuario> {
    public UsuarioDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
