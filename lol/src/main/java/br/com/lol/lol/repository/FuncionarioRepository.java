package br.com.lol.lol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lol.lol.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByUsuarioEmail(String email);
}

