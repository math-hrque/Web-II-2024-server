package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import br.com.lol.lol.dto.PedidoDTO;
import br.com.lol.lol.dto.PedidoRoupaDTO;
import br.com.lol.lol.enums.TipoSituacao;

import java.time.LocalDateTime;

@Entity
@Table(name="pedido")
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido")
    @Setter @Getter
    private Long idPedido;

    @Column(name="numero_pedido",  unique = true, insertable = false, updatable = false)
    @SequenceGenerator(name = "numero_pedido_seq", sequenceName = "numero_pedido_sequencia", initialValue = 10000, allocationSize = 1)
    @Setter @Getter
    private Long numeroPedido;

    @Column(name="data_pedido")
    @Setter @Getter
    private LocalDateTime dataPedido;

    @Column(name="data_pagamento")
    @Setter @Getter
    private LocalDateTime dataPagamento;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_cliente")
    @Setter @Getter
    private Cliente cliente;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_situacao")
    @Setter @Getter
    private Situacao situacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orcamento")
    @Setter @Getter
    private Orcamento orcamento;

    @OneToMany(mappedBy = "pedido", fetch=FetchType.LAZY, orphanRemoval = true)
    @Setter @Getter
    private List<PedidoRoupa> listaPedidoRoupas;

    public void cadastrar(PedidoDTO pedidoDTO) {
        this.cliente = new Cliente();
        this.situacao = new Situacao();
        this.orcamento = new Orcamento();
        List<PedidoRoupa> listaPedidoRoupas = new ArrayList<>();
        this.dataPedido = pedidoDTO.getDataPedido();
        this.cliente.setIdCliente(pedidoDTO.getIdCliente());
        if (pedidoDTO.getOrcamento().isAprovado()) {
            this.situacao.setTipoSituacao(TipoSituacao.EM_ABERTO);
        } else {
            this.situacao.setTipoSituacao(TipoSituacao.REJEITADO);
        }
        //this.numeroPedido = 0L;
        this.orcamento = pedidoDTO.getOrcamento();
        for (PedidoRoupaDTO pedidoRoupaDTO  : pedidoDTO.getListaPedidoRoupas()) {
            PedidoRoupa pedidoRoupa = new PedidoRoupa();
            pedidoRoupa.setQuantidade(pedidoRoupaDTO.getQuantidade());
            Roupa roupa = new Roupa();
            roupa.atualizar(pedidoRoupaDTO.getRoupa().getIdRoupa(), pedidoRoupaDTO.getRoupa());
            pedidoRoupa.setRoupa(roupa);
            listaPedidoRoupas.add(pedidoRoupa);
        }
        this.listaPedidoRoupas = listaPedidoRoupas;
    }



}
