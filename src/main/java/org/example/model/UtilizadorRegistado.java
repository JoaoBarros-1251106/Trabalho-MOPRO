package org.example.model;

import java.util.ArrayList;

public abstract class UtilizadorRegistado extends Utilizador {

    private String email;
    private String nome;
    private String password;

    // Novas listas adicionadas
    private ListaPessoal listaPessoal;
    private ArrayList<Recurso> vistos;

    public UtilizadorRegistado(String username, String email, String password) {
        super(username, email, password);
    }

    public String getNome() {

        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean temNome(String nome) {

        return this.nome.equals(nome);
    }

    public boolean temPassword(String password) {

        return this.password.equals(password);
    }

    // ==========================================
    // LISTA PESSOAL
    // ==========================================

    public ListaPessoal getListaPessoal() {
        return listaPessoal;
    }

    // ==========================================
    // HISTÓRICO DE VISUALIZAÇÕES
    // ==========================================

    public void adicionarVisualizado(Recurso r) {
        if (!vistos.contains(r)) {
            vistos.add(r);
        }
    }

    public boolean jaViu(Recurso r) {
        return vistos.contains(r);
    }

    // ==========================================
    // INTERAÇÕES (CLASSIFICAR E COMENTAR)
    // ==========================================

    public void classificarFilme(Filme filme, int valor) {
        if (!jaViu(filme)) {
            System.out.println("Tem de ver o filme primeiro.");
            return; // Sai do método sem lançar exceção
        }

        for (Classificacao c : filme.getClassificacoes()) {
            // Nota: Garante que o método getUtilizador() existe na classe Classificacao
            if (c.getUtilizador().equals(this)) {
                System.out.println("Já classificou este filme.");
                return; // Sai do método sem lançar exceção
            }
        }

        // Assumindo que o construtor é Classificacao(int, UtilizadorRegistado)
        filme.adicionarClassificacao(new Classificacao(valor, this));
    }

    public void comentarFilme(Filme filme, String texto) {
        if (!jaViu(filme)) {
            System.out.println("Tem de ver o filme primeiro para poder comentar.");
            return; // Sai do método sem lançar exceção
        }

        // Assumindo que o construtor é Comentario(UtilizadorRegistado, String)
        filme.adicionarComentario(new Comentario(this, texto));
    }

    @Override
    public String toString() {
        return nome + " <" + email + ">";
    }
}
//classe abstrata que representa um utilizador registado
//implements Serializable - necessário para gravar em ficheiro (serialização)
//Superclass de Admin e Espectador
//Constroi um utilizador registado
//verifica username e password correto
//getPassword() não é adicionado - má pratica de segurança expor a password
//boolean verificaEmail não usado porque não vamos usar o email para autenticação