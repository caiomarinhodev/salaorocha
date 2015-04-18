import controllers.Sistema;
import models.Usuario;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
/**
 * Created by Caio.
 */

public class UsuarioTest extends AbstractTest {
    @Test
    public void testDeveCriarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        usuario.setTipo(1);
        Assert.assertTrue(Sistema.addUsuario(usuario));
    }
    @Test
    public void testTentaCadastrarUsuarioNomeInvalido(){
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("");
        usuario.setSenha("123");
        usuario.setTipo(1);
        Assert.assertFalse(Sistema.addUsuario(usuario));
    }
    @Test
    public void testTentaCadastrarUsuarioSenhaInvalida(){
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("");
        usuario.setTipo(1);
        Assert.assertFalse(Sistema.addUsuario(usuario));
    }
    @Test
    public void testRecuperaUsuarioCadastradoSalvoNoBD(){
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        usuario.setTipo(1);
        Sistema.addUsuario(usuario);
        Assert.assertNotNull(Sistema.getUsuario("teste@edu.br"));
    }
}

