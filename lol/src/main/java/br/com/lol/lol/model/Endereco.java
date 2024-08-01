package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="endereco")
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_endereco")
    @Setter @Getter
    private Long idEndereco;

    @Column(name="cep")
    @Setter @Getter
    private String cep;

    @Column(name="uf")
    @Setter @Getter
    private String uf;

    @Column(name="cidade")
    @Setter @Getter
    private String cidade;

    @Column(name="bairro")
    @Setter @Getter
    private String bairro;

    @Column(name="rua")
    @Setter @Getter
    private String rua;

    @Column(name="numero")
    @Setter @Getter
    private String numero;

    @Column(name="complemento")
    @Setter @Getter
    private String complemento;
}
