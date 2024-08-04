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

import br.com.lol.lol.dto.EnderecoDTO;
import br.com.lol.lol.model.EnderecoApi;

@CrossOrigin
@RestController
@RequestMapping("/endereco")
public class EnderecoREST {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CEP_PATTERN = "\\d{8}";

    @GetMapping("/consultar/{cep}")
    public ResponseEntity<EnderecoDTO> consultar(@PathVariable("cep") String cep) {
        if (cep.matches(CEP_PATTERN)) {
            try {
                String url = "https://viacep.com.br/ws/" + cep + "/json/";
                EnderecoApi enderecoApi = restTemplate.getForObject(url, EnderecoApi.class);
                
                if (enderecoApi.getCep() == null) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
    
                EnderecoDTO enderecoDTO = new EnderecoDTO(enderecoApi);
                return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
    
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
