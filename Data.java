public interface Data {

    /*
    OVERVIEW: tipo di dato mutabile che rappresenta le caratteristiche di un brano musicale, 
              ovvero il titolo del brano, l'artista del brano e il numero di likes associato al brano.
    Typical Element: <titolo, artista, categoria, likes>
        dove
            - titolo: stringa che mi identifica il titolo del brano musicale.
            - artista: stringa che mi identifica il nome dell'artista del brano musicale.
            - categoria: stringa che mi identifica il genere del brano musicale.
            - likes: intero che mi identifica il numero di likes che il brano ha ricevuto.
    */
    
    // Stampa gli attributi del dato (this).
    public void Display();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: /
    EFFECTS: Stampa this.titolo, this.artista, this.likes
    */

    // Ottiene il titolo del brano.
    public String getTitolo();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: /
    EFFECTS: Restituisce this.titolo
    */

    // Ottiene l'artista del brano.
    public String getArtista();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: /
    EFFECTS: Restituisce this.artista
    */

    // Ottiene il genere del brano.
    public String getCategory();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: /
    EFFECTS: Restituisce this.categoria
    */

    // Ottiene il numero di likes del brano.
    public int getLikes();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: /
    EFFECTS: Restituisce this.likes
    */

    // Aggiunge un like al brano
    public void addLikes();
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: this
    EFFECTS: this.likes++
    */

    // Aggiunge il genere al brano.
    public void setCategory(String category) throws NullPointerException;
    /*
    REQUIERES: /
    THROWS: /
    MODIFIES: this
    EFFECTS: this.categoria = category.
    */
}