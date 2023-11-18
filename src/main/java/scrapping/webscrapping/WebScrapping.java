package scrapping.webscrapping;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Larbi
 */
public class WebScrapping {

    public static void main(String[] args) throws Exception {
        System.out.println("Start scrapping");
        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()){
        for (int i = 1; i <= 50; i++) {
           executorService.submit(new Scrapper(i));
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Start ends!");
    }


}
