package br.com.lol.lol.service;

import org.springframework.stereotype.Service;

import br.com.lol.lol.model.Endereco;
import br.com.lol.lol.model.EnderecoApi;

@Service
public class EnderecoService {

    public Endereco convertEnderecoApiToEndereco(EnderecoApi enderecoApi) {
        if (enderecoApi == null) {
            return null;
        }

        Endereco endereco = new Endereco();
        endereco.setCep(enderecoApi.getCep());
        endereco.setUf(enderecoApi.getUf());
        endereco.setCidade(enderecoApi.getCidade());
        endereco.setBairro(enderecoApi.getBairro());
        endereco.setRua(enderecoApi.getRua());
        return endereco;
    }
    
}
