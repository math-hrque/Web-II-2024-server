package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="funcionario")
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_funcionario")
    @Setter @Getter
    private Long idFuncionario;

    @Column(name="nome")
    @Setter @Getter
    private String nome;

    @Temporal(TemporalType.DATE)
    @Column(name="data_nascimento")
    @Setter @Getter
    private LocalDate dataNascimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @Setter @Getter
    private Usuario usuario;
}
