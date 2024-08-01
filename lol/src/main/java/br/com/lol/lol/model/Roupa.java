package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roupa")
@NoArgsConstructor
@AllArgsConstructor
public class Roupa implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_roupa")
    @Setter @Getter
    private Long idRoupa;

    @Column(name="descricao")
    @Setter @Getter
    private String descricao;

    @Column(name="preco")
    @Setter @Getter
    private double preco;

    @Column(name="prazo_dias")
    @Setter @Getter
    private int prazoDias;
}
