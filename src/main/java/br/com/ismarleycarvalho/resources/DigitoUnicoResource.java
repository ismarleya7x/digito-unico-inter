package br.com.ismarleycarvalho.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ismarleycarvalho.entities.DigitoUnico;
import br.com.ismarleycarvalho.entities.Usuario;
import br.com.ismarleycarvalho.services.DigitoUnicoService;
import br.com.ismarleycarvalho.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Digito Único")
@RestController
@RequestMapping(value = "/digitounico")
public class DigitoUnicoResource {	

	private static final Integer DEFAULT_CONCAT = 1;
	private List<DigitoUnico> digitosCache = new ArrayList<>();
	
	@Autowired
	private DigitoUnicoService service;
	
	@Autowired
	private UsuarioService serviceUsuario;

	@ApiOperation(value="Retorna todos os cálculos já realizados!")
	@GetMapping
	public ResponseEntity<List<DigitoUnico>> findAll(){
		List<DigitoUnico> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@ApiOperation(value="Retorna os cálculos feitos para um usuário via ID!")
	@GetMapping(value = "/{idUsuario}")
	public ResponseEntity<List<DigitoUnico>> findById(@PathVariable Long idUsuario){
		List<DigitoUnico> list = service.findAll();
		
		List<DigitoUnico> digitos = new ArrayList<>();
		
		for(DigitoUnico digito : list) {
			Long digId = digito.getUsuario().getId() != null ? digito.getUsuario().getId() : 0L;
			if(digId.equals(idUsuario) && !digId.equals(0L)) {
				digitos.add(digito);
			}
		}
		
		return ResponseEntity.ok().body(digitos);
	}

	@ApiOperation(value="Realiza um cálculo novo!")
	@PostMapping
	public ResponseEntity<DigitoUnico> insert(@RequestBody DigitoUnico obj){
		
		DigitoUnico isCached = verificaCache(obj);
		
		if(isCached == null) {
			obj.setResultado(digitoUnico(obj.getNumero(), obj.getRepeticao()));
		}else {
			obj.setResultado(isCached.getResultado());
		}
		
		if(obj.getUsuario() != null) {
			obj.setUsuario(recuperaDadosUsuario(obj.getUsuario().getId()));
		}
		
		obj = service.insert(obj);		
		
		insereCache(obj);
		
		return ResponseEntity.ok().body(obj);
	}

	private Usuario recuperaDadosUsuario(Long id) {
		Usuario obj = serviceUsuario.findById(id);
		
		return obj;
	}

	private void insereCache(DigitoUnico obj) {
		if(digitosCache.size() == 10) {
			digitosCache.remove(0);
			digitosCache.add(obj);
		}else {
			digitosCache.add(obj);
		}
	}
	
	private DigitoUnico verificaCache(DigitoUnico obj) {
		
		DigitoUnico digCached = null;
		
		if(digitosCache.size() > 0) {
			for(DigitoUnico dig : digitosCache) {
				if(dig.equals(obj)) {
					digCached = dig;
					break;
				}
			}
		}
		return digCached;
		
		
	}

	private Integer digitoUnico(String valor, Integer rep) {
		Integer soma = 0;
		char[] numberArray;
		String newNumber = valor;

		if (rep > DEFAULT_CONCAT) {
			for (int i = 1; i < rep; i++) {
				newNumber += valor;
			}
		}

		numberArray = newNumber.toCharArray();

		for (char ch : numberArray) {
			soma += Character.getNumericValue(ch);
		}

		if (soma.toString().length() > 1) {
			return digitoUnico(soma.toString(), DEFAULT_CONCAT);
		}

		return soma;
	}

}
