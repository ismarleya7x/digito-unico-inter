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


		
	}
	
	
}
