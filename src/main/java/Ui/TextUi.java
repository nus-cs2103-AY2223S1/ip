package Ui;

public class TextUi {

    private static final String line = "____________________________________________________________\n";

    public static void print(String output) {
        StringBuilder printable = new StringBuilder(line);
        printable.append(output);
        printable.append("\n");
        printable.append(line);
        System.out.println(printable);
    }
}
