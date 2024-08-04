package br.com.lol.lol.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lol.lol.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumeroPedido(Long numeroPedido);
    Optional<List<Pedido>> findByClienteIdCliente(Long idCliente);
    Optional<List<Pedido>> findByDataPagamentoBetween(LocalDateTime dataDe, LocalDateTime dataAte);
}
