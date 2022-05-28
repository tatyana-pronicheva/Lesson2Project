package demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static demo.ConsoleApp.productRepository;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> list = new ArrayList<>();
    public void addProduct(int id){
        list.add(productRepository.getProductById(id));
    }
    public void removeProduct(int id){
        list.remove(productRepository.getProductById(id));
    }
    public void getList(){
        for (int i = 0; i< list.size() ; i ++){
            System.out.println(String.format("id: %d, title: %s, cost: %d",
                    list.get(i).getId(),
                    list.get(i).getTitle(),
                    list.get(i).getCost()
            ));
        }
        if(list.size()==0){
            System.out.println("Корзина пуста");
        }
    }
}
