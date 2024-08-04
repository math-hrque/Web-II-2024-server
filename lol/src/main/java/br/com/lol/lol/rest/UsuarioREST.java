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

import br.com.lol.lol.dto.UsuarioRequestDTO;
import br.com.lol.lol.dto.UsuarioResponseDTO;
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
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioRequestDTO.setEmail(usuarioRequestDTO.getEmail().toLowerCase());
        Optional<Usuario> usuarioBD = usuarioRepository.findByEmail(usuarioRequestDTO.getEmail());
        if (usuarioBD.isPresent()) {
            if (passwordEncoder.matches(usuarioRequestDTO.getSenha(), usuarioBD.get().getSenha())) {
                UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(usuarioBD.get());
                return ResponseEntity.ok(usuarioResponseDTO);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
