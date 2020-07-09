import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Dichiaro e inizializzo la password
        String password = "ProgettoJava";
        MyDataBoard<Data> dboard = new MyDataBoard<Data>(password);
        String category;
        String input = args[0];
        if (input.equals("TestA") == true) {
            System.out.println("TEST 1: createCategory");
            for (int i = 0; i < 4; i++) {
                category = "Category" + i;
                try {
                    dboard.createCategory(category, password);
                } catch (NullPointerException | InvalidPasswordException e) {
                    System.out.println(e);
                }
            }
            System.out.println("Category list: ");
            for (Object elem : dboard.getCategoryList()) {
                System.out.println(elem.toString());
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 2: rmoveCategory");
            try {
                dboard.removeCategory("Category0", password);
            } catch (NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }
            System.out.println("Category list: ");
            for (Object elem : dboard.getCategoryList()) {
                System.out.println(elem.toString());
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 3: addFriend");
            for (int i = 0; i < 4; i++) {
                try {
                    if (i % 2 == 0)
                        dboard.addFriend("Category1", password, "Friend" + i);
                    else
                        dboard.addFriend("Category2", password, "Friend" + i);
                    System.out.println("Friend" + i + " aggiunto alla lista");
                } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 4: removeFriend");
            try {
                dboard.removeFriend("Category1", password, "Friend0");
                System.out.println("Category1 rimossa dalla lista di categorie di Friend0");
            } catch (NullPointerException | FriendNotFoundException | CategoryDoesNotExistException
                    | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 5: put");
            for (int i = 0; i < 4; i++) {
                try {
                    if (i % 2 == 0)
                        category = "Category1";
                    else
                        category = "Category2";
                    int likes = 0;
                    MyData dato = new MyData("Brano" + i, "Artista" + i, category, likes);
                    if (dboard.put(password, dato, category))
                        System.out.println("Dato" + i + "inserito");

                } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException
                        | NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 6: get");
            for (int i = 0; i < 4; i++) {
                try {
                    if (i % 2 == 0)
                        category = "Category1";
                    else
                        category = "Category2";
                    int likes = 0;
                    MyData dato = new MyData("Brano" + i, "Artista" + i, category, likes);
                    dboard.get(password, dato).Display();
                } catch (NullPointerException | InvalidPasswordException | NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 7: remove");
            for (int i = 0; i < 3; i++) {
                try {
                    if (i % 2 == 0)
                        category = "Category1";
                    else
                        category = "Category2";
                    int likes = 0;
                    MyData dato = new MyData("Brano" + i, "Artista" + i, category, likes);
                    dboard.remove(password, dato).Display();
                } catch (NullPointerException | InvalidPasswordException | NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 8: getDataCategory");
            for (Object elem : dboard.getCategoryList()) {
                try {
                    for (Data el : dboard.getDataCategory(password, (String) elem)) {
                        el.Display();
                    }
                } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 9: getIterator");
            try {
                Iterator iter = dboard.getIterator(password);
                while (iter.hasNext()) {
                    MyData elem = (MyData) iter.next();
                    elem.Display();
                }
            } catch (NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 10: insertLike");
            for (int i = 0; i < 4; i++) {
                try {
                    if (i % 2 == 0)
                        category = "Category1";
                    else
                        category = "Category2";
                    int likes = 0;
                    MyData elem = new MyData("Brano" + i, "Artista" + i, category, likes);
                    for (int j = 0; j < 3; j++) {
                        dboard.insertLike("Friend" + i, elem);
                        System.out.println("Like inserito");
                    }
                } catch (NullPointerException | FriendNotFoundException | DataNotFoundException
                        | NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 11: getFriendIterator");
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("Lista dei dati visibili a Friend" + i + ": ");
                    Iterator iter2 = dboard.getFriendIterator("Friend" + i);
                    if (iter2 == null)
                        System.out.println("La lista di Friend" + i + " è vuota");
                    else {
                        while (iter2.hasNext()) {
                            MyData elem = (MyData) iter2.next();
                            elem.Display();
                        }
                    }
                } catch (NullPointerException | FriendNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
        // TEST PER LE ECCEZIONI
        if (input.equals("TestB") == true) {
            System.out.println("TEST 1: createCategory exception");
            try {
                // dboard.createCategory(null, password);
                // dboard.createCategory("CateogryB", null);
                dboard.createCategory("CategoryB", "ProgettoSOL");
            } catch (NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 2: rmoveCategory");
            try {
                // dboard.removeCategory(null, password);
                // dboard.removeCategory("CategoryB", null);
                dboard.removeCategory("CategoryB", "ProgettoSOL");
            } catch (NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 3: addFriend");
            try {
                // dboard.addFriend(null, password, "FriendB");
                dboard.createCategory("CategoryB", password);
                // dboard.addFriend("CategoryB", null);
                // dboard.addFriend("CategoryB", password, null);
                // dboard.addFriend("CategoryB", "ProgettoSOL", "FriendB");
                dboard.removeCategory("CategoryB", password);
                dboard.addFriend("CategoryB", password, "FriendB");
            } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 4: removeFriend");
            try {
                // dboard.removeFriend(null, password,"FriendB");
                // dboard.removeFriend("CategoryB", null, "FriendB");
                // dboard.removeFriend("CategoryB", password, null);
                dboard.createCategory("CategoryB", password);
                // dboard.removeFriend("CategoryB", "ProgettoSOL", "FriendB");
                // dboard.removeFriend("CategoryB", password, "Amico");
                dboard.removeCategory("CategoryB", password);
                dboard.removeFriend("CategoryB", password, "FriendB");
            } catch (NullPointerException | FriendNotFoundException | CategoryDoesNotExistException
                    | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 5: put");
            try {
                int likes = 0;
                // int likes = -1;
                MyData dato = new MyData("Brano", "Artista", "CategoryB", likes);
                // dboard.put(null, dato, "CategoryB");
                // dboard.put(password, null, "CategoryB");
                // dboard.put(password, dato, null);
                // dboard.put(password, dato, "Category");
                dboard.put(password, dato, "CategoryB");
            } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException
                    | NegativeNumberException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 6: get");
            try {
                int likes = 0;
                // int likes = -1;
                MyData dato = new MyData("Brano", "Artista", "CategoryB", likes);
                // dboard.get(password, null);
                // dboard.get(null, dato);
                dboard.get(password, dato);
            } catch (NullPointerException | InvalidPasswordException | NegativeNumberException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 7: remove");
            try {
                int likes = 0;
                // int likes = -1;
                MyData dato = new MyData("Brano", "Artista", "CategoryB", likes);
                // dboard.remove(password, null);
                // dboard.remove(null, dato);
                dboard.remove(password, dato);
            } catch (NullPointerException | InvalidPasswordException | NegativeNumberException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 8: getDataCategory");
            try {
                // dboard.getDataCategory(null, "CategoryB");
                // dboard.getDataCategory(password, null);
                // dboard.getDataCategory(password, "Category");
                dboard.getDataCategory(password, "CategoryB");
            } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 9: getIterator");
            try {
                // dboard.getIterator(null);
                // dboard.getIterator("ProgettoSOL");
                dboard.getIterator(password);
            } catch (NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 10: insertLike");
            try {
                int likes = 0;
                // int likes = -1;
                MyData elem = new MyData("Brano", "Artista", "CategoryB", likes);
                // dboard.insertLike("FriendB", null);
                // dboard.insertLike(null, elem);
                dboard.createCategory("CategoryB", password);
                // dboard.addFriend("Category", password, "FriendB");
                dboard.addFriend("CategoryB", password, "FreindB");
                dboard.remove(password, elem);
                // dboard.insertLike("Amico", elem);
                dboard.insertLike("FriendB", elem);
            } catch (NullPointerException | FriendNotFoundException | DataNotFoundException | NegativeNumberException
                    | CategoryDoesNotExistException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 11: getFriendIterator");
            try {
                //dboard.getFriendIterator(null);
                dboard.getFriendIterator("Amico");
            } catch (NullPointerException | FriendNotFoundException e) {
                System.out.println(e);
            }
        }
    }
}