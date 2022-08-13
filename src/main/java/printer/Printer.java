package printer;

public class Printer {
    private static final String DASH = "----------------------------------------";

    public static void print(String text) {
        System.out.print(String.format("%s\n%s\n%s\n", DASH, text, DASH));
    }
}
