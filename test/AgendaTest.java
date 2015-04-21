import controllers.Sistema;
import models.Corte;
import models.Usuario;
import models.Util;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Caio.
 */

public class AgendaTest extends AbstractTest {
    @Test
    public void deveAgendarUmCorte(){
        Usuario u = Sistema.getUsuario("u1");
        String hora = "18:00";
        String data = "20/04/2015";
        Corte c = new Corte(hora,data, Util.getPos(hora),u.getIdUsuario());
        Sistema.agendarCorte(c);
        List<Corte> l = Sistema.getListaOrdenadaCorteDia(data);
        Assert.assertEquals(true,Sistema.existeCorte(c));
    }

    @Test
    public void deveRecusarAgendamentoDeCorte(){
        Usuario u = Sistema.getUsuario("u1");
        Usuario u2 = Sistema.getUsuario("u2");
        String hora = "18:00";
        String data = "20/04/2015";
        Corte c = new Corte(hora,data, Util.getPos(hora),u.getIdUsuario());
        Corte c2 = new Corte(hora,data,Util.getPos(hora),u2.getIdUsuario());
        Sistema.agendarCorte(c);
        Sistema.agendarCorte(c2);
        List<Corte> l = Sistema.getListaOrdenadaCorteDia(data);
        System.out.println(l.toString());
        Corte co = null;
        for(int i =0; i<l.size();i++){
            if(l.get(i)!=null){
                co = l.get(i);
            }
        }
        Assert.assertEquals(u.getCorteId(), co.getIdCorte());
    }

    @Test
    public void verificaListaOrdenadaDoDia(){
        Usuario u = Sistema.getUsuario("u1");
        String hora = "18:00";
        String data = "20/04/2015";
        Corte c = new Corte(hora,data, Util.getPos(hora),u.getIdUsuario());
        Corte c2 = new Corte("17:30", data,Util.getPos("18:30"),u.getIdUsuario());
        Sistema.agendarCorte(c);
        Sistema.agendarCorte(c2);
        List<Corte> l = Sistema.getListaOrdenadaCorteDia(data);
        Assert.assertEquals(21,l.size());
    }

}

