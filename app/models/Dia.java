package models;

import javax.persistence.*;

/**
 * Created by Caio on 20/04/2015.
 */
@Entity
@Table(name = "DIA")
public class Dia {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDia;
    @Column
    private Long c0;
    @Column
    private Long c1;
    @Column
    private Long c2;
    @Column
    private Long c3;
    @Column
    private Long c4;
    @Column
    private Long c5;
    @Column
    private Long c6;
    @Column
    private Long c7;
    @Column
    private Long c8;
    @Column
    private Long c9;
    @Column
    private Long c10;
    @Column
    private Long c11;
    @Column
    private Long c12;
    @Column
    private Long c13;
    @Column
    private Long c14;
    @Column
    private Long c15;
    @Column
    private Long c16;
    @Column
    private Long c17;
    @Column
    private Long c18;
    @Column
    private Long c19;
    @Column
    private Long c20;
    @Column
    private String data;

    public Dia(){

    }

    public Dia(String data) {
        this.data = data;
        this.c0=null;
        this.c1=null;
        this.c2=null;
        this.c3=null;
        this.c4=null;
        this.c5=null;
        this.c6=null;
        this.c7=null;
        this.c8=null;
        this.c9=null;
        this.c10=null;
        this.c11=null;
        this.c12=null;
        this.c13=null;
        this.c14=null;
        this.c15=null;
        this.c16=null;
        this.c17=null;
        this.c18=null;
        this.c19=null;
        this.c20=null;
    }

    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long idDia) {
        this.idDia = idDia;
    }

    public Long getC0() {
        return c0;
    }

    public void setC0(Long c0) {
        this.c0 = c0;
    }

    public Long getC1() {
        return c1;
    }

    public void setC1(Long c1) {
        this.c1 = c1;
    }

    public Long getC2() {
        return c2;
    }

    public void setC2(Long c2) {
        this.c2 = c2;
    }

    public Long getC3() {
        return c3;
    }

    public void setC3(Long c3) {
        this.c3 = c3;
    }

    public Long getC4() {
        return c4;
    }

    public void setC4(Long c4) {
        this.c4 = c4;
    }

    public Long getC5() {
        return c5;
    }

    public void setC5(Long c5) {
        this.c5 = c5;
    }

    public Long getC6() {
        return c6;
    }

    public void setC6(Long c6) {
        this.c6 = c6;
    }

    public Long getC7() {
        return c7;
    }

    public void setC7(Long c7) {
        this.c7 = c7;
    }

    public Long getC8() {
        return c8;
    }

    public void setC8(Long c8) {
        this.c8 = c8;
    }

    public Long getC9() {
        return c9;
    }

    public void setC9(Long c9) {
        this.c9 = c9;
    }

    public Long getC10() {
        return c10;
    }

    public void setC10(Long c10) {
        this.c10 = c10;
    }

    public Long getC11() {
        return c11;
    }

    public void setC11(Long c11) {
        this.c11 = c11;
    }

    public Long getC12() {
        return c12;
    }

    public void setC12(Long c12) {
        this.c12 = c12;
    }

    public Long getC13() {
        return c13;
    }

    public void setC13(Long c13) {
        this.c13 = c13;
    }

    public Long getC14() {
        return c14;
    }

    public void setC14(Long c14) {
        this.c14 = c14;
    }

    public Long getC15() {
        return c15;
    }

    public void setC15(Long c15) {
        this.c15 = c15;
    }

    public Long getC16() {
        return c16;
    }

    public void setC16(Long c16) {
        this.c16 = c16;
    }

    public Long getC17() {
        return c17;
    }

    public void setC17(Long c17) {
        this.c17 = c17;
    }

    public Long getC18() {
        return c18;
    }

    public void setC18(Long c18) {
        this.c18 = c18;
    }

    public Long getC19() {
        return c19;
    }

    public void setC19(Long c19) {
        this.c19 = c19;
    }

    public Long getC20() {
        return c20;
    }

    public void setC20(Long c20) {
        this.c20 = c20;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
