package controllers;

import models.Usuario;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    @Transactional
    public static Result renderlogin(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        if(u!=null){
            if(u.getTipo()==0){
                return renderAgendaAdmin();
            }else{
                return renderAgendar();
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
                return renderAgendaAdmin();
            }else{
                return renderAgendar();
            }
        }
        return renderlogin();
    }

    @Transactional
    public static Result deslogar(){
        session().clear();
        return index();
    }

    @Transactional
    public static Result renderAgendaAdmin(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        return ok(agendadmin.render(u));
    }

    @Transactional
    public static Result renderAgendar(){
        Usuario u = Sistema.getUsuario(session().get("email"));
        return ok(agendar.render(u));
    }


}
