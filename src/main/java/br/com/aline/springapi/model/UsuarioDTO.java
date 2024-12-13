package br.com.aline.springapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioDTO {
    @NotBlank(message = "O nome deve ser informado!")
    @Size(min = 4,max = 50, message = "Nome não pode ser menor que {min} e maior que {max} caracteres")
    private String nome;

    @NotBlank(message = "O login deve ser informado!")
    @Size(min = 4,max = 30, message = "Login não pode ser menor que {min} e maior que {max} caracteres")
    private String login;

    @NotBlank(message = "A senha deve ser informado!")
    @Size(min = 4,max = 20, message = "Senha não pode ser menor que {min} e maior que {max} caracteres")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario mapToUsuario() {
        Usuario user = new Usuario();
        user.setNome(this.nome);
        user.setLogin(this.login);
        user.setSenha(this.senha);
        return user;
    }
}
