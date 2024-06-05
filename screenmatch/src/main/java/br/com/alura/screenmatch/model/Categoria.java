package br.com.alura.screenmatch.model;
public enum Categoria {
    ACAO("Action", "Ação"),
    ROMANCE("Romance", "Romance"),
    DRAMA("Drama", "Drama"),
    COMEDIA("Comedy", "Comédia"),
    CRIME("Crime", "Crime"),
    ANIMACAO("Animation", "Animação"),

    BIOGRAFIA("Biography", "Biografia");
    private String categoriaOmdb;
    private String categoriaPortuguesOmdb;

    Categoria(String categoriaOmdb, String categoriaPortuguesOmdb){
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortuguesOmdb = categoriaPortuguesOmdb;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }

    public static Categoria fromStringPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortuguesOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
