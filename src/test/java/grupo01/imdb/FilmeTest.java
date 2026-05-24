package grupo01.imdb;

import org.example.model.*;
import org.example.utils.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {

    private DB imdb;
    private Espectador espectador;
    private Filme filme;

    @BeforeEach
    void setup() throws Exception {
        imdb = new DB("test.imdb.com");

        espectador = new Espectador("test@test.com", "testuser", "pass");
        imdb.adicionarUtilizador(espectador);

        filme = new Filme("Inception", 2010, 148);
        filme.adicionarGenero(Genero.SCI_FI);
        filme.adicionarAtor(new Ator("Tom Hardy", new Data(1977, 9, 15)));
        imdb.adicionarRecurso(filme);
    }

    @Test
    void testMarcarFilmeComoVisto() throws Exception {
        assertFalse(filme.isVisto(espectador));
        filme.marcarComoVisto(espectador);
        assertTrue(filme.isVisto(espectador));
    }

    @Test
    void testMarcarFilmeComoVistoRepetido() throws Exception {
        filme.marcarComoVisto(espectador);
        assertThrows(Exception.class, () -> filme.marcarComoVisto(espectador));
    }

    @Test
    void testClassificarFilme() throws Exception {
        filme.marcarComoVisto(espectador);
        imdb.classificarFilme(filme, espectador, 8);
        assertEquals(8.0, filme.getClassificacaoMedia(), 0.01);
    }

    @Test
    void testClassificarFilmeNaoVisto() {
        assertThrows(Exception.class,
                () -> imdb.classificarFilme(filme, espectador, 8));
    }

    @Test
    void testClassificacaoValorInvalido() throws Exception {
        filme.marcarComoVisto(espectador);
        assertThrows(Exception.class,
                () -> imdb.classificarFilme(filme, espectador, 11));
        assertThrows(Exception.class,
                () -> imdb.classificarFilme(filme, espectador, 0));
    }

    @Test
    void testClassificarFilmeDuasVezes() throws Exception {
        filme.marcarComoVisto(espectador);
        imdb.classificarFilme(filme, espectador, 7);
        assertThrows(Exception.class,
                () -> imdb.classificarFilme(filme, espectador, 9));
    }

    @Test
    void testRecursoDuplicado() {
        Filme duplicado = new Filme("Inception", 2010, 120);
        assertThrows(Exception.class,
                () -> imdb.adicionarRecurso(duplicado));
    }

    @Test
    void testCategoriaFraco() throws Exception {
        filme.marcarComoVisto(espectador);
        imdb.classificarFilme(filme, espectador, 3);
        assertEquals("Fraco", filme.getCategoriaClassificacao());
    }

    @Test
    void testCategoriaMedio() throws Exception {
        filme.marcarComoVisto(espectador);
        imdb.classificarFilme(filme, espectador, 6);
        assertEquals("Médio", filme.getCategoriaClassificacao());
    }

    @Test
    void testCategoriaBom() throws Exception {
        filme.marcarComoVisto(espectador);
        imdb.classificarFilme(filme, espectador, 9);
        assertEquals("Bom", filme.getCategoriaClassificacao());
    }

    @Test
    void testPesquisaFilme() {
        assertTrue(filme.correspondePesquisa("incep"));
        assertTrue(filme.correspondePesquisa("INCEPTION"));
        assertFalse(filme.correspondePesquisa("avatar"));
    }

    @Test
    void testListaPessoal() {
        espectador.getListaPessoal().adicionarFilme(filme);
        assertEquals(1, espectador.getListaPessoal().getFilmes().size());
        espectador.getListaPessoal().removerFilme(filme);
        assertTrue(espectador.getListaPessoal().getFilmes().isEmpty());
    }

    @Test
    void testLoginValido() {
        assertNotNull(imdb.login("testuser", "pass"));
    }

    @Test
    void testLoginInvalido() {
        assertNull(imdb.login("testuser", "errado"));
    }
}