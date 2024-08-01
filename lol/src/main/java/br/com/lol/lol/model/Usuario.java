package br.com.lol.lol.model;

import java.io.Serializable;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="usuario")
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usuario")
    @Setter @Getter
    private Long idUsuario;

    @Column(name="email", unique = true)
    @Setter @Getter
    private String email;

    @Column(name="senha")
    @Setter @Getter
    private String senha;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_permissao")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_permissao")
    @Setter @Getter
    private Permissao permissao;
}
