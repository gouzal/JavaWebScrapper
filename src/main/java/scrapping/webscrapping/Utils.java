package scrapping.webscrapping;

/**
 *
 * @author Larbi
 */
public class Utils {

    public static int convertToInt(String str) {
        return switch (str) {
            case "One" ->
                1;
            case "Two" ->
                2;
            case "Three" ->
                3;
            case "Four" ->
                4;
            case "Five" ->
                5;
            default ->
                throw new IllegalArgumentException("Number not Supported Exception");
        };
    }
}
