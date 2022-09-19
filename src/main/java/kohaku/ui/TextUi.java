package kohaku.ui;

public class TextUi {

    private static final String LINE = "____________________________________________________________\n";

    public static String buildOutput(String output) {
        StringBuilder printable = new StringBuilder(LINE);
        printable.append(output);
        printable.append("\n");
        printable.append(LINE);
        return printable.toString();
    }

    public static void print(String output) {
        System.out.println(buildOutput(output));
    }
}
