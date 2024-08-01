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

import br.com.lol.lol.model.Usuario;
import br.com.lol.lol.repository.UsuarioRepository;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioREST {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {

        usuario.setEmail(usuario.getEmail().toLowerCase());
        Optional<Usuario> usuarioBD = usuarioRepository.findByEmail(usuario.getEmail());

        if (!usuarioBD.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            if (passwordEncoder.matches(usuario.getSenha(), usuarioBD.get().getSenha())) {
                usuarioBD.get().setSenha(null);
                return ResponseEntity.ok(usuarioBD.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }

    }
}
