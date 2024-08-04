package br.com.lol.lol.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dto.RoupaDTO;
import br.com.lol.lol.model.Roupa;
import br.com.lol.lol.repository.RoupaRepository;

@CrossOrigin
@RestController
@RequestMapping("/roupa")
public class RoupaREST {

    @Autowired
    private RoupaRepository roupaRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<RoupaDTO> cadastrar(@RequestBody RoupaDTO roupaDTO) {
        if (roupaDTO.getIdRoupa() == null) {
            roupaDTO.setIdRoupa(0L);
        }
        Optional<Roupa> roupaBD = roupaRepository.findById(roupaDTO.getIdRoupa());
        if (roupaBD.isPresent()) {
            RoupaDTO roupaDTOExistente = new RoupaDTO(roupaBD.get());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(roupaDTOExistente);
        } else {
            Roupa roupa = new Roupa();
            roupa.cadastrar(roupaDTO);
            Roupa roupaSalva = roupaRepository.save(roupa);
            RoupaDTO roupaDTOSalva = new RoupaDTO(roupaSalva);
            return ResponseEntity.status(HttpStatus.CREATED).body(roupaDTOSalva);
        }

    }

    @PutMapping("/atualizar/{idRoupa}")
    public ResponseEntity<RoupaDTO> atualizar(@PathVariable("idRoupa") Long idRoupa, @RequestBody RoupaDTO roupaDTO) {
        return roupaRepository.findById(idRoupa).map(roupaBD -> {
            Roupa roupa = new Roupa();
            roupa.atualizar(idRoupa, roupaDTO);
            Roupa roupaSalva = roupaRepository.save(roupa);
            RoupaDTO roupaDTOSalva = new RoupaDTO(roupaSalva);
            return ResponseEntity.ok(roupaDTOSalva);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/inativar/{idRoupa}")
    public ResponseEntity<RoupaDTO> inativar(@PathVariable("idRoupa") Long idRoupa) {
        return roupaRepository.findById(idRoupa).map(roupaBD -> {
            roupaBD.inativar();
            Roupa roupaInativada = roupaRepository.save(roupaBD);
            RoupaDTO roupaDTOInativada = new RoupaDTO(roupaInativada);
            return ResponseEntity.ok(roupaDTOInativada);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RoupaDTO>> listar() {
        Optional<List<Roupa>> listaRoupaBD = roupaRepository.findByAtivo(true);
        if (listaRoupaBD.isPresent()) {
            List<RoupaDTO> listaRoupaDTO = listaRoupaBD.get().stream().map(roupa -> new RoupaDTO(roupa)).collect(Collectors.toList());
            return ResponseEntity.ok(listaRoupaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
