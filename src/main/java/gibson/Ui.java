package gibson;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String ERROR_LINE = "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    private static final String LOGO = " _____ ______ ____   _____  ____  _   _\n" +
            "/ _____|_   _|  _ \\ / ____|/ __ \\| \\ | |\n" +
            "| |  __  | | | |_) | (___ | |  | |  \\| |\n" +
            "| | |_ | | | |  _ < \\___ \\| |  | | . ` |\n" +
            "| |__| |_| |_| |_) |____) | |__| | |\\  |\n" +
            "\\______|_____|____/|_____/ \\____/|_| \\_|\n";

    public void printWelcome() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println("Hello! I'm Gibson");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void printBye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you soon!");
        System.out.println(LINE);
    }

    public void printMessage(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_LINE);
        System.out.println(message);
        System.out.println(ERROR_LINE);
    }
}
