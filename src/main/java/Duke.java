import java.util.Scanner;

public class Duke {
    private static String[] tasks= new String[100];
    private static void taskToString() {
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi, I'm Jamie.\nWhat do you want?");

        String input = sc.next();
        int j = 0;
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                taskToString();
                input = sc.next();
                continue;
            }
            tasks[j] = input;
            System.out.println("added: " + input);
            input = sc.next();
            j++;
        }

        System.out.println("bye >:(");

    }
}
