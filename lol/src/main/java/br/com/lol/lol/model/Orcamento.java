package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="orcamento")
@NoArgsConstructor
@AllArgsConstructor
public class Orcamento implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_orcamento")
    @Setter @Getter
    private Long idOrcamento;

    @Column(name="valor")
    @Setter @Getter
    private Double valor;

    @Temporal(TemporalType.DATE)
    @Column(name="data_prazo")
    @Setter @Getter
    private LocalDate dataPrazo;

    @Column(name="aprovado")
    @Setter @Getter
    private boolean aprovado;
}
