package br.com.lol.lol.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.model.Funcionario;
import br.com.lol.lol.repository.FuncionarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/funcionario")
public class FuncionarioREST {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<Funcionario> cadastrar(@RequestBody Funcionario funcionario) {

        funcionario.getUsuario().setEmail(funcionario.getUsuario().getEmail().toLowerCase());
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findByUsuarioEmail(funcionario.getUsuario().getEmail());
        
        if (funcionarioBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
            funcionarioRepository.save(funcionario);
            funcionario.getUsuario().setSenha(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        }

    }
    
}
