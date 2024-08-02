package br.com.lol.lol.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PutMapping("/atualizar/{idFuncionario}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable("idFuncionario") Long idFuncionario, @RequestBody Funcionario funcionario) {
        return funcionarioRepository.findById(idFuncionario).map(funcionarioBD -> {
            funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
            funcionarioRepository.save(funcionario);
            funcionario.getUsuario().setSenha(null);
            return ResponseEntity.ok(funcionario);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/consultar/{idFuncionario}")
    public ResponseEntity<Funcionario> consultar(@PathVariable("idFuncionario") Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).map(funcionario -> {
            funcionario.getUsuario().setSenha(null);
            return ResponseEntity.ok(funcionario);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/remover/{idFuncionario}")
    public ResponseEntity<Funcionario> remover(@PathVariable("idFuncionario") Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).map(funcionario -> {
            funcionarioRepository.delete(funcionario);
            funcionario.getUsuario().setSenha(null);
            return ResponseEntity.ok(funcionario);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> listar() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        funcionarios.forEach(funcionario -> {
            if (funcionario.getUsuario() != null) funcionario.getUsuario().setSenha(null);
        });
        return ResponseEntity.ok(funcionarios);
    }
    
}
