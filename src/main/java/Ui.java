public class Ui {
    final static String DIVIDER_LINE = "\t-----------------------------------------------";

    public Ui() {
    }

    public static void showWelcome() {
        showLine();
        System.out.println("\tHello! I'm Duke Dukem\n\tWhat can I do for you?");
        showLine();
    }

    public static void showGoodbye() {
        showLine();
        System.out.println("\tBye. Hope to see you again soon!");
        showLine();
    }

    public static void showLine() {
        System.out.println(DIVIDER_LINE);
    }

    public static void show(String line) {
        System.out.println(line);
    }

}
