package br.com.lol.lol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ClienteFielDTO {
    @Setter @Getter
    private ClienteDTO cliente;

    @Setter @Getter
    private int quantidadePedidos;

    @Setter @Getter
    private Double receita;
}
