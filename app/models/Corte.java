package models;

import javax.persistence.*;

/**
 * Created by Caio on 20/04/2015.
 */
@Entity
@Table(name = "CORTE")
public class Corte {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCorte;
    @Column
    private String hora;
    @Column
    private String data;
    @Column
    private int horaPos;
    @Column
    private Long clienteId;
    @Column
    private String nomeCliente;

    public Corte(){

    }

    public Corte(String hora, String data, int horaPos, Long clienteId, String nomeCliente) {
        this.hora = hora;
        this.data = data;
        this.horaPos = horaPos;
        this.clienteId = clienteId;
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getIdCorte() {
        return idCorte;
    }

    public void setIdCorte(Long idCorte) {
        this.idCorte = idCorte;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHoraPos() {
        return horaPos;
    }

    public void setHoraPos(int horaPos) {
        this.horaPos = horaPos;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
