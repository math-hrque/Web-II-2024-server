package br.com.lol.lol.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ClienteFiel {
    @Setter @Getter
    private Cliente cliente;

    @Setter @Getter
    private int quantidadePedidos;

    @Setter @Getter
    private double receita;
}
