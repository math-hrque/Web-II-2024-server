package br.com.lol.lol.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.lol.lol.model.Endereco;
import br.com.lol.lol.model.EnderecoApi;
import br.com.lol.lol.service.EnderecoService;

@CrossOrigin
@RestController
@RequestMapping("/endereco")
public class EnderecoREST {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<Endereco> consultar(@PathVariable("cep") String cep) {
        try {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            EnderecoApi enderecoApi = restTemplate.getForObject(url, EnderecoApi.class);
            
            if (enderecoApi == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Endereco endereco = enderecoService.convertEnderecoApiToEndereco(enderecoApi);
            return new ResponseEntity<>(endereco, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
