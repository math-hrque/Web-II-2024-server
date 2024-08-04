package br.com.lol.lol.model;

import java.io.Serializable;

import br.com.lol.lol.dto.ClienteDTO;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_cliente")
    @Setter @Getter
    private Long idCliente;

    @Column(name="cpf", unique = true)
    @Setter @Getter
    private String cpf;

    @Column(name="telefone")
    @Setter @Getter
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @Setter @Getter
    private Usuario usuario;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="id_endereco")
    @Setter @Getter
    private Endereco endereco;
}
