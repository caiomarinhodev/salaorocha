package controllers;

import models.Corte;
import models.Mensagem;
import models.Usuario;
import models.Util;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application extends Controller {

    @Transactional
    public static Result index() {
        return ok(index.render());
    }

    /*
        Inicia mapper para render login e registro
     */

    @Transactional
    public static Result renderlogin(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        if(u!=null){
            if(u.getTipo()==0){
                return renderAgendaAdmin(null);
            }else{
                return renderAgendar(null);
            }
        }
        return ok(login.render());
    }

    public static Result renderRegistro(){
        return ok(register.render());
    }

    @Transactional
    public static Result register(){
        DynamicForm r = Form.form().bindFromRequest();
        String nome, email, senha, telefone,data,endereco;
        nome = r.get("nome");
        email = r.get("email");
        senha = r.get("senha");
        data = r.get("data");
        telefone = r.get("telefone");
        endereco = r.get("endereco");
        int tipo = 1;
        Usuario u = new Usuario(nome,email,senha,tipo,telefone,data,endereco);
        if(Sistema.addUsuario(u)){
            return renderlogin();
        }
        return renderRegistro();
    }

    @Transactional
    public static Result enter(){
        DynamicForm r = Form.form().bindFromRequest();
        String email, senha;
        email = r.get("email");
        senha = r.get("senha");
        Usuario u = Sistema.getUsuario(email);
        if(u!=null && senha!=""){
            session().put("email",email);
            if(u.getTipo()==0){
                return renderAgendaAdmin(null);
            }else{
                return renderAgendar(null);
            }
        }
        return renderlogin();
    }

    @Transactional
    public static Result deslogar(){
        session().clear();
        return index();
    }

    /*
        Termina mapper render login e registro
     */

    /*
        Inicia mapper para agenda admin e usuario
     */

    @Transactional
    public static Result renderAgendaAdmin(String data){
        Usuario u = Sistema.getUsuario(session().get("email"));
        if(data==null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            String da = df.format(d);
            Logger.info("DATA:"+ da);
            return ok(agendadmin.render(u, Sistema.getListaOrdenadaCorteDia(da), da));
        }else if(data==""){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            String da = df.format(d);
            Logger.info("DATA:"+ da);
            return ok(agendadmin.render(u, Sistema.getListaOrdenadaCorteDia(da), da));
        }else{
            Logger.info("DATA:"+ data);
            return ok(agendadmin.render(u, Sistema.getListaOrdenadaCorteDia(data), data));
        }
    }

    @Transactional
    public static Result renderAgendar(String data){
        Usuario u = Sistema.getUsuario(session().get("email"));
        if(data==null){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            String da = df.format(d);
            Logger.info("DATA:"+ da);
            return ok(agendar.render(u, Sistema.getListaOrdenadaCorteDia(da),da));
        }else if(data==""){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date d = new Date();
            String da = df.format(d);
            Logger.info("DATA:"+ da);
            return ok(agendar.render(u, Sistema.getListaOrdenadaCorteDia(da),da));
        }else{
            Logger.info("DATA:"+ data);
            return ok(agendar.render(u, Sistema.getListaOrdenadaCorteDia(data),data));
        }

    }

    @Transactional
    public static Result search(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        DynamicForm d = Form.form().bindFromRequest();
        String data = d.get("dataAtual");
        if(u.getTipo()==0){
            return renderAgendaAdmin(data);
        }
        return renderAgendar(data);
    }

    @Transactional
    public static Result agendaCorte(){
        DynamicForm d = Form.form().bindFromRequest();
        String hora = d.get("hora");
        String data = d.get("dataAgora");
        int horaPos = Util.getPos(hora);
        Usuario u = Sistema.getUsuario(session().get("email"));
        Corte co = new Corte(hora,data,horaPos,u.getIdUsuario());
        Sistema.agendarCorte(co);
        if(u.getTipo()==0){
            return renderAgendaAdmin(data);
        }
        return renderAgendar(data);
    }

    @Transactional
    public static Result barraAgendaU(){
        return renderAgendar(null);
    }

    @Transactional
    public static Result barraAgendaA(){
        return renderAgendaAdmin(null);
    }


    /*
        Termina mapper para agenda admin e usuario
     */

    /*
        Inicia mapper para Chat admin e usuario
     */

    @Transactional
    public static Result chatu(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        return ok(chat.render(u, Sistema.getListaDeMensagens()));
    }

    @Transactional
    public static Result chata(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        return ok(chatadmin.render(u, Sistema.getListaDeMensagens()));
    }

    @Transactional
    public static Result addMensagem(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        DynamicForm r = Form.form().bindFromRequest();
        String mensagem = r.get("mensagem");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        String data = df.format(d);
        Mensagem me = new Mensagem(u.getIdUsuario(),data,mensagem);
        Sistema.addMensagem(me);
        if(u.getTipo()==0){
            return chata();
        }
        return chatu();
    }
    @Transactional
    public static Result removeMensagem(Long i){
        Usuario u = Sistema.getUsuario(session().get("email"));
        Sistema.removeMensagem(i);
        if(u.getTipo()==0){
            return ok(chatadmin.render(u, Sistema.getListaDeMensagens()));
        }
        return ok(chat.render(u, Sistema.getListaDeMensagens()));
    }

    /*
        Termina mapper para Chat admin e usuario
     */

    @Transactional
    public static Result renderOutros(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        return ok(outrosadmin.render(u,Sistema.getTodosUsuarios()));
    }

}
