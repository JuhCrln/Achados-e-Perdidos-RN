package Java;

import javax.faces.bean.ManagedBean;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

@ManagedBean
public class MBAchadosPerdidosRN {
	private InterfaceServicoUsuario servUsuario;
	private InterfaceServicoItem servItem;
	private Usuario usuario;
	private Item item;
	private String persistenceUnitName;
	private SimpleEntityManager simpleEntityManager;
	private ArrayList<Item> itens;
	private ArrayList<Item> itens_filtrados;
	private String filtro;
	private String busca;

	public ArrayList<Item> getItens_filtrados() {
		return itens_filtrados;
	}

	public void setItens_filtrados(ArrayList<Item> itens_filtrados) {
		this.itens_filtrados = itens_filtrados;
	}
	
	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public MBAchadosPerdidosRN() throws InterruptedException {
		usuario = new Usuario();
		item = new Item();
		persistenceUnitName = "achados";
		simpleEntityManager = new SimpleEntityManager(persistenceUnitName);
		//FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(simpleEntityManager.getEntityManager());
		//fullTextEntityManager.createIndexer().startAndWait();
		servUsuario = new ServicoUsuario(simpleEntityManager);
		servItem = new ServicoItem(simpleEntityManager);
	}

	public void salvarUsuario() {
		int retorno = servUsuario.gravaUsuario(usuario);
		if(retorno == InterfaceServicoUsuario.SUCESSO) {
			showMessage("Mensagem", "Usuário criado com sucesso!");
		} else if(retorno == InterfaceServicoUsuario.ERRO) {
			showError("Erro", "O usuário não removido!Tente novamente!");
		}
		//O hibernate pegava o valor do objeto anterior e gravava de novo, uma saída é sempre criar um novo usuário.
		usuario = new Usuario();
	}

	public void salvarItem() {
		
		int retorno = servItem.gravaItem(item);
		if(retorno == InterfaceServicoUsuario.SUCESSO) {
			showMessage("Mensagem", "Objeto cadastrado com sucesso!");
		} else if(retorno == InterfaceServicoUsuario.ERRO) {
			showError("Erro", "O objeto não foi cadastrado!Tente novamente!");
		}
		item = new Item(); 
	}

	public String removerUsuario() {
		try {
			servUsuario.excluiUsuario(usuario);
			return "Usuário removido com sucesso!";

		} catch (Exception e) {
			e.printStackTrace();
			return "O Usuário não foi removido!Tente novamente!";
		}
	}

	public String removerItem() {
		try {
			servItem.excluiItem(item);
			return "Item removido com sucesso!";

		} catch (Exception e) {
			return "O Item não foi removido!Tente novamente!";
		}
	}

	public void substituiUsuario() {
		try {
			servUsuario.atualizaUsuario(usuario);
			showMessage("Mensagem","Usuário atualizado com sucesso!");

		} catch (Exception e) {
			showMessage("Mensagem","O usuário não foi atualizado! Tente novamente!");
		}
	}

	public String substituiItem() {
		try {
			servItem.atualizaItem(item);
			return "Item atualizado com sucesso!";
		} catch (Exception e) {
			return "O item não foi atualizado! Tente novamente!";
		}
	}

	public String buscarUsuario() {
		try {
			servUsuario.getUsuarios();
			return "";

		} catch (Exception e) {
			return "O usuário não foi encontrado! Tente novamente!";
		}
	}

	public void buscarItem() {
		try {
			itens = servItem.getItem();
			String result = "Itens encontrados: <br />";
			for(Item i : itens) {
				result += " - " + i.getTipo() + "<br />";
			}
			showMessage("Itens", result);
		} catch (Exception e) {
			showError("Erro", "O item não foi encontrado! Tente novamente!");
		}
	}
	
	public void buscarItemF() {
		System.out.println("FILTRO"+filtro);
		System.out.println("BUSCA"+busca);
		try {
			itens_filtrados = servItem.getItem(filtro, busca);
			String result = "Itens encontrados: <br />";
			for(Item i : itens_filtrados) {
				result += " - Tipo : " + i.getTipo() + "<br />";
				result += " - Marca : " + i.getMarca() + "<br />";
				result += " - Cor : " + i.getCor() + "<br />";
				result += " - Modelo : " + i.getModelo() + "<br />";
			}
			showMessage("Itens", result);
		} catch (Exception e) {
			e.printStackTrace();
			showError("Erro", "O item não foi encontrado! Tente novamente!");
		}
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Item getItem() {
		return this.item;
	}
	
	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}

	public void showMessage(String titulo, String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public void showError(String titulo, String mensagem) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, mensagem);
		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}
}
