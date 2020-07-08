import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Dichiaro e inizializzo la password
        String password = "ProgettoJava";
        MyDataBoard<Data> dboard = new MyDataBoard<Data>(password);
        String category;
        String input = args[0];
        if(input.equals("Test A") == false || input.equals("Test B") == false)
            System.out.println("Argomento errato: inserire 'Test A' oppure 'Test B'");
        if (input.equals("Test") == true) {
            System.out.println("TEST 1: createCategoryategory");
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

                } catch (NullPointerException | InvalidPasswordException | CategoryDoesNotExistException | NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 6: get");
            for(int i = 0; i < 4; i++){
                try {
                    if(i % 2 == 0)
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
            for(int i = 0; i < 3; i++){
                try {
                    if(i % 2 == 0)
                        category = "Category1";
                    else
                        category = "Category2";
                    int likes = 0;
                    MyData dato = new MyData("Brano" + i, "Artista" + i, category, likes);
                    dboard.remove(password, dato).Display();
                } catch (NullPointerException | InvalidPasswordException| NegativeNumberException e) {
                    System.out.println(e);
                }
            }

            System.out.println("/////////////////////////////////////////");

            System.out.println("TEST 8: getCategoryList");
            for(Object elem : dboard.getCategoryList()) {
                try {
                    for(Data el : dboard.getDataCategory(password, (String) elem)){
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
            } catch(NullPointerException | InvalidPasswordException e) {
                System.out.println(e);
            }

            System.out.println("/////////////////////////////////////////");

            
        }
    }
}