package duke.utils;

public class Ui {

    private static final String logo =   "        / \\     |_   _| | |  / _|                   | |\n"
                                        + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                                        + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                                        + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                                        + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

    private static final String greeting = "                      Hello! I am \n" + logo +
                                    "      Your personal assistant. What can I do for you?\n";


    private static final String goodbye = "               ☺ Saving your data before you go...\n" +
                                          "               Bye. Hope to see you again soon!\n";


    public static void printMessage(String message) {
        System.out.println("____________________________________________________________\n" +
                           message +
                           "____________________________________________________________\n");
    }

    /**
     * Prints the greeting message to the console
     */
    public static void greet() {
        printMessage(greeting);
    }

    /**
     * Prints the goodbye message to the console
     */
    public static void bye() {
        printMessage(goodbye);
    }


}
