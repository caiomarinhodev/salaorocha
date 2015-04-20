package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result renderlogin(){
        return ok(login.render());
    }

    public static Result renderRegistro(){
        return ok(register.render());
    }



}
