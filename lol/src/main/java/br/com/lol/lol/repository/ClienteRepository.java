package br.com.lol.lol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lol.lol.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsuarioEmail(String email);
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByUsuarioEmailOrCpf(String email, String cpf);
}

