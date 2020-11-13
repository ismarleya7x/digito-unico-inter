package br.com.ismarleycarvalho.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ismarleycarvalho.entities.DigitoUnico;
import br.com.ismarleycarvalho.entities.Usuario;
import br.com.ismarleycarvalho.repositories.DigitoUnicoRepository;

@Service
public class DigitoUnicoService {
	
	@Autowired
	private DigitoUnicoRepository repository;
	
	public List<DigitoUnico> findAll(){
		return repository.findAll() ;
	}
	
	public DigitoUnico findById(Long id) {
		Optional<DigitoUnico> obj = repository.findById(id);
		return obj.get();
	}
	
	public DigitoUnico insert(DigitoUnico obj) {
		return repository.save(obj);
	}

}
