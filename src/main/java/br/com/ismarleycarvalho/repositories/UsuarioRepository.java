package br.com.ismarleycarvalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ismarleycarvalho.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
