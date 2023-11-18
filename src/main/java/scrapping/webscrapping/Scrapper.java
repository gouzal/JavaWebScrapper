package scrapping.webscrapping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Larbi
 */
public class Scrapper implements Runnable {

    private final int page;

    public Scrapper(int page) {
        this.page = page;
    }

    @Override
    public void run() {
        int cpt = 0;
        try {
            Document doc = Jsoup.connect("http://books.toscrape.com/catalogue/page-" + this.page + ".html").get();
            System.out.println("Page Title:" + doc.title());
            Elements elementsByClass = doc.getElementsByClass("product_pod");

            for (Element el : elementsByClass) {
                var photo = "https://books.toscrape.com/" + el.getElementsByClass("thumbnail").attr("src");
                var rating = el.getElementsByClass("star-rating").attr("class").split(" ")[1];
                var booktitle = el.select("h3 > a").first().attr("title");
                double price = Double.valueOf(el.getElementsByClass("price_color").first().text().substring(1));
                boolean inStock = !el.getElementsByClass("instock availability").isEmpty();
                System.out.println("Insert before -> Page:" + this.page + ", count:" + cpt);
                MySQLConnection.executeParametredQuery(this.page * 100 + cpt, photo, booktitle, Utils.convertToInt(rating), inStock, price);
                System.out.println("Insert after -> Page:" + this.page + ", count:" + cpt);
                cpt++;
            }
        } catch (Exception e) {
            System.out.println("Error parsing page:" + this.page + ", cpt=" + cpt + "," + e.getMessage());
        }
    }

}
