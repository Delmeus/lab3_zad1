import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        int choice;
        boolean end = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wybierz aplikacje:\n1. Aplikacja sprzedawcy\n2. Aplikacja klienta");
        choice = scanner.nextInt();
        StorageManager manager = new StorageManager();
        switch (choice) {
            case 1 ->{

                do {
                    System.out.println("Aplikacja sprzedawcy, co chcesz zrobić?");
                    System.out.println("1. Dodać artykuł\n2. Zapisać listę do pliku\n3. Wczytać listę z pliku\n4. Zakończyć program");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> {
                            try {
                                System.out.println("Podaj kod produktu do dodania: ");
                                String code = scanner.next();
                                System.out.println("Podaj produkt do dodania: ");
                                String name = scanner.next();
                                System.out.println("Podaj jego cenę: ");
                                double price = scanner.nextDouble();
                                manager.addProduct(new Product(code, name, price));
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                        case 2 -> manager.saveStorageToFile();
                        case 3 -> manager.loadStorageFromFile();
                        case 4 -> {
                            manager.printProducts();
                            end = true;
                        }
                        default -> System.out.println("Błędny wybór, spróbuj jeszcze raz");
                    }
                } while (!end);
            }

            case 2 ->{

                ClientHelper helper = new ClientHelper();
                List<Product> productList;
                manager.loadStorageFromFile();
                do{
                    System.out.println("Aplikacja klienta, co chcesz zrobić?");
                    System.out.println("1. Sprawdź dostępność artykułu\n2. Dodaj artykuł do koszyka\n3. Wyceń koszyk\n4. Pokaż koszyk\n5. Realizuj zakup\n6. Zakończ program");
                    choice = scanner.nextInt();
                    switch (choice){
                        case 1 ->{
                            System.out.println("Podaj nazwę produktu");
                            productList = helper.checkForProduct(scanner.next(), manager.getProductsList());
                            if(productList.isEmpty()) System.out.println("Nie znaleziono artykułów o podanej nazwie");
                            else System.out.println(productList);
                        }
                        case 2 ->{
                            System.out.println("Podaj kod artykułu do dodania");
                            helper.addProductToCart(scanner.next(), manager.getProductsList());
                        }
                        case 3 -> System.out.println(helper.calculatePrice());
                        case 4 -> helper.showProductsInCart();
                        case 5 -> {
                            manager.removeProducts(helper.getShoppingCart());
                            manager.saveStorageToFile();
                            manager.printProducts();
                            end = true;
                        }
                        case 6 -> end = true;
                        default -> System.out.println("Błędny wybór, spróbuj jeszcze raz");
                    }
                }while(!end);
            }
            default -> System.out.println("Błędny wybór");
        }
    }
}