package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.time.OffsetDateTime;

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

    @Column(name="numero_pedido", unique = true)
    @Setter @Getter
    private Long numeroPedido;

    @Column(name="data_pedido")
    @Setter @Getter
    private OffsetDateTime dataPedido;

    @Column(name="data_pagamento")
    @Setter @Getter
    private OffsetDateTime dataPagamento;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_cliente")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_cliente")
    @Setter @Getter
    private Cliente cliente;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_situacao")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_situacao")
    @Setter @Getter
    private Situacao situacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_orcamento")
    @Setter @Getter
    private Orcamento orcamento;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="pedido_roupa", joinColumns=@JoinColumn(name="id_pedido"), inverseJoinColumns=@JoinColumn(name="id_roupa"))
    @Setter @Getter
    private ArrayList<PedidoRoupa> listaPedidoRoupas;
}
