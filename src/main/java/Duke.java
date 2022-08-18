import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner dukeScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello i'm Duke\nHow may i help you today?");
        String[] taskList  = new String[100];
        int count = 0;

        String input;
        while (true) {
            input = dukeScanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                checkTask(input, taskList, count);
                count++;
            }
        }
    }

    private static void checkTask(String input, String[] list, int index) {
        switch (input) {
            case "list" :
                taskDisplay(list, index);
                break;
            default:
                System.out.println("added: " + input);
                list[index] = input;
        }
    }

    private static void taskDisplay(String[] list, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i]));
        }
    }
}
