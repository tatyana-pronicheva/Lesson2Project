package demo;

import demo.configs.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Scanner;

public class ConsoleApp {
    boolean commandIsRecognized;
    static ApplicationContext context;
    static ProductRepository productRepository;
    Cart cart;

public static void main(String[] args ){
    context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    new ConsoleApp().startApp();
}
    @PostConstruct
        public void startApp(){
        System.out.println("Для выхода из приложения введите exit");
        System.out.println("Для получения корзины введите getCart");
        System.out.println("Для добавления продукта введите addProduct и через пробел id продукта");
        System.out.println("Для удаления продукта введите removeProduct и через пробел id продукта");
        System.out.println("Для получения списка продуктов getProductList");
        productRepository = context.getBean("productRepository", ProductRepository.class);
        cart = context.getBean(Cart.class);

        while (true) {
            Scanner scanIn = new Scanner(System.in);
            String input = scanIn.nextLine();
            commandIsRecognized = false;
            handle(input);
        }
    }
    public void handle(String command){
    try{
        if ("exit".equals(command)) {
            commandIsRecognized = true;
            System.out.println("Exit!");
            System.exit(0);
        }
        if ("getCart".equals(command)) {
            commandIsRecognized = true;
            cart.getList();
        }
        if (command.startsWith("addProduct")){
            String[] splittedCommand = command.split(" ");
            int productId = Integer.valueOf(splittedCommand[1]);
            cart.addProduct(productId);
            commandIsRecognized = true;
        }
        if (command.startsWith("removeProduct")){
            String[] splittedCommand = command.split(" ");
            int productId = Integer.valueOf(splittedCommand[1]);
            cart.removeProduct(productId);
            commandIsRecognized = true;
        }
        if ("getProductList".equals(command)) {
            commandIsRecognized = true;
            productRepository.getList();
        }
        if (commandIsRecognized ==false) {
            throw new RuntimeException("Команда не распознана");
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    }
}
