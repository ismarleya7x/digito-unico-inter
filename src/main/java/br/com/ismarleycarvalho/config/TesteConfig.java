package br.com.ismarleycarvalho.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.ismarleycarvalho.entities.DigitoUnico;
import br.com.ismarleycarvalho.entities.Usuario;
import br.com.ismarleycarvalho.repositories.DigitoUnicoRepository;
import br.com.ismarleycarvalho.repositories.UsuarioRepository;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DigitoUnicoRepository digitoUnicoRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Ismarley", "ismarley_2005@msn.com");
		
		usuarioRepository.saveAll(Arrays.asList(u1));
		
		DigitoUnico du1 = new DigitoUnico("12345", 4, new Usuario());
		DigitoUnico du2 = new DigitoUnico("678910", 4, u1);
		DigitoUnico du3 = new DigitoUnico("111213", 4, u1);
		DigitoUnico du4 = new DigitoUnico("12345", 4, new Usuario());
		
		digitoUnicoRepository.saveAll(Arrays.asList(du1, du2, du3, du4));
		
	}
	
	
}
