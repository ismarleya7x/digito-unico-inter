package br.com.ismarleycarvalho.services;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ismarleycarvalho.cripto.EncriptaDecriptaRSA;
import br.com.ismarleycarvalho.entities.Usuario;
import br.com.ismarleycarvalho.repositories.UsuarioRepository;
import br.com.ismarleycarvalho.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	private KeyPair pair;
	private EncriptaDecriptaRSA security;

	public UsuarioService() {
		security = new EncriptaDecriptaRSA();
		pair = security.generateKeyPair();
	}

	public List<Usuario> findAll() {
		List<Usuario> listUsuarios = repository.findAll();
		List<Usuario> listRetorno = new ArrayList<>();

		try {
			for (Usuario usuario : listUsuarios) {
				String nome = security.decrypt(usuario.getNome().toString(), pair.getPrivate());
				String email = security.decrypt(usuario.getEmail().toString(), pair.getPrivate());

				usuario.setEmail(email);
				usuario.setNome(nome);
				usuario.setId(usuario.getId());

				listRetorno.add(usuario);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listRetorno;
	}

	public Usuario findById(Long id, boolean bool) {

		try {

			Optional<Usuario> obj = repository.findById(id);

			if (!bool) {
				obj.get().setEmail(security.decrypt(obj.get().getEmail().toString(), pair.getPrivate()));
				obj.get().setNome(security.decrypt(obj.get().getNome().toString(), pair.getPrivate()));
			}

			return obj.get();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResourceNotFoundException(id);
		}
	}

	public Usuario insert(Usuario obj) {
		try {

			obj.setEmail(security.encrypt(obj.getEmail(), pair.getPublic()));
			obj.setNome(security.encrypt(obj.getNome(), pair.getPublic()));

			return repository.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Usuario update(Long id, Usuario obj) {
		Usuario entity = repository.getOne(id);

		updateData(entity, obj);

		return repository.save(entity);
	}

	private void updateData(Usuario entity, Usuario obj) {
		if (obj.getNome() != null)
			entity.setNome(security.encrypt(obj.getNome(), pair.getPublic()));
		if (obj.getEmail() != null)
			entity.setEmail(security.encrypt(obj.getEmail(), pair.getPublic()));
	}
}
