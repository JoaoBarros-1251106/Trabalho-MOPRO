package org.example.model;

    public class Comentario {

        private UtilizadorRegistado autor;

        private String texto;

        public Comentario(
                UtilizadorRegistado autor,
                String texto) {

            this.autor = autor;
            this.texto = texto;
        }

        public UtilizadorRegistado getAutor() {
            return autor;
        }

        public String getTexto() {
            return texto;
        }

        @Override
        public String toString() {

            return autor.getNome() +
                    ": " + texto;

        }
    }
}
