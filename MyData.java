public class MyData implements Data {

    //AF
    //RI

    // Variabili di istanza
    private String titolo;
    private String artista;
    private String categoria;
    private int likes;

    // Costruttore
    public MyData(String titolo, String artista, String categoria, int likes) throws NullPointerException,NegativeNumberException {
            if(titolo == null )
                throw new NullPointerException("Titolo non specificato ");
            if(artista ==  null )
                throw new NullPointerException("Artista non specificato");
            if(categoria == null)
                throw new NullPointerException("Genere non specificato");
            if(likes < 0)
                throw new NegativeNumberException("Numero likes negativo");
            this.titolo = titolo;
            this.artista = artista;
            this.categoria = categoria;
            this.likes = likes;
    }

    @Override
    // Stampa le informazioni del dato.
    public void Display() {
      System.out.println("Titolo: " + this.titolo);
      System.out.println("Artista: " + this.artista);
      System.out.println("Genere: " + this.categoria);
      System.out.println("Likes: " + this.likes);
      System.out.println("");
    }

    @Override
    // Restituisce il titolo del brano.
    public String getTitolo() {
        return this.titolo;
    }

    @Override
    // Restitituisce l'artista del brano.
    public String getArtista() {
        return this.artista;
    }

    @Override
    // Restituisce il categoria del brano.
    public String getCategory() {
       return this.categoria;
    }

    @Override
    // Restituisce il numero di likes del brano.
    public int getLikes() {
        return this.likes;
    }

    @Override
    // Incrementa di uno il numero di likes del brano.
    public void addLikes() {
        this.likes++;
    }

    @Override
    // Aggiune la categoria al brano.
    public void setCategory(String category) throws NullPointerException {
        if(category == null)
            throw new NullPointerException("Category è uguale a null");
        this.categoria = category;
    }
}