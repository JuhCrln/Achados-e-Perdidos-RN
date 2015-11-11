package Java;

import java.util.ArrayList;

public interface InterfaceServicoUsuario {
	final static int SUCESSO = 1;
	final static int ERRO = 2;
	
	public ArrayList<Usuario> getUsuarios() throws Exception;

	public int gravaUsuario(Usuario usuario);

	public void atualizaUsuario(Usuario usuario) throws Exception;

	public void excluiUsuario(Usuario usuario) throws Exception;
}
