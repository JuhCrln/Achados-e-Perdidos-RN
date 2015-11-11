package Testes;

import Java.InterfaceServicoItem;
import Java.Item;
import Java.ServicoItem;
import Java.SimpleEntityManager;

public class TesteServicoItem {
	public static void main(String[] args) {
		String persistenceUnitName = "itens";
		SimpleEntityManager simpleEntityManager = new SimpleEntityManager(persistenceUnitName);
		@SuppressWarnings("unchecked")
		InterfaceServicoItem serv = new ServicoItem(simpleEntityManager);
		Item item = new Item();
		item.setTipo("tipo1");
		item.setModelo("marca1");
		item.setCor("cor1");
		item.setOutrasCaracteristicas("outra1");
		item.setBairro("bairro1");
		item.setPontoDeReferencia("opento1");
		item.setHorario("horario1");
		//serv.gravaItem(item);
		simpleEntityManager.close();
	}
}
