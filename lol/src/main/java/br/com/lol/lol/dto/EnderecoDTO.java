package br.com.lol.lol.dto;

import br.com.lol.lol.model.Endereco;
import br.com.lol.lol.model.EnderecoApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    @Setter @Getter
    private String cep;

    @Setter @Getter
    private String uf;

    @Setter @Getter
    private String cidade;

    @Setter @Getter
    private String bairro;

    @Setter @Getter
    private String rua;

    @Setter @Getter
    private String numero;

    @Setter @Getter
    private String complemento;

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.uf = endereco.getUf();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
    }

    public EnderecoDTO(EnderecoApi enderecoApi) {
        this.cep = enderecoApi.getCep();
        this.uf = enderecoApi.getUf();
        this.cidade = enderecoApi.getCidade();
        this.bairro = enderecoApi.getBairro();
        this.rua = enderecoApi.getRua();
    }
}
