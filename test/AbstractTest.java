import controllers.Sistema;
import models.Usuario;
import org.junit.After;
import org.junit.Before;
import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;
import javax.persistence.EntityManager;

/**
 * Created by X on 17/03/2015.
 */

public abstract class AbstractTest {
    public EntityManager em;
    @Before
    public void setUp() {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
        iniciaInstancias();
    }
    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }
    private void iniciaInstancias(){
        Usuario usuario = new Usuario("Usuario 1","u1","u1",1,"8888-9999","https://cdn3.iconfinder.com/data/icons/users/100/user_male_1-512.png"
                ,"Rua Endereco de teste, 888");
        Usuario usuario2 = new Usuario("Usuario 2","u2","u2",1,"8888-9999","https://cdn3.iconfinder.com/data/icons/users/100/user_male_1-512.png"
                ,"Rua Endereco de teste, 888");
        Usuario usuario3 = new Usuario("Usuario 3","u3","u3",1,"8888-9999","https://cdn3.iconfinder.com/data/icons/users/100/user_male_1-512.png",
                "Rua Endereco de teste, 888");
        Sistema.addUsuario(usuario);
        Sistema.addUsuario(usuario2);
        Sistema.addUsuario(usuario3);

    }
}