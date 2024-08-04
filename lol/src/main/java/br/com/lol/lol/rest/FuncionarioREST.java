package br.com.lol.lol.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.lol.lol.dto.FuncionarioDTO;
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
    public ResponseEntity<FuncionarioDTO> cadastrar(@RequestBody Funcionario funcionario) {
        funcionario.getUsuario().setEmail(funcionario.getUsuario().getEmail().toLowerCase());
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findByUsuarioEmail(funcionario.getUsuario().getEmail());
        if (funcionarioBD.isPresent()) {
            FuncionarioDTO funcionarioDTOExistente = new FuncionarioDTO(funcionarioBD.get());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(funcionarioDTOExistente);
        } else {
            funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
            Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
            FuncionarioDTO funcionarioDTOSalvo = new FuncionarioDTO(funcionarioSalvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDTOSalvo);
        }
    }

    @PutMapping("/atualizar/{idFuncionario}")
    public ResponseEntity<FuncionarioDTO> atualizar(@PathVariable("idFuncionario") Long idFuncionario, @RequestBody Funcionario funcionario) {
        Optional<Funcionario> funcionarioBD = funcionarioRepository.findById(idFuncionario);
        if (funcionarioBD.isPresent()) {
            Optional<Funcionario> funcionarioExistente = funcionarioRepository.findByUsuarioEmail(funcionario.getUsuario().getEmail());
            if (funcionarioExistente.isPresent() && !funcionarioExistente.get().getUsuario().getIdUsuario().equals(funcionarioBD.get().getUsuario().getIdUsuario())) {
                FuncionarioDTO funcionarioDTOExistente = new FuncionarioDTO(funcionarioExistente.get());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(funcionarioDTOExistente);
            } else {
                funcionario.setIdFuncionario(idFuncionario);
                funcionario.getUsuario().setIdUsuario(funcionarioBD.get().getUsuario().getIdUsuario());
                funcionario.getUsuario().setPermissao(funcionarioBD.get().getUsuario().getPermissao());
                funcionario.getUsuario().setSenha(passwordEncoder.encode(funcionario.getUsuario().getSenha()));
                Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
                FuncionarioDTO funcionarioDTOSalvo = new FuncionarioDTO(funcionarioSalvo);
                return ResponseEntity.ok(funcionarioDTOSalvo);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remover/{idFuncionario}")
    public ResponseEntity<FuncionarioDTO> remover(@PathVariable("idFuncionario") Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).map(funcionarioBD -> {
            funcionarioRepository.delete(funcionarioBD);
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionarioBD);
            return ResponseEntity.ok(funcionarioDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<FuncionarioDTO> consultar(@PathVariable("idUsuario") Long idUsuario) {
        return funcionarioRepository.findByUsuarioIdUsuario(idUsuario).map(funcionarioBD -> {
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionarioBD);
            return ResponseEntity.ok(funcionarioDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FuncionarioDTO>> listar() {
        List<Funcionario> listaFuncionarioBD = funcionarioRepository.findAll();
        if (listaFuncionarioBD.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<FuncionarioDTO> listaFuncionarioDTO = listaFuncionarioBD.stream().map(funcionario -> {
                FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
                return funcionarioDTO;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(listaFuncionarioDTO);
        }
    }
    
}
