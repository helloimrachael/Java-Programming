import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MyDataBoard2<E extends Data> implements DataBoard<E> {

    // AF: <passwordBacheca, {category(0)...category(n)}, {dato(0)...dato(n)}, <friend, categoryFriendList>> 
        // tale che f(friend) --> {categoryFriend(0)...categoryFriend(k)} con 0 <= i < n.
    // RI: password != null && categoryBacheca != null && dataList != null && hash != null
        // for all i in categoryBacheca --> categoryBacheca.get(i) != null con 0 <= i <
        // categoryBacheca.size() &&
        // for all j in dataList --> dataList.get(j) != null con 0 <= j <
        // dataList.size() &&
        // (for all k in has.keySet()--> k!=null) && (for all k in hash.keySet() --> hash.get(k)!=null)

    // Password proprietario bacheca.
    private String password;

    // Struttura che memorizza la lista di categorie presenti nella bacheca.
    private ArrayList<String> categoryBacheca;

    // Struttura che memorizza la lista dei dati presenti nella bacheca.
    private ArrayList<E> dataList;

    // Struttura che memorizza per ogni amico della bacheca la lista di categorie a
    // lui visibili.
    private HashMap<String, ArrayList<String>> hash;

    // COSTRUTTORE: inizializza solamente password e crea tre liste diverse.
    public MyDataBoard2(String password) throws NullPointerException {
        if (password == null)
            throw new NullPointerException("Password uguale a null");
        this.password = password;
        categoryBacheca = new ArrayList<String>();
        dataList = new ArrayList<E>();
        hash = new HashMap<String, ArrayList<String>>();
    }

    @Override
    // Aggiunge, se non già presente, la categoria category all'insieme di
    // categorie.
    public void createCategory(String category, String passw) throws NullPointerException, InvalidPasswordException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.isEmpty() == false) {
            if (categoryBacheca.contains(category) == false) // La bacheca non contiene category, quindi la aggiungo.
                categoryBacheca.add(category);
            // Nel caso il risultato fosse true, ovvero che la bacheca contiene category,
            // non faccio niente.
        } else // Se sono in questo ramo significa che la lista di categorie non contiene
               // elementi e quindi aggiungo category.
            categoryBacheca.add(category);
    }

    @Override
    // Rimuove, se è presente, la categoria category dall'insieme di categorie della
    // bacheca.
    public void removeCategory(String category, String passw) throws NullPointerException, InvalidPasswordException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.isEmpty() == false) {
            if (categoryBacheca.contains(category) == true) // La bacheca contiene category, quindi la rimuovo.
                categoryBacheca.remove(category);
            // Se la bacheca non contiene category non faccio niente.
        }
        // Se la lista di categorie non contiene elementi allora non devo fare niente.
    }

    @Override
    // Aggiunge, se non è già presente, friend nella lista degli amici.
    public void addFriend(String category, String passw, String friend)
            throws NullPointerException, CategoryDoesNotExistException, InvalidPasswordException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.contains(category) == false)
            throw new CategoryDoesNotExistException(category + " non è presente nella bacheca");
        if (hash.isEmpty() == true || hash.containsKey(friend) == false) { // La hashmap è vuota o friend non è
                                                                           // presente.
            ArrayList<String> categoryList = new ArrayList<String>();
            categoryList.add(category);
            hash.put(friend, categoryList);
        } else { // L'amico è presente e aggiungo la categoria.
            hash.get(friend).add(category);
        }
    }

    @Override
    // Rimuove, se presente, friend dalla lista degli amici che possono vedere la
    // categoria Category.
    public void removeFriend(String category, String passw, String friend) throws NullPointerException,
            CategoryDoesNotExistException, InvalidPasswordException, FriendNotFoundException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.contains(category) == false)
            throw new CategoryDoesNotExistException(category + " non è presente nella bacheca");
        if (hash.isEmpty() == false) { // C'è almeno un elemento nella hashmap.
            ArrayList<String> cat = new ArrayList<String>(hash.get(friend));
            cat.remove(category);
        }
    }

    @Override
    // Restituisce true se inserisce il dato data della categoria category in
    // bacheca, false altrimenti.
    public boolean put(String passw, E data, String category)
            throws NullPointerException, InvalidPasswordException, CategoryDoesNotExistException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (data == null)
            throw new NullPointerException("Data è uguale a null");
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (categoryBacheca.contains(category) == false)
            throw new CategoryDoesNotExistException(category + " non è presente nella bacheca");
        if (dataList.add(data) == true) {
            data.setCategory(category); // Il dato è stato inserito e quindi aggiorno la sua categoria.
            return true;
        }
        return false;
    }

    @Override
    // Restituisce una copia del dato data se presente in bacheca, null se data non
    // è presente.
    public E get(String passw, E data) throws NullPointerException, InvalidPasswordException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (data == null)
            throw new NullPointerException("Data è uguale a null");
        boolean trovato = false;
        int i = 0;
        while (trovato == false && i < dataList.size()) {
            if (dataList.get(i).getTitolo().equals(data.getTitolo()))
                trovato = true;
            else
                i++;
        }
        if (trovato == true) { // Il dato è presente nella lista.
            return dataList.get(i);
        }
        return null;
    }

    @Override
    // Rimuove e restituisce il dato data se presente in bacheca, altrimenti
    // restituisce null.
    public E remove(String passw, E data) throws NullPointerException, InvalidPasswordException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (data == null)
            throw new NullPointerException("Data è uguale a null");
        boolean trovato = false;
        int i = 0;
        while (trovato == false && i < dataList.size()) {
            if (dataList.get(i).getTitolo().equals(data.getTitolo()))
                trovato = true;
            else
                i++;
        }
        if (trovato == true) { // Il dato è presente nella lista.
            return dataList.remove(i);
        }
        return null;
    }

    @Override
    // Restituisce una lista contenente i dati della categoria Category presente in
    // bacheca.
    // Se category non contiene dati allora restitusice [].
    public List<E> getDataCategory(String passw, String category)
            throws NullPointerException, InvalidPasswordException, CategoryDoesNotExistException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (categoryBacheca.contains(category) == true) { // La categoria è presente in bachecas.
            List<E> dataCategory = new ArrayList<E>();
            for (int i = 0; i < dataList.size(); i++) { // Scorro tutta la mia lista di dati controllando la categoria a
                                                        // cui appartengono.
                if (dataList.get(i).getCategory().equals(category)) { // Se la categoria corrisponde inserisco nella
                                                                      // lista il dato.
                    dataCategory.add(dataList.get(i));
                }
            }
            return dataCategory;
        }
        return null;
    }

    @Override
    // Restituisce un iteratore che scorre tutti i dati che si trovano in bacheca
    // ordinati a seconda del numero di likes senza il metodo remove.
    // Se non ci sono dati nella bacheca restituisce null.
    public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPasswordException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (dataList.isEmpty() == true) // Non ci sono elementi nella bacheca
            return null;
        ArrayList<E> allData = new ArrayList<>(dataList);
        allData.sort(new Comparator<E>() {
            @Override
            public int compare(E c1, E c2) {
                if (c1.getLikes() == c2.getLikes())
                    return 0;
                return c1.getLikes() - c2.getLikes();
            }
        });
        return new myIterator<>(allData.iterator());
    }

    @Override
    // Incrementa di 1 il numero di likes del dato data che è visibile all'amico
    // friend.
    public void insertLike(String friend, E data)
            throws NullPointerException, FriendNotFoundException, DataNotFoundException {
        if (friend == null)
            throw new NullPointerException("Friend uguale a null");
        if (data == null)
            throw new NullPointerException("Data uguale a null");
        boolean trovato = false;
        int i = 0;
        while (trovato == false && i < dataList.size()) {
            if (dataList.get(i).getTitolo().equals(data.getTitolo()) == true) // Controllo se è presente il dato nella
                                                                              // lista.
                trovato = true;
            else
                i++;
        }
        if (trovato == true) { // Il dato è presente nella bacheca.
            String c = dataList.get(i).getCategory(); // Mi salvo la categoria del dato.
            if (hash.containsKey(friend) == true) {
                ArrayList<String> cat = new ArrayList<>(hash.get(friend));
                if (cat.contains(c) == true) {
                    dataList.get(i).addLikes();
                }
            }
        } else
            throw new DataNotFoundException(data.getTitolo() + " non presente nella lista");
    }

    @Override
    // Restituisce un iteratore che permette all'amico friend di visualizzare tutti
    // i dati a lui visibili.
    // Se non ci sono dati a lui visibili restituisce null.
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException, FriendNotFoundException {
        if (friend == null)
            throw new NullPointerException("Friend è uguale a null");
        if (hash.containsKey(friend) == true) {
            ArrayList<E> allDataFriends = new ArrayList<>(); // Mi creo una lista dove andrò a salvare tutti i dati che
            // friend può vedere.

            ArrayList<String> allCategoryFriends = new ArrayList<>(hash.get(friend)); // Mi creo una
                                                                                      // lista in
                                                                                      // cui vado a
                                                                                      // salvarmi
                                                                                      // tutte le
                                                                                      // categorie
                                                                                      // visibili a
                                                                                      // friend.
            int j;
            for (int i = 0; i < allCategoryFriends.size(); i++) { // Scorre la lista delle categorie visibili a friend.
                for (j = 0; j < dataList.size(); j++) { // Cerco nella lista dei dati della bacheca, i dati che
                                                        // appartengono
                                                        // alla categoria i-esima.
                    if (dataList.get(i).getCategory().equals(allCategoryFriends.get(i)) == true)
                        allDataFriends.add(dataList.get(i)); // Se trovo il dato lo inserisco nella nuova lista di dati.
                }
            }
            if (allDataFriends.isEmpty() == true)
                return null;
            return new myIterator<>(allDataFriends.iterator());
        } else // Se friend non è nella lista degli amici lancio un'eccezione.
            throw new FriendNotFoundException(friend + " non è nella lista.");
    }

    // Restituisce la lista delle categorie
    public ArrayList<String> getCategoryList() {
        return this.categoryBacheca;
    }

    // Stampa la lista di dati della bacheca.
    public void getDataList() {
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println("Dato" + i + ": " + dataList.get(i).getTitolo());
        }
    }

    private class myIterator<E> implements Iterator<E> { // Iteratore senza il metodo remove.

        private Iterator<E> iter;

        public myIterator(Iterator<E> iter) throws NullPointerException {
            if (iter == null)
                throw new NullPointerException();
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public E next() {
            return iter.next();
        }

    }

}