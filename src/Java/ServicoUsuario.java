package Java;

import java.util.ArrayList;

public class ServicoUsuario implements InterfaceServicoUsuario {
	private InterfaceDAO<String, Usuario> daoUsuario;
	private SimpleEntityManager simpleEntityManager;

	public ServicoUsuario(SimpleEntityManager simpleEntityManager) {
		this.simpleEntityManager = simpleEntityManager;
		this.daoUsuario = new UsuarioDAO(simpleEntityManager.getEntityManager());
	}

	@Override
	public ArrayList<Usuario> getUsuarios() throws Exception {
		simpleEntityManager.beginTransaction();
		ArrayList<Usuario> result = (ArrayList<Usuario>) daoUsuario.busca();
		simpleEntityManager.commit();
		return result;
	}

	@Override
	public int gravaUsuario(Usuario usuario) {
		try {
			simpleEntityManager.beginTransaction();
			daoUsuario.adiciona(usuario);
			simpleEntityManager.commit();
			return SUCESSO;
		} catch(javax.persistence.PersistenceException erro) {
			erro.printStackTrace();
			try {
				simpleEntityManager.rollBack();
			} catch(java.lang.IllegalStateException ex) {
				ex.printStackTrace();
			}
			return ERRO;
		}
		 
	}

	@Override
	public void atualizaUsuario(Usuario usuario) throws Exception {
		simpleEntityManager.beginTransaction();
		daoUsuario.substitui(usuario);
		simpleEntityManager.commit();
	}

	@Override
	public void excluiUsuario(Usuario usuario) throws Exception {
		simpleEntityManager.beginTransaction();
		daoUsuario.remove(usuario);
		simpleEntityManager.commit();
	}

}
