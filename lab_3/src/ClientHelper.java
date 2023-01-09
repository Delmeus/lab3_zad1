import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientHelper {


    private final List<Product> shoppingCart = new ArrayList<>();

    public void showProductsInCart(){
        System.out.println(shoppingCart);
    }

    public List<Product> checkForProduct(String name, List<Product> productList){
        List<Product> foundProducts = new ArrayList<>();
        String myPattern = name.replace("?","[a-zA-Z]");
        myPattern = myPattern.replace("*","[a-zA-Z]+");

        for (Product product : productList) {
            Pattern pattern = Pattern.compile(myPattern);
            Matcher matcher = pattern.matcher(product.getName());
            if(matcher.matches()){
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    public double calculatePrice(){
        double sum = 0;
        for(Product s : shoppingCart){
           sum += s.getPrice();
        }
        return sum;
    }

    public void addProductToCart(String code, List<Product> productsList){
        for(Product s : productsList){
            if(s.getCode().matches(code)){
                shoppingCart.add(s);
                System.out.println("Pomyślnie dodano " + s + " do koszyka");
            }
        }
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }
}
