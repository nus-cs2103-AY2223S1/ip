package models;

public class Ui {
    private static final String logo =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String separator = "––––––––––––––––––––––––––––––––––––––––";

    public void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.print("Tell me what you need\n");
    }

    public void exit() {
        System.out.print("Goodbye!");
    }

    public void showError(String errorMessage) {
        System.out.println(separator);
        System.out.println("OOPS! " + errorMessage);
        System.out.println(separator);
    }

    public void showResponse(String response) {
        System.out.println(separator);
        System.out.println(response);
        System.out.println(separator);
    }
}
