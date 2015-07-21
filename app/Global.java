import controllers.Sistema;
import models.GenericDAO;
import models.Usuario;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by caio on 24/03/15.
 */

public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {

            public void invoke() throws Throwable {

                List<Usuario> li = Sistema.getAdmins();
                for(Usuario u: li){
                    dao.remove(u);
                }
                Sistema.deletacristiano();
                if (!Sistema.existeAdmin()) {
                    String fo = "https://cdn4.iconfinder.com/data/icons/meBaze-Freebies/512/user.png";
                    Usuario u = new Usuario("Administrador", "admin", "admin", 0, "", "", "", fo);
                    dao.persist(u);
//
//                    for(int i=1; i<=31; i++){
//                        Usuario cli = new Usuario("cl"+i,"cl"+i,"cl"+i,1,"","","",foto);
//                        dao.persist(cli);
//                    }


                    dao.flush();
                    Logger.info("Inserindo dados no BD.");
                }
            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}