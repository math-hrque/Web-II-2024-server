package br.com.lol.lol.rest;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.model.Cliente;
import br.com.lol.lol.repository.ClienteRepository;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteREST {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {

        cliente.getUsuario().setEmail(cliente.getUsuario().getEmail().toLowerCase());
        Optional<Cliente> clienteBD = clienteRepository.findByUsuarioEmailOrCpf(cliente.getUsuario().getEmail(), cliente.getCpf());
        
        if (clienteBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            String senhaAleatoria = String.format("%04d", new Random().nextInt(10000));
            cliente.getUsuario().setSenha(passwordEncoder.encode(senhaAleatoria));
            clienteRepository.save(cliente);
            cliente.getUsuario().setSenha(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }

    }
    
}
