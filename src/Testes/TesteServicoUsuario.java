package Testes;

import Java.InterfaceServicoUsuario;
import Java.ServicoUsuario;
import Java.SimpleEntityManager;
import Java.Usuario;

public class TesteServicoUsuario {
	
	public static void main(String[] args) {
		String persistenceUnitName = "usuarios";
		SimpleEntityManager simpleEntityManager = new SimpleEntityManager(persistenceUnitName);
		InterfaceServicoUsuario serv = new ServicoUsuario(simpleEntityManager);
		Usuario usuarioTeste1 = new Usuario();
		usuarioTeste1.setNome("Usuario1");
		usuarioTeste1.setTelefone("84333333333");
		usuarioTeste1.setEmail("usuario1@usuario1.com");
		usuarioTeste1.setCidade("São Paulo1");
		usuarioTeste1.setSenha("123pwd1");
		usuarioTeste1.setEstado("SP");
		usuarioTeste1.setSenha("usuario1123");
		usuarioTeste1.setNomeDeUsuario("usuario1123");
		Usuario usuarioTeste = new Usuario();
		usuarioTeste.setNome("Usuario");
		usuarioTeste.setTelefone("84555555555");
		usuarioTeste.setEmail("usuario@usuario.com");
		usuarioTeste.setCidade("São Paulo");
		usuarioTeste.setSenha("123pwd");
		usuarioTeste.setEstado("SP");
		usuarioTeste.setSenha("usuario123");
		usuarioTeste.setNomeDeUsuario("usuario123");
		serv.gravaUsuario(usuarioTeste);
		serv.gravaUsuario(usuarioTeste1);
		simpleEntityManager.close();
	}
}
