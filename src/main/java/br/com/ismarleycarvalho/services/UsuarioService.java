package br.com.ismarleycarvalho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ismarleycarvalho.entities.Usuario;
import br.com.ismarleycarvalho.repositories.UsuarioRepository;
import br.com.ismarleycarvalho.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll(){
		return repository.findAll() ;
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
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
		if(obj.getNome() != null)
			entity.setNome(obj.getNome());
		if(obj.getEmail() != null)
			entity.setEmail(obj.getEmail());		
	}
}
