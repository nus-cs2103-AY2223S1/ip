package Ui;

public class TextUi {

    private static final String line = "____________________________________________________________\n";

    public static String buildOutput(String output) {
        StringBuilder printable = new StringBuilder(line);
        printable.append(output);
        printable.append("\n");
        printable.append(line);
        return printable.toString();
    }

    public static void print(String output) {
        System.out.println(buildOutput(output));
    }
}
