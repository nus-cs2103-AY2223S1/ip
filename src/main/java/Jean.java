import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jean {

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void add(Task newTask, Task[] taskList) {
        taskList[Task.numberOfTasks - 1] = newTask;
        System.out.println("\tadded / ajouté:\n\t\t" + newTask.toString());
        System.out.println("\tYou now have " + Task.numberOfTasks + " task(s)!\n" +
                           "\tVous avez " + Task.numberOfTasks + " tâche(s)!");
    }

    private static void list(Task[] taskList) {
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.println("\t" + (i+1) + ".\t " + taskList[i].toString());
        }
    }

    private static void mark(Task[] taskList, int taskIndex) {
        Task curr = taskList[taskIndex - 1];
        curr.setIsDone(true);
        System.out.println("\tI have marked it as done:\n" +
                           "\tJe l'ai marqué comme fait:\n\t" +
                           curr.toString());
    }

    private static void unmark(Task[] taskList, int taskIndex) {
        Task curr = taskList[taskIndex - 1];
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

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(taskList);
            } else if (input.startsWith("mark")) {
                try {
                    if (input.length() == 4) {
                        throw new JeanException("You must name a task to mark!");
                    } else {
                        mark(taskList, Integer.parseInt(input.substring(5)));
                    }
                } catch (JeanException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("unmark")) {
                try {
                    if (input.length() == 6) {
                        throw new JeanException("You must name a task to unmark!");
                    } else {
                        unmark(taskList, Integer.parseInt(input.substring(7)));
                    }
                } catch (JeanException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.length() == 4) {
                        throw new JeanException("The description must not be empty!");
                    } else {
                        add(new Todo(input.substring(5)), taskList);
                    }
                } catch (JeanException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("deadline")) {
                int sep = input.indexOf("/by");
                try {
                    if (sep == 9 || input.length() == 8) {
                        throw new JeanException("The description must not be empty!");
                    } else if (sep == -1){
                        throw new JeanException("You must give a deadline!");
                    } else {
                        add(new Deadline(input.substring(9, sep), input.substring(sep + 4)),
                                taskList);
                    }
                } catch (JeanException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("event")) {
                int sep = input.indexOf("/at");
                try {
                    if (sep == 6 || input.length() == 5) {
                        throw new JeanException("The description must not be empty!");
                    } else if (sep == -1){
                        throw new JeanException("You must give a time!");
                    } else {
                        add(new Event(input.substring(6, sep), input.substring(sep + 4)),
                                taskList);
                    }
                } catch (JeanException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
