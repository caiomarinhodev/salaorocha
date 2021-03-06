package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Corte;
import models.Mensagem;
import models.Usuario;
import models.Util;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static Result renderRegistro(String message){
        return ok(register.render(message));
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
        String foto = "https://cdn4.iconfinder.com/data/icons/meBaze-Freebies/512/user.png";
        int tipo = 1;
        Usuario usuario = Sistema.getUsuario(email);
        if(usuario == null){
            Usuario u = new Usuario(nome,email,senha,tipo,telefone,data,endereco, foto);
            Sistema.addUsuario(u);
            return renderlogin();
        }
        return renderRegistro("Email já Cadastrado !");

    }

    @Transactional
    public static Result enter(){
        DynamicForm r = Form.form().bindFromRequest();
        String email, senha;
        email = r.get("email");
        senha = r.get("senha");
        Usuario u = Sistema.getUsuario(email);
        if(u!=null && senha!=""){
            if(u.getTipo()==0){
                if(senha.equals(u.getSenha())){
                    session().put("email",email);
                    return renderAgendaAdmin(null);
                }
            }else{
                if(senha.equals(u.getSenha())){
                    session().put("email",email);
                    return renderAgendar(null);
                }
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
        Logger.info(u.getNome());
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
    public static Result desmarcarCorte(){
        DynamicForm d = Form.form().bindFromRequest();
        Long id = Long.parseLong(d.get("id"));
        String data = d.get("dataAgora");
        Usuario u = Sistema.getUsuario(session().get("email"));
        Sistema.desmarcarCorte(id);
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
        String nomeus = d.get("nomeus");
        int horaPos = Util.getPos(hora);
        Usuario u = Sistema.getUsuario(session().get("email"));
        Corte co = new Corte(hora,data,horaPos,u.getId(),nomeus);
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
        Mensagem me = new Mensagem(u.getId(),data,mensagem);
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
    @Transactional
    public static Result removeUsuario(Long id){
        Usuario u = Sistema.getUsuario(id);
        if(u!=null){
            Sistema.removeUsuario(id);
        }
        return renderOutros();
    }
    @Transactional
    public static Result json() {
        List<Usuario> li = Sistema.getTodosUsuarios();
        JsonNode obj = Json.toJson(li);
        return ok(obj);
    }

    @Transactional
    public static Result jsoncorte() {
        List<Corte> li = Sistema.getTodosCortes();
        JsonNode obj = Json.toJson(li);
        return ok(obj);
    }
}
