package Java;

import java.util.ArrayList;

public interface InterfaceServicoItem {
	final static int SUCESSO = 1;
	final static int ERRO = 2;

	public ArrayList<Item> getItem();
	
	public ArrayList<Item> getItem(String fild, String Keyword);

	public int gravaItem(Item item);

	public void atualizaItem(Item item) throws Exception;

	public void excluiItem(Item item) throws Exception;

}
