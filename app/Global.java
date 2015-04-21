import models.Corte;
import models.GenericDAO;
import models.Usuario;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Date;
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

                List<Usuario> lis = dao.findAllByClassName(Usuario.class.getName());
                if (lis.size() < 1) {
                    Usuario usuario = new Usuario("admin","admin","admin",0,"",null,"");
                    dao.persist(usuario);

//
//                    for(int i=1; i<=31; i++){
//                        Cliente cli = new Cliente("cliente"+(i*31),"(83) 9999-9999","","","","","","","","","");
//                        dao.persist(cli);
//                        Calendar calendar = Calendar.getInstance();
//                        java.util.Date d = calendar.getTime();
//                        Date date = new Date(d.getYear(),(d.getMonth()-1),i);
//                        Chamada cha = new Chamada(cli,"","","",0,t2,date,"","");
//                        dao.persist(cha);
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