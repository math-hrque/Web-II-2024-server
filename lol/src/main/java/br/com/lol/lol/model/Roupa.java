package br.com.lol.lol.model;

import java.io.Serializable;

import br.com.lol.lol.dto.RoupaDTO;
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

    @Column(name="ativo")
    @Setter @Getter
    private boolean ativo = true;

    public void cadastrar(RoupaDTO roupaDTO) {
        this.idRoupa = 0L;
        this.descricao = roupaDTO.getDescricao();
        this.preco = roupaDTO.getPreco();
        this.prazoDias = roupaDTO.getPrazoDias();
    }

    public void atualizar(Long idRoupa, RoupaDTO roupaDTO) {
        this.idRoupa = idRoupa;
        this.descricao = roupaDTO.getDescricao();
        this.preco = roupaDTO.getPreco();
        this.prazoDias = roupaDTO.getPrazoDias();
    }

    public void inativar() {
        this.ativo = false;
    }
}
