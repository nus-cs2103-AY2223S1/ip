import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String receiveCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void add(Task newTask, Task[] taskList) {
        taskList[newTask.numberOfTasks - 1] = newTask;
        System.out.println("\tadded / ajouté:\n\t " + newTask.toString());
        System.out.println("\tYou now have " + newTask.numberOfTasks + " task(s)!\n" +
                           "\tVous avez " + newTask.numberOfTasks + " tâche(s)!");
    }

    private static void list(Task[] taskList) {
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.println("\t" + (i+1) + ".\t " + taskList[i].toString());
        }
    }

    private static void mark(Task[] taskList, String taskIndex) {
        Task curr = taskList[(Integer.parseInt(taskIndex) - 1)];
        curr.setIsDone(true);
        System.out.println("\tI have marked it as done:\n" +
                           "\tJe l'ai marqué comme fait:\n\t" +
                           curr.toString());
    }

    private static void unmark(Task[] taskList, String taskIndex) {
        Task curr = taskList[(Integer.parseInt(taskIndex) - 1)];
        curr.setIsDone(false);
        System.out.println("\tI have marked it as undone:\n" +
                           "\tJe l'ai marqué comme défait:\n\t" +
                           curr.toString());
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jean\n" +
                           "How can I help you?\n" +
                           "Bonjour! Je m'appelle Jean\n" +
                           "Vous désirez?\n");

        Task[] taskList = new Task[100];

        while(true) {
            String input = receiveCommand();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(taskList);
            } else if (input.startsWith("mark ")) {
                mark(taskList, input.substring(5));
            } else if (input.startsWith("unmark ")) {
                unmark(taskList, input.substring(7));
            } else if (input.startsWith("todo ")) {
                add(new Todo(input.substring(5)), taskList);
            } else if (input.startsWith("deadline ")) {
                int sep = input.indexOf("/by");
                add(new Deadline(input.substring(9, sep), input.substring(sep + 4)),
                        taskList);
            } else if (input.startsWith("event ")) {
                int sep = input.indexOf("/at");
                add(new Event(input.substring(6, sep), input.substring(sep + 4)),
                        taskList);
            }
        }
    }
}
