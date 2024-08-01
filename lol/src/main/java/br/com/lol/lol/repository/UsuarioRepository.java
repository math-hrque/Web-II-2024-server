package br.com.lol.lol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lol.lol.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
