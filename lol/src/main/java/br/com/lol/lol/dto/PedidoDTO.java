package br.com.lol.lol.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.lol.lol.enums.TipoSituacao;
import br.com.lol.lol.model.Orcamento;
import br.com.lol.lol.model.Pedido;
import br.com.lol.lol.model.PedidoRoupa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @Setter @Getter
    private Long numeroPedido;

    @Setter @Getter
    private LocalDateTime dataPedido;

    @Setter @Getter
    private LocalDateTime dataPagamento;

    @Setter @Getter
    private Long idCliente;

    @Setter @Getter
    private TipoSituacao situacao;

    @Setter @Getter
    private Orcamento orcamento;

    @Setter @Getter
    private List<PedidoRoupaDTO> listaPedidoRoupas;

    public PedidoDTO(Pedido pedido) {
        this.numeroPedido = pedido.getNumeroPedido();
        this.dataPedido = pedido.getDataPedido();
        this.dataPagamento = pedido.getDataPagamento();
        this.idCliente = pedido.getCliente().getIdCliente();
        this.situacao = pedido.getSituacao().getTipoSituacao();
        this.orcamento = pedido.getOrcamento();
        List<PedidoRoupaDTO> listaPedidoRoupas = new ArrayList<>();
        for (PedidoRoupa pedidoRoupa : pedido.getListaPedidoRoupas()) {
            listaPedidoRoupas.add(new PedidoRoupaDTO(pedidoRoupa));
        }
        this.listaPedidoRoupas = listaPedidoRoupas;
    }
}
