package Java;

import java.util.ArrayList;

public class ServicoItem implements InterfaceServicoItem {

	private InterfaceDAO<String, Item> daoItem;
	private SimpleEntityManager simpleEntityManager;

	public ServicoItem(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		this.daoItem = new ItemDAO(simpleEntityManager.getEntityManager());
	}

	@Override
	public ArrayList<Item> getItem() {
		return (ArrayList<Item>) daoItem.busca();
	}

	@Override
	public int gravaItem(Item item) {
		try {
			simpleEntityManager.beginTransaction();
			daoItem.adiciona(item);
			simpleEntityManager.commit();
			return SUCESSO;
		} catch (javax.persistence.PersistenceException erro) {
			erro.printStackTrace();
			try {
				simpleEntityManager.rollBack();
			} catch (java.lang.IllegalStateException ex) {
				ex.printStackTrace();
			}
			return ERRO;
		}
	}

	@Override
	public void atualizaItem(Item item) throws Exception {
		daoItem.substitui(item);
	}

	@Override
	public void excluiItem(Item item) throws Exception {
		daoItem.remove(item);
	}

	@Override
	public ArrayList<Item> getItem(String fild, String Keyword) {
		simpleEntityManager.beginTransaction();
		ArrayList<Item> result = (ArrayList<Item>) daoItem.busca(fild, Keyword);
		simpleEntityManager.commit();
		return result;
	}
}
