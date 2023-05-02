package model;

import model.Category;
import model.Controller;
import model.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ControllerTest {

    private Controller c;
    private ArrayList<Product> products;
    public void setup1 (){
        c = new Controller();
    }

    public void setup2(){
        c = new Controller();

        products = new ArrayList<>();
        Product product1 = new Product("Miguel in Wonderland", "An unexpected adventure awaits Miguel in" +
                " wonderland. Join him on his journey.", 100000, 7, Category.BOOKS,0);

        Product product2 = new Product("Cboc Two", "An incredible video game console.", 2000000,
                9, Category.ELECTRONIC, 0);

        Product product3 = new Product("HD laptop", "Intel Core, 2 Ram, 500GB HDD", 1200000,
                7, Category.ELECTRONIC, 0);

        products.add(product1);
        products.add(product2);
        products.add(product3);
    }

    @Test
    public void AddProductTest1(){
        setup1();
        assertEquals("Product added!", c.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
                "Join him on his journey", 100000, 7, 1, 0));

    }

    @Test
    public void AddProductTest2(){
        setup1();
        try{
            c.addProduct("", "An unexpected adventure awaits Miguel in wonderland. " +
                    "Join him on his journey", 100000, 7, 1, 0);

            fail("An exception is expected as the name is empty.");
        }catch (RuntimeException e){
            assertEquals("Error. The name of the product is empty.", e.getMessage());
        }
    }

    @Test
    public void AddProductTest3(){
        setup1();
        try{
            c.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
                    "Join him on his journey", 100000, -5, 1, 0);

            fail("An exception is expected as the quantity is negative.");
        }catch (RuntimeException e){
            assertEquals("Error. The quantity of product in inventory cannot be negative.", e.getMessage());
        }
    }

    @Test
    public void AddProductTest4(){
        setup1();
        try{
            c.addProduct("Miguel in wonderland", "An unexpected adventure awaits Miguel in wonderland. " +
                    "Join him on his journey", 100000, 7, 10, 0);

            fail("An exception is expected because the category don´t exist");
        }catch (RuntimeException e){
            assertEquals("Error. Invalid category.", e.getMessage());
        }
    }

    @Test
    public void AddOrderTest1(){
        setup2();

        assertEquals("Order added!", c.addOrder("Miguel", "1", products));
    }

    @Test
    public void AddOrderTest2(){
        setup2();

        try{
            c.addOrder("", "1", products);

            fail("An exception is expected as the buyer name is empty.");
        }catch (RuntimeException e){
            assertEquals("Error. The name of buyer is empty.", e.getMessage());
        }
    }

    @Test
    public void AddOrderTest3(){
        setup2();

        try{
            c.addOrder("Miguel", "", products);

            fail("An exception is expected as the order id is empty.");
        }catch (RuntimeException e){
            assertEquals("Error. The order id is empty.", e.getMessage());
        }
    }

    @Test
    public void AddOrderTest4(){
        setup2();
        ArrayList<Product> products1 = new ArrayList<>();
        try{
            c.addOrder("Miguel", "23", products1);

            fail("An exception is expected as the product list is empty.");
        }catch (RuntimeException e){
            assertEquals("Error. The product list is empty.", e.getMessage());
        }
    }
}
