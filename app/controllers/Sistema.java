package controllers;

import models.*;
import play.db.jpa.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Caio on 15/04/2015.
 */
public class Sistema {

    private static GenericDAO dao = new GenericDAO();

    /*
    Aqui fica tudo relacionado a Usuario
     */

    public static boolean addUsuario(Usuario u){
        if(u!=null && u.getNome()!="" && u.getSenha()!=""){
            dao.persist(u);
            dao.flush();
            return true;
        }
        return false;
    }

    public static boolean removeUsuario(Long id){
        Usuario u = dao.findByEntityId(Usuario.class,id);
        if(u!=null){
            dao.remove(u);
            dao.flush();
            return true;
        }
        return false;
    }

    public static Usuario getUsuario(String email){
        List<Usuario> l = dao.findByAttributeName(Usuario.class.getName(),"email",email);
        if(l.size()>0){
            return l.get(0);
        }
        return null;
    }

    public static List<Usuario> getTodosUsuarios(){
        return dao.findAllByClassName(Usuario.class.getName());
    }

    /*
    Finish tudo relacionado a Usuario
     */

     /*
    Inicia tudo relacionado a Agenda
     */

    public static Dia getDia(String data){
        List<Dia> l = dao.findByAttributeName(Dia.class.getName(),"data",data);
        if(l.size()>0){
            return l.get(0);
        }
        return null;
    }

    public static Dia getDia(Long id){
        return dao.findByEntityId(Dia.class,id);
    }

    public static Corte getCorte(Long idCorte){
        return dao.findByEntityId(Corte.class,idCorte);
    }

    public static List<Corte> getListaCortesDia(String data){
        return dao.findByAttributeName(Corte.class.getName(),"data",data);
    }

    public static List<Corte> getListaOrdenadaCorteDia(String data){
        List<Corte> l = getListaCortesDia(data);
        List<Corte> li = new ArrayList<>();
        List<Corte> aux = new ArrayList<>();
        Corte[] temp = new Corte[21];
        for(Corte c: l){
            if(Util.getPos(c.getHora())!=-1){
                temp[Util.getPos(c.getHora())]=c;
            }
        }
        for(int i=0;i<temp.length;i++){
            aux.add(temp[i]);
        }
        return aux;
    }

    public static boolean existeCorte(Corte c){
        for(Corte corte: getListaCortesDia(c.getData())){
            if(corte.getHora().equals(c.getHora())){
                return true;
            }
        }
        return false;
    }

    public static void agendarCorte(Corte c){
        if(!existeCorte(c)){
            dao.persist(c);
            dao.flush();
        }
    }

    public static void desmarcarCorte(Long id){
        Corte c = dao.findByEntityId(Corte.class,id);
        if(c!=null){
            dao.remove(c);
            dao.flush();
        }
    }

/*public static void main(String [] args){
    Corte[] temp = new Corte[21];
    List<Corte> li = new ArrayList<>();
    List<Corte> aux = new ArrayList<>();
    List<Corte> l = new ArrayList<>();
    Usuario u = new Usuario(null,null,null,0,null,null,null);
    String data = "20/04/2015";
    String hora = "18:00";
    l.add(new Corte(hora,data,Util.getPos(hora),null));
    for(Corte c: l){
        if(Util.getPos(c.getHora())!=-1){
            temp[Util.getPos(c.getHora())]=c;
        }
    }
    for(int i=0;i<temp.length;i++){
        aux.add(temp[i]);
    }

    System.out.println(aux.toString());
}*/



     /*
    Finish tudo relacionado a Usuario
     */
}