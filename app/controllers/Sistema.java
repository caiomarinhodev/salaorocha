package controllers;

import models.*;

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

    public static List<Corte> getTodosCortes(){
        return dao.findAllByClassName(Corte.class.getName());
    }

    public static List<Usuario> getAdmins(){
        List<Usuario> l = dao.findByAttributeName(Usuario.class.getName(),"tipo","0");
        return l;
    }

    public static void alimentabd(){
        List<Usuario> lu = getTodosUsuarios();
        List<Corte> lc = getTodosCortes();
        List<Mensagem> lm = getListaDeMensagens();

        for(Mensagem m: lm){
            dao.remove(m);
        }
        for(Corte c: lc){
            dao.remove(c);
        }
        for(Usuario u: lu){
            dao.remove(u);
        }
        dao.flush();

        for(Usuario u: lu){
            addUsuario(u);
        }


    }

    public static void alg(){
        List<Corte> lc = getTodosCortes();
        for(Corte c: lc){
            Usuario u = getUsuario(c.getClienteId());
            u.setCorteId(null);
            dao.merge(u);
            dao.remove(c);
            dao.flush();
        }

    }

    public static Usuario getUsuario(Long id){
        return dao.findByEntityId(Usuario.class,id);
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

    public static boolean existeAdmin(){
        Usuario u = getUsuario("admin");
        if(u!=null){
            return true;
        }
        return false;
    }

    public static void deletacristiano(){
        List<Usuario> l = dao.findByAttributeName(Usuario.class.getName(),"email","admin");
        if(l.size()>0){
            for(int i=0; i<l.size();i++){
                dao.remove(l.get(i));
            }
        }
        dao.flush();
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

    public static Corte getCorte(Long idCorte){
       List<Corte> l = dao.findByAttributeName(Corte.class.getName(),"idCorte", String.valueOf(idCorte));
        if(l.size()>0){
            return l.get(0);
        }
        return null;
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

    public static Usuario getAdmin(){
        List<Usuario> l = dao.findByAttributeName(Usuario.class.getName(),"tipo",String.valueOf(0));
        if(l.size()>0){
            return l.get(0);
        }
        return null;
    }

    public static void agendarCorte(Corte c){
        Usuario admin = getAdmin();
        Usuario u = getUsuario(c.getClienteId());
        if(!existeCorte(c)){
            if(c.getClienteId()==admin.getId()){
                dao.persist(c);
            }else{
                if(u.getCorteId()!=null){
                    Corte corte = getCorte(u.getCorteId());
                    dao.remove(corte);
                }
                dao.persist(c);
                u.setCorteId(c.getIdCorte());
                dao.merge(u);
            }
            dao.flush();
        }
    }

    public static void desmarcarCorte(Long id){
        Corte c = dao.findByEntityId(Corte.class,id);
        if(c!=null){
            Usuario u = getUsuario(c.getClienteId());
            u.setCorteId(null);
            dao.merge(u);
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

    /*
    Inicia tudo relacionado a Chat
     */
    public static List<Mensagem> getListaDeMensagens(){
        return dao.findAllByClassName(Mensagem.class.getName());
    }

    public static Mensagem getMensagem(Long idMensagem){
        return dao.findByEntityId(Mensagem.class,idMensagem);
    }

    public static List<Mensagem> getListaMensagensDoUsuario(Long idUsuario){
        return dao.findByAttributeName(Mensagem.class.getName(),"idUsuario", String.valueOf(idUsuario));
    }

    public static void addMensagem(Mensagem m){
        dao.persist(m);
        dao.flush();
    }

    public static void removeMensagem(Long id){
        Mensagem me = getMensagem(id);
        if(me!=null){
            dao.remove(me);
            dao.flush();
        }
    }
    /*
    Finish tudo relacionado a Chat
     */


    public static int getTotalUsadoBd(){
        List<Usuario> lu = getTodosUsuarios();
        List<Corte> lc = dao.findAllByClassName(Corte.class.getName());
        List<Mensagem> lm = getListaDeMensagens();

        return (lu.size()+lc.size()+lm.size());
    }
}