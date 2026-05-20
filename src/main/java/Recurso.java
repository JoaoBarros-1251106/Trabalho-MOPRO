public class Recurso {
    private String titulo;
    private int ano;
    private ArrayList<genero> generos;
    private ArrayList<ator> atores;

    public Recurso(String titulo, int ano, ArrayList<genero> generos, ArrayList<ator> atores) {
        this.titulo = titulo;
        this.ano = ano;

        generos = new ArrayList<>();
        atores = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public ArrayList<genero> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<genero> generos) {
        this.generos = generos;
    }

    public ArrayList<ator> getAtores() {
        return atores;
    }

    public void setAtores(ArrayList<ator> atores) {
        this.atores = atores;
    }


    @Override
    public String toString() {
        return "Recurso{" +
                "titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", generos=" + generos +
                ", atores=" + atores +
                '}';
    }
}
