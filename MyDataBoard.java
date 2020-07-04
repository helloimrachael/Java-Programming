import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataBoard<E extends Data> implements DataBoard<E> {

    // AF
    // RI

    private class Couple {

        // Variabile che mi indica l'amico.
        private String friend;

        // Lista di stringhe che mi indica le categorie che friend può vedere.
        private ArrayList<String> categories;

        // COSTRUTTORE: inizializza solamente la variabile friend e crea la lista di
        // categorie.
        public Couple(String friend) throws NullPointerException {
            if (friend == null)
                throw new NullPointerException("friend uguale a null");
            if (categories == null)
                throw new NullPointerException("lista categorie uguale a null");
            this.friend = friend;
            this.categories = new ArrayList<String>();
        }

        // Restituisce il nome dell'amico.
        public String getName() {
            return this.friend;
        }

        // Aggiunge, se non è gia presente, category alla lista di categorie visibili a
        // friend.
        public void addCategory(String category) throws NullPointerException {
            if (category == null)
                throw new NullPointerException("category uguale a null");
            if (categories.isEmpty() == true) // Se la lista non contiene elementi aggiungo category ad essa.
                categories.add(category);
            else { // La lista contiene almeno un elemento.
                if (categories.contains(category) == false) // La lista non contiene category, quindi la aggiungo.
                    categories.add(category);
                // Se la lista contiene già category non faccio niente.
            }
        }

        // Rimuove, se presente, category dalla lista di categorie visibili a friend.
        public void removeCategory(String category) throws NullPointerException {
            if (category == null)
                throw new NullPointerException("category uguale a null");
            if (categories.isEmpty() == false) { // La lista contiene almeno un elemento.
                if (categories.contains(category) == true) // La lista contiene category, quindi la rimuovo.
                    categories.remove(category);
                // Se la lsita non contiene category non faccio niente.
            }
            // Se la lista non contiene elementi non devo fare niente.
        }

        public boolean checkCatecory(String category) throws NullPointerException {
            if (category == null)
                throw new NullPointerException("Category uguale a null");
            if (categories.contains(category) == true)
                return true;
            return false;
        }
    }

    // Password proprietario bacheca.
    private String password;

    // Struttura che memorizza la lista di categorie presenti nella bacheca.
    private ArrayList<String> categoryBacheca;

    // Struttura che memorizza la lista dei dati presenti nella bacheca.
    private ArrayList<E> dataList;

    // Struttura che memorizza la lista di amici della bacheca.
    private ArrayList<Couple> friends;

    // COSTRUTTORE: inizializza solamente password e crea tre liste diverse.
    public MyDataBoard(String password) throws NullPointerException {
        if (password == null)
            throw new NullPointerException("Password uguale a null");
        this.password = password;
        categoryBacheca = new ArrayList<String>();
        dataList = new ArrayList<E>();
        friends = new ArrayList<Couple>();
    }

    @Override
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
    public void addFriend(String category, String passw, String friend)
            throws NullPointerException, CategoryDoesNotExistException, InvalidPasswordException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.contains(category) == false)
            throw new CategoryDoesNotExistException("Category non è presente nella bacheca");
        if (friends.isEmpty() == true) { // Se la lista è vuota creo la coppia <friend, categoryList>.
            Couple f = new Couple(friend);
            f.addCategory(category);
            friends.add(f);
        } else { // La lista di coppie non è vuota.
            boolean trovato = false;
            int i = 0;
            while (trovato == false && i < friends.size()) {
                if (friends.get(i).getName().equals(friend) == true)
                    trovato = true;
                else
                    i++;
            }
            if (trovato == true)
                friends.get(i).addCategory(category);
            else {
                Couple newF = new Couple(friend);
                newF.addCategory(category);
                friends.add(newF);
            }
        }
    }

    @Override
    public void removeFriend(String category, String passw, String friend)
            throws NullPointerException, CategoryDoesNotExistException, InvalidPasswordException {
        if (category == null)
            throw new NullPointerException("Category uguale a null");
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (categoryBacheca.contains(category) == false)
            throw new CategoryDoesNotExistException("Category non è presente nella bacheca");
        if (friends.isEmpty() == false) { // C'è almeno un amico nella lista.
            boolean trovato = false;
            int i = 0;
            while (trovato == false && i < friends.size()) {
                if (friends.get(i).getName().equals(friend) == true)
                    trovato = true;
                else
                    i++;
            }
            if (trovato == true) // Ho trovato l'amico quindi tolgo dalla sua lista di categorie la categoria
                                 // category.
                friends.get(i).removeCategory(category);
            // Se non ho trovato l'amico non faccio niente.
        }
        // Se la lista di coppie è vuota non faccio niente.
    }

    @Override
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
            throw new CategoryDoesNotExistException("Category non è presente nella bacheca");
        if (dataList.add(data) == true) {
            data.setCategory(category); // Il dato è stato inserito e quindi aggiorno la sua categoria.
            return true;
        }
        return false;
    }

    @Override
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
            if (dataList.get(i).equals(data))
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
            if (dataList.get(i).equals(data))
                trovato = true;
            else
                i++;
        }
        if (trovato == true) { // Il dato è presente nella lista.
            E d = dataList.get(i);
            dataList.remove(i);
            return d;
        }
        return null;
    }

    @Override
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
    public Iterator<E> getIterator(String passw) throws NullPointerException, InvalidPasswordException {
        if (passw == null)
            throw new NullPointerException("Passw uguale a null");
        if (passw.equals(password) == false)
            throw new InvalidPasswordException("Passw sbagliata");
        if (dataList.isEmpty() == true) // Non ci sono elementi nella bacheca
            return null;
        
    }

    @Override
    public void insertLike(String friend, E data)
            throws NullPointerException, FriendNotFoundException, DataNotFoundException {
        if (friend == null)
            throw new NullPointerException("Friend uguale a null");
        if (data == null)
            throw new NullPointerException("Data uguale a null");
        boolean trovato = false;
        int i = 0;
        while (trovato == false && i < dataList.size()) {
            if (dataList.get(i).equals(data) == true) // Controllo se è presente il dato nella lista.
                trovato = true;
            else
                i++;
        }
        if (trovato == true) {
            String c = dataList.get(i).getCategory(); // Mi salvo la categoria del dato.
            boolean trovata = false;
            int j = 0;
            while (trovata == false && j < friends.size()) { // Controllo se l'amico è nella lista di amici.
                if (friends.get(i).getName().equals(friend) == true)
                    trovato = true;
                else
                    j++;
            }
            if (trovata == false)
                throw new FriendNotFoundException("L'amico non è nella lista di amici della bacheca");
            if (trovata == true) {
                if (friends.get(i).checkCatecory(c) == true) { // Aggiungo il like al brano.
                    dataList.get(i).addLikes();
                }
            }

        } else
            throw new DataNotFoundException("Dato non presente nella lista");
    }

    @Override
    public Iterator<E> getFriendIterator(String friend) throws NullPointerException {
        return null;
    }

    private class myIterator implements Iterator<E> {

        private Iterator<E> iter;

        public myIterator(Iterator<E> iter) {
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