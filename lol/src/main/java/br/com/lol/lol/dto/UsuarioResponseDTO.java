package br.com.lol.lol.dto;

import br.com.lol.lol.enums.TipoPermissao;
import br.com.lol.lol.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {
    @Setter @Getter
    private Long idUsuario;

    @Setter @Getter
    private String email;

    @Setter @Getter
    private String nome;

    @Setter @Getter
    private TipoPermissao tipoPermissao;

    public UsuarioResponseDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.tipoPermissao = usuario.getPermissao().getTipoPermissao();
    }
}
