import java.util.Scanner;

public class Duke {

    private static int index = 0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] taskList = new String[100];

        while (sc.hasNext()) {
            String output = sc.nextLine();
            // cannot use ==
            if (output.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (output.equals("list")) {
                printList(taskList);
            } else {
                taskList[index] = output;
                index++;
                System.out.println("added: " + output);
            }
        }
    }

    public static void printList(String[] list) {
        for (int i = 0; i < index; i++) {
            System.out.println(i + ". " + list[i]);
        }
    }
}
