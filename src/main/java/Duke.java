import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] taskList = new String[100];
        int index = 0;
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
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println(i + ". " + list[i]);
            } else {
                break;
            }
        }
    }
}
