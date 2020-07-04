public class MyData implements Data {

    //AF
    //RI

    // Variabili di istanza
    private String titolo;
    private String artista;
    private String categoria;
    private int likes;

    // Costruttore
    public MyData(String titolo, String artista, String categoria, int like) throws NullPointerException,NegativeNumberException {
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
            this.likes = 0;
    }

    @Override
    // Stampa le informazioni del dato.
    public void Display() {
      System.out.println("Titolo:" + getTitolo());
      System.out.println("Artista:" + getArtista());
      System.out.println("Genere:" + getCategory());
      System.out.println("Likes:" + getLikes());
    }

    @Override
    // Restituisce il titolo del brano.
    public String getTitolo() {
        String title = new String(titolo);
        return title;
    }

    @Override
    // Restitituisce l'artista del brano.
    public String getArtista() {
        String artist = new String(artista);
        return artist;
    }

    @Override
    // Restituisce il categoria del brano.
    public String getCategory() {
        String category = new String(categoria);
        return category;
    }

    @Override
    // Restituisce il numero di likes del brano.
    public int getLikes() {
        int nlikes = likes;
        return nlikes;
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