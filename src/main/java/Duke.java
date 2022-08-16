import java.util.Scanner;

public class Duke {
    private static String[] tasks = new String[100];
    private static int numTask = 0;
    private static Scanner sc = new Scanner((System.in));
    public static void main(String[] args) {
        System.out.println("Hello! I'm Fungusta\n" + "Peter's personal chatbot\n");
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            InputReader(userInput);
            userInput = sc.nextLine();
        }
        System.out.println("Goodbye!");
    }

    public static void InputReader(String userInput) {
        String output = "";
        if (userInput.equals("list")) {
            for(int i = 0; i < numTask; i++) {
                int num = i + 1;
                String header = num + ". ";
                System.out.println(header + tasks[i]);
            }
            System.out.println("");
        } else {
            tasks[numTask] = userInput;
            numTask++;
            System.out.println("added: " + userInput + "\n");
        }
    }
}
