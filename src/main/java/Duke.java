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
        String greetings = "Good day to you! I'm Jake\n"
                            + "How can I help you?";
        System.out.println(greetings);
        responseRepeater();
    }
}
