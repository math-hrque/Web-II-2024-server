package br.com.lol.lol.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDTO {
    @Setter @Getter
    private LocalDate data;

    @Setter @Getter
    private Double receita;
}
