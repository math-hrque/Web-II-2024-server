package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pedido_roupa")
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRoupa implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_pedido_roupa")
    @Setter @Getter
    private Long idPedidoRoupa;

    @Column(name="quantidade")
    @Setter @Getter
    private int quantidade;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pedido")
    @Setter @Getter
    private Pedido pedido;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_roupa")
    @Setter @Getter
    private Roupa roupa;
}
