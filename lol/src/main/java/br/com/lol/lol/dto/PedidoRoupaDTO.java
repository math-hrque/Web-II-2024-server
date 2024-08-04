package br.com.lol.lol.dto;

import br.com.lol.lol.model.PedidoRoupa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PedidoRoupaDTO {
    @Setter @Getter
    private int quantidade;
    
    @Setter @Getter
    private RoupaDTO roupa;

    public PedidoRoupaDTO(PedidoRoupa pedidoRoupa) {
        this.quantidade = pedidoRoupa.getQuantidade();
        this.roupa = new RoupaDTO(pedidoRoupa.getRoupa());
    }
}
