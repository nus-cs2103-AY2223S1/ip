import java.util.Scanner;

public class Duke {
    static Scanner input = new Scanner(System.in);
    static String response = "";

    public static void responseRepeater() {
        response = input.next();
        while (!response.equals("bye")) {
            System.out.println("    " + response);
            response = input.next();
        }
        System.out.println("    Sad to see you go! Visit me again soon!");
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/


        String greetings = "Good day to you! I'm Jake\n"
                            + "How can I help you?";
        System.out.println(greetings);
        responseRepeater();
        //String response = input.next();
        //System.out.println("You entered: " + response);
    }
}
