package Java;

import javax.persistence.EntityManager;

public class ItemDAO extends GenericoDAO<String, Item> implements InterfaceDAO<String, Item> {
    public ItemDAO(EntityManager entityManager) {
        super(entityManager);
    }
}
