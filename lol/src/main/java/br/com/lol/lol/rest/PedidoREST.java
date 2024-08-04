package br.com.lol.lol.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lol.lol.dto.PedidoDTO;
import br.com.lol.lol.model.Pedido;
import br.com.lol.lol.model.Situacao;
import br.com.lol.lol.repository.PedidoRepository;
import br.com.lol.lol.repository.SituacaoRepository;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoREST {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<PedidoDTO> cadastrar(@RequestBody PedidoDTO pedidoDTO) {
        // if (pedidoDTO.getNumeroPedido() != null) {
            // Optional<Pedido> pedidoBD = pedidoRepository.findByNumeroPedido(pedidoDTO.getNumeroPedido());
            // if (pedidoBD.isPresent()) {
            //     PedidoDTO pedidoDTOExistente = new PedidoDTO(pedidoBD.get());
            //     return ResponseEntity.status(HttpStatus.CONFLICT).body(pedidoDTOExistente);
            // } else {
                Pedido pedido = new Pedido();
                pedido.cadastrar(pedidoDTO);
                Optional<Situacao> situacao = situacaoRepository.findByTipoSituacao(pedido.getSituacao().getTipoSituacao());
                if (situacao.isPresent()) {
                    pedido.setSituacao(situacao.get());
                    Pedido pedidoSalva = pedidoRepository.save(pedido);
                    PedidoDTO pedidoDTOSalva = new PedidoDTO(pedidoSalva);
                    return ResponseEntity.status(HttpStatus.CREATED).body(pedidoDTOSalva);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

            // }
        // } else {
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        // }
    }

    @GetMapping("/consultar/{numeroPedido}")
    public ResponseEntity<PedidoDTO> consultar(@PathVariable("numeroPedido") Long numeroPedido) {
        return pedidoRepository.findByNumeroPedido(numeroPedido).map(pedidoBD -> {
            PedidoDTO pedidoDTO = new PedidoDTO(pedidoBD);
            return ResponseEntity.ok(pedidoDTO);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoDTO>> listar() {
        List<Pedido> listaPedidoBD = pedidoRepository.findAll();
        if (listaPedidoBD.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<PedidoDTO> listaPedidoDTO = listaPedidoBD.stream().map(pedido -> {
                PedidoDTO pedidoDTO = new PedidoDTO(pedido);
                return pedidoDTO;
            }).collect(Collectors.toList());

            return ResponseEntity.ok(listaPedidoDTO);
        }
    }

    @GetMapping("/listarPorCliente/{idCliente}")
    public ResponseEntity<List<PedidoDTO>> listarPorCliente(@PathVariable("idCliente") Long idCliente) {
        Optional<List<Pedido>> listaPedidoBD = pedidoRepository.findByClienteIdCliente(idCliente);
        if (listaPedidoBD.get().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<PedidoDTO> listaPedidoDTO = listaPedidoBD.get().stream().map(pedido -> {
                PedidoDTO pedidoDTO = new PedidoDTO(pedido);
                return pedidoDTO;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(listaPedidoDTO);
        }
    }
}
