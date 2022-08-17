import java.util.Scanner;

public class Duke {
    static Scanner input = new Scanner(System.in);
    //private static String response = "";
    //private static String[] responseStore = new String[100];

    public static void responseRepeater() {
        String response = input.nextLine();
        String[] responseStore = new String[100];
        int counter = 0;

        while (!response.equals("bye")) {
            if (response.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println("     " + (i + 1) + ". " + responseStore[i]);
                }
                response = input.nextLine();
            } else {
                responseStore[counter] = response;
                counter += 1;
                System.out.println("     added: " + response);
                response = input.nextLine();
            }
        }
        System.out.println("     Sad to see you go! Visit me again soon!");
    }

    public static void main(String[] args) {
        String greetings = "Good day to you! I'm Jake\n"
                            + "How can I help you?";
        System.out.println(greetings);
        responseRepeater();
    }
}
