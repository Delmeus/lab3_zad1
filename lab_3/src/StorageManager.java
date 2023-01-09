import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StorageManager {

    private final List<Product> productsList = new ArrayList<>();

    public StorageManager(){
       productsList.add(new Product("0001","pomidor",1.25));
       productsList.add(new Product("0002","lizak",0.5));
       productsList.add(new Product("0003","zeszyt",3));
       productsList.add(new Product("0004","sok jablkowy",4));
    }

    public void addProduct(Product product){
        boolean canAdd = true;
        for(Product s : productsList){
            if(s.getCode().matches(product.getCode())){
                System.out.println("Nie można dodać produktu, produkt o podanym kodzie juz istnieje");
                canAdd = false;
            }
        }
        if(canAdd){
            productsList.add(product);
            System.out.println("Pomyślnie dodano artykuł");
        }
    }

    public void removeProducts(List<Product> productsToRemove){
        for(Product s : productsToRemove){
            productsList.remove(s);
        }
    }

    public void saveStorageToFile(){
        try{
            File file = new File("src/products.txt");
            PrintWriter writer = new PrintWriter(file);
            for (Product s : productsList) {
                writer.println(s.toString());
            }
            writer.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void loadStorageFromFile(){
        try{
            File file = new File("src/products.txt");
            Scanner scanner = new Scanner(file);
            productsList.clear();
            while(scanner.hasNextLine()){
                String[] s = scanner.nextLine().split(",");
                productsList.add(new Product(s[0], s[1], Double.parseDouble(s[2])));
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void printProducts(){
        for (Product s : productsList) {
            System.out.println(s.toString());
        }
    }

    public List<Product> getProductsList() {
        return productsList;
    }
}
