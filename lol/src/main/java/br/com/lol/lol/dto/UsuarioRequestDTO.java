package br.com.lol.lol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {
    @Setter @Getter
    private String email;

    @Setter @Getter
    private String senha;
}
