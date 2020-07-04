import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data> {

    //OVERVIEW: Tipo mutabile che rappresenta una collezione di oggetti generici che estendono il tipo di dato Data.
    /*Typical Element: <passwBacheca, <categorie>, <dati>, <arrayCoppie>>  
        dove: 
            - passwBacheca è la stringa che identifica la password univoca del proprietario della bacheca
            - <categorie> è la collezione di categorie contenute nella bacheca
            - <dati> è la collezione di dati di tipo Data contenuti nella bacheca
            - <arrayCoppie> è un insieme di coppie <friend, categoryList> che ad ogni amico associa la lista di categorie a lui visibile 
    */

    // Crea l'identità di una categoria di dati
    public void createCategory(String category, String passw) throws NullPointerException, InvalidPasswordException;
    /*
    REQUIRES: category != null && passw != null && passw.equals(this.passwBacheca) == true
    THROWS: se category == null lancia NullPointerException
            se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
    MODIFIES: this
    EFFECTS: aggiunge, se non già presente, la categoria category all'insieme di categorie
    */  

    // Rimuove l’identità di una categoria di dati
    public void removeCategory(String Category, String passw) throws NullPointerException, InvalidPasswordException;
     /*
    REQUIRES: category != null && passw != null && passw.equals(this.passwBacheca) == true
    THROWS: se category == null lancia NullPointerException
            se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
    MODIFIES: this
    EFFECTS: rimuove, se è presente, la categoria category dall'insieme di categorie della bacheca
    */  

    // Aggiunge un amico ad una categoria di dati
    public void addFriend(String Category, String passw, String friend) throws NullPointerException, CategoryDoesNotExistException, InvalidPasswordException;
     /*
    REQUIRES: category != null && category appartiene all'insieme di categorie della bacheca && passw != null && friend != null && passw.equals(this.passwBacheca) == true
    THROWS: se category == null lancia NullPointerException
            se category non appartiene all'insieme di categorie della bacheca lancia CategoryDoesNotExistException
            se passw == null lancia NullPointerException
            se friend == null lancia NullPointException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
    MODIFIES: this
    EFFECTS: aggiunge, se non è già presente, friend nella lista degli amici 
    */  

    // rimuove un amico da una categoria di dati
    public void removeFriend(String category, String passw, String friend) throws NullPointerException, CategoryDoesNotExistException, InvalidPasswordException;
     /*
    REQUIRES: category != null && category fa parte dell'insieme di categorie della bacheca && passw != null && friend != null && passw.equals(this.passwBacheca) == true 
    THROWS: se category == null lancia NullPointerException
            se category non appartiene all'insieme di categorie della bacheca lancia CategoryDoesNotExistException
            se passw == null lancia NullPointerException
            se friend == null lancia NullPointException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
    MODIFIES: this
    EFFECTS: rimuove, se presente, friend dalla lista degli amici che possono vedere la categoria Category
    */  

    // Inserisce un dato in bacheca se vengono rispettati i controlli di identità
    public boolean put(String passw, E  data, String category) throws NullPointerException, InvalidPasswordException, CategoryDoesNotExistException;
     /*
    REQUIRES: passw != null && passw.equals(this.passwBacheca) == true && dato != null && categoria != null && category appartiene all'insieme di categorie della bacheca
    THROWS: se passw == null lancia NullPointerException
            se data == null lancia NullPointerException
            se category == null lancia NullPointerException
            se category non appartiene all'insieme di categorie della bacheca lancia CategoryDoesNotExistException
    MODIFIES: this
    EFFECTS: determinato che passw.equals(this.passwBacheca) == true, restituisce true se inserisce il dato data della categoria category in bacheca, false altrimenti
    */  

    // Ottiene una copia del dato in bacheca se vengono rispettati i controlli di identità
    public E get(String passw, E data) throws NullPointerException, InvalidPasswordException;
     /*
    REQUIRES: passw != null && passw.equals(this.passwBacheca) == true && dato != null 
    THROWS: se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
            se data == null lancia NullPointerException
    MODIFIES: this
    EFFECTS: determinato che passw == passwProprietarioBacheca, restituisce una copia del dato data se presente in bacheca, null se data non è presente
    */  

    // Rimuove il dato dalla bacheca se vengono rispettati i controlli di identità
    public E remove(String passw, E data) throws NullPointerException, InvalidPasswordException;
     /*
    REQUIRES: passw != null && passw.equals(this.passwBacheca) == true && dato != null
    THROWS: se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
            se data == null lancia NullPointerException
    MODIFIES: this
    EFFECTS: determinato che passw.equals(this.passwBacheca) == true, rimuove e restituisce il dato data se presente in bacheca, altrimenti restituisce null
    */  

    // Crea la lista dei dati in bacheca su una determinata categoria se vengono rispettati i controlli di identità
    public List<E> getDataCategory(String passw, String category) throws NullPointerException, InvalidPasswordException, CategoryDoesNotExistException;
     /*
    REQUIRES: passw != null && passw.equals(this.passwBacheca) == true && category != null && Category appartiene all'insieme di categorie della bacheca
    THROWS: se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false lancia InvalidPasswordException
            se category == null lancia NullPointerException
            se category non appartiene all'insieme di categorie della bacheca lancia CategoryDoesNotExistException
    MODIFIES: this
    EFFECTS: determinato che passw.equals(this.passwBacheca) == true, restituisce una lista contenente i dati della categoria Category presente in bacheca.
             Se category non contiene dati allora restitusice []
    */  

    // restituisce un iteratore (senza remove) che genera tutti i dati in bacheca ordinati rispetto al numero di like.
    public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPasswordException;
     /*
    REQUIRES: passw != null && passw.equals(this.passwBacheca) == true
    THROWS: se passw == null lancia NullPointerException
            se passw.equals(this.passwBacheca) == false  lancia InvalidPasswordException
    MODIFIES: /
    EFFECTS: restituisce un iteratore che scorre tutti i dati che si trovano in bacheca ordinati a seconda del numero di likes senza il metodo remove.
             Se non ci sono dati nella bacheca restituisce null
    */  

    // Aggiunge un like a un dato
    void insertLike(String friend, E data) throws NullPointerException, FriendNotFoundException, DataNotFoundException;
    /*
    REQUIRES: friend != null && data != null
    THROWS: se friend == null lancia NullPointerException
            se data == null lancia NullPointerException
    MODIFIES: this
    EFFECTS: incrementa di 1 il numero di likes del dato data che è visibile all'amico friend
    */

    // Legge un dato condiviso e restituisce un iteratore (senza remove) che genera tutti i dati in
    // bacheca condivisi.
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException;
    /*
    REQUIRES: friend != null 
    THROWS: se friend == null lancia NullPointerException
    MODIFIES: /
    EFFECTS: restituisce un iteratore che permette all'amico friend di visualizzare tutti i dati a lui visibili.
             Se non ci sono dati a lui visibili restituisce null.
    */

}