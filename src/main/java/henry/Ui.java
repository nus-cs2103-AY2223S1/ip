package henry;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        System.out.println(
            "  _    _ ______ _   _ _______     __\n"
            + " | |  | |  ____| \\ | |  __ \\ \\   / /\n"
            + " | |__| | |__  |  \\| | |__) \\ \\_/ /\n"
            + " |  __  |  __| | . ` |  _  / \\   /\n"
            + " | |  | | |____| |\\  | | \\ \\  | |\n"
            + " |_|  |_|______|_| \\_|_|  \\_\\ |_|");
        sc = new Scanner(System.in);
        output("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?");
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void output(String message) {
        System.out.println(formatResponse(message));
    }

    public void close() {
        output("GOODBYE!");
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" + "\n HENRY: "
               + input + "\n" + "____________________________________________________________";
    }
}
