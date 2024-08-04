package br.com.lol.lol.dto;

import java.time.LocalDate;

import br.com.lol.lol.model.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    @Setter @Getter
    private Long idFuncionario;

    @Setter @Getter
    private LocalDate dataNascimento;

    @Setter @Getter
    private UsuarioResponseDTO usuario;

    public FuncionarioDTO(Funcionario funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.dataNascimento = funcionario.getDataNascimento();
        this.usuario = new UsuarioResponseDTO(funcionario.getUsuario());
    }
}
