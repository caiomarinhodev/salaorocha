package models;

import javax.persistence.*;

/**
 * Created by Caio on 21/04/2015.
 */
@Entity
@Table(name = "MENSAGEM")
public class Mensagem {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensagem;
    @Column
    private Long idUsuario;
    @Column
    private String data;
    @Column
    private String mensagem;

    public Mensagem(Long idUsuario, String data, String mensagem) {
        this.idUsuario = idUsuario;
        this.data = data;
        this.mensagem = mensagem;
    }

    public Mensagem(){

    }

    public Long getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
