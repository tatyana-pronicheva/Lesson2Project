package demo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> list = new ArrayList<>();

    public ProductRepository (){
        list.add(new Product(1,"Milk", 50));
        list.add(new Product(2,"Bread", 20));
        list.add(new Product(3, "Meat", 300));
        list.add(new Product( 4, "Butter", 200));
        list.add(new Product(5, "Eggs", 100));
    }

    public void getList(){
        for (int i = 0; i< list.size() ; i ++){
            System.out.println(String.format("id: %d, title: %s, cost: %d",
                    list.get(i).getId(),
                    list.get(i).getTitle(),
                    list.get(i).getCost()
            ));

        }
    }

    public Product getProductById(int productId){
        for(int i=0; i<list.size(); i++){
            if (list.get(i).getId() == productId){return list.get(i);}
        }
        return null;
    }
}
