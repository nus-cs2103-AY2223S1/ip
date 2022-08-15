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

    private static void add(Task newTask, ArrayList<Task> taskList) {
        taskList.add(newTask);
        System.out.println("\tadded / ajouté: " + newTask.description + "\n");
    }

    private static void list(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ".\t " + taskList.get(i).toString());
        }
    }

    private static void mark(ArrayList<Task> taskList, String taskIndex) {
        Task curr = taskList.get(Integer.parseInt(taskIndex) - 1);
        curr.setIsDone(true);
        System.out.println("\tI have marked it as done:\n" +
                           "\tJe l'ai marqué comme fait:\n\t" +
                           curr.toString());
    }

    private static void unmark(ArrayList<Task> taskList, String taskIndex) {
        Task curr = taskList.get(Integer.parseInt(taskIndex) - 1);
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

        ArrayList<Task> taskList = new ArrayList<>();

        while(true) {
            String input = receiveCommand();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(taskList);
            } else if (input.length() > 4 && input.substring(0, 4).equals("mark")){
                mark(taskList, input.substring(5));
            } else if (input.length() > 6 && input.substring(0, 6).equals("unmark")){
                unmark(taskList, input.substring(7));
            } else {
                add(new Task(input), taskList);
            }
        }
    }
}
