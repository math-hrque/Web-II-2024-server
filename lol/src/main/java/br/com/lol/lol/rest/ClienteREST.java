package br.com.lol.lol.rest;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dto.ClienteDTO;
import br.com.lol.lol.model.Cliente;
import br.com.lol.lol.repository.ClienteRepository;
import br.com.lol.lol.service.EmailService;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteREST {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteDTO> cadastrar(@RequestBody Cliente cliente) {
        cliente.getUsuario().setEmail(cliente.getUsuario().getEmail().toLowerCase());
        Optional<Cliente> clienteBD = clienteRepository.findByUsuarioEmailOrCpf(cliente.getUsuario().getEmail(), cliente.getCpf());
        if (clienteBD.isPresent()) {
            ClienteDTO clienteDTOExistente = new ClienteDTO(clienteBD.get());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(clienteDTOExistente);
        } else {
            String senhaAleatoria = String.format("%04d", new Random().nextInt(10000));
            try {
                emailService.sendEmail(cliente.getUsuario().getEmail(), "Cadastro no LOL - Lavanderia On-Line", "Sua senha de acesso é: " + senhaAleatoria);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            cliente.getUsuario().setSenha(passwordEncoder.encode(senhaAleatoria));
            Cliente clienteSalvo = clienteRepository.save(cliente);
            ClienteDTO clienteDTOSalvo = new ClienteDTO(clienteSalvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOSalvo);
        }
    }

    @GetMapping("/consultar/{idUsuario}")
    public ResponseEntity<ClienteDTO> consultar(@PathVariable("idUsuario") Long idUsuario) {
        return clienteRepository.findByUsuarioIdUsuario(idUsuario).map(clienteBD -> {
            ClienteDTO clienteDTO = new ClienteDTO(clienteBD);
            return ResponseEntity.ok(clienteDTO);
        }).orElse(ResponseEntity.notFound().build());
    }
    
}
