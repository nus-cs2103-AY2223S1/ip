package roger;

import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        String logo = "  ____                                \n"
                    + " |  _ \\    ___     __ _    ___   _ __ \n"
                    + " | |_) |  / _ \\   / _` |  / _ \\ | '__|\n"
                    + " |  _ <  | (_) | | (_| | |  __/ | |   \n"
                    + " |_| \\_\\  \\___/   \\__, |  \\___| |_|   \n"
                    + "                  |___/               ";
        System.out.println("Hello its \n" + logo);
        System.out.println("What you wan? What you wan?");
    }

    public void show(String string) {
        System.out.println(string);
    }

    public void show(String[] strings) {
        for (String string: strings) {
            this.show(string);
        }
    }

    public void showcase(String preface, String content) {
        System.out.println(preface);
        System.out.println("  " + content);
    }

    public void showFarewell() {
        System.out.println("Bye bye niece and nephew.");
    }

    public void showException(Exception exception) {
        System.out.println("Nephew, what you doing? " + exception.getMessage());
    }

    public String getUserInput() {
        return new Scanner(System.in).nextLine();
    }

}
