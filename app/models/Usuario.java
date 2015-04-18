package models;

import javax.persistence.*;

/**
 * Created by Caio on 15/04/2015.
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private int tipo;
    @Column
    private String telefone;
    @Column
    private String foto;
    @Column
    private String endereco;

    public Usuario(){

    }

    public Usuario(String nome, String email, String senha, int tipo, String telefone, String foto, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.telefone = telefone;
        this.foto = foto;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        if (tipo != usuario.tipo) return false;
        if (email != null ? !email.equals(usuario.email) : usuario.email != null) return false;
        if (!nome.equals(usuario.nome)) return false;
        if (senha != null ? !senha.equals(usuario.senha) : usuario.senha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nome.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (senha != null ? senha.hashCode() : 0);
        result = 31 * result + tipo;
        return result;
    }
}