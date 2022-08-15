import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jean {

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void checkTodo(String input, ArrayList<Task> taskList) {
        try {
            if (input.length() == 4) {
                throw new JeanException("The description must not be empty!");
            } else {
                add(new Todo(input.substring(5)), taskList);
            }
        } catch (JeanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkDeadline(String input, ArrayList<Task> taskList) {
        int sep = input.indexOf("/by");
        try {
            if (sep == 9 || input.length() == 8) {
                throw new JeanException("The description must not be empty!");
            } else if (sep == -1) {
                throw new JeanException("You must give a deadline!");
            } else {
                add(new Deadline(input.substring(9, sep), input.substring(sep + 4)),
                        taskList);
            }
        } catch (JeanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkEvent(String input, ArrayList<Task> taskList) {
        int sep = input.indexOf("/at");
        try {
            if (sep == 6 || input.length() == 5) {
                throw new JeanException("The description must not be empty!");
            } else if (sep == -1) {
                throw new JeanException("You must give a time!");
            } else {
                add(new Event(input.substring(6, sep), input.substring(sep + 4)),
                        taskList);
            }
        } catch (JeanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void add(Task newTask, ArrayList<Task> taskList) {
        taskList.add(newTask);
        System.out.println("\tadded / ajouté:\n\t\t" + newTask.toString());
        System.out.println("\tYou now have " + Task.numberOfTasks + " task(s)!\n" +
                           "\tVous avez " + Task.numberOfTasks + " tâche(s)!");
    }

    private static void list(ArrayList<Task> taskList) {
        for (int i = 0; i < Task.numberOfTasks; i++) {
            System.out.println("\t" + (i+1) + ".\t " + taskList.get(i).toString());
        }
    }

    private static void checkMark(String input, ArrayList<Task> taskList) {
        try {
            if (input.length() == 4) {
                throw new JeanException("You must name a task to mark!");
            } else {
                mark(taskList, Integer.parseInt(input.substring(5)));
            }
        } catch (JeanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void mark(ArrayList<Task> taskList, int taskIndex) {
        Task curr = taskList.get(taskIndex - 1);
        curr.setIsDone(true);
        System.out.println("\tI have marked it as done:\n" +
                           "\tJe l'ai marqué comme fait:\n\t" +
                           curr.toString());
    }

    private static void checkUnmark(String input, ArrayList<Task> taskList) {
        try {
            if (input.length() == 6) {
                throw new JeanException("You must name a task to unmark!");
            } else {
                unmark(taskList, Integer.parseInt(input.substring(7)));
            }
        } catch (JeanException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void unmark(ArrayList<Task> taskList, int taskIndex) {
        Task curr = taskList.get(taskIndex - 1);
        curr.setIsDone(false);
        System.out.println("\tI have marked it as undone:\n" +
                           "\tJe l'ai marqué comme défait:\n\t" +
                           curr.toString());
    }

    private static void checkDelete(String input, ArrayList<Task> taskList) {
        try {
            if (input.length() == 6) {
                throw new JeanException("You must name a task to delete!");
            } else {
                delete(taskList, Integer.parseInt(input.substring(7)));
            }
        } catch (JeanException e) {
            System.out.println(e);
        }
    }

    private static void delete(ArrayList<Task> taskList, int taskIndex) {
        Task curr = taskList.get(taskIndex - 1);
        Task.numberOfTasks -= 1;
        taskList.remove(taskIndex - 1);
        System.out.println("\tI have deleted the task:" +
                           "\n\tJe l'ai supprimé:\n\t" +
                           curr.toString() +
                           "\n\tYou now have " + Task.numberOfTasks + " tasks remaining!" +
                           "\n\tIl vous reste maintenant " + Task.numberOfTasks + " tâches!");
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jean\n" +
                           "How can I help you?\n" +
                           "Bonjour! Je m'appelle Jean\n" +
                           "Vous désirez?\n");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(taskList);
            } else if (input.startsWith("mark")) {
                checkMark(input, taskList);
            } else if (input.startsWith("unmark")) {
                checkUnmark(input, taskList);
            } else if (input.startsWith("todo")) {
                checkTodo(input, taskList);
            } else if (input.startsWith("deadline")) {
                checkDeadline(input, taskList);
            } else if (input.startsWith("event")) {
                checkEvent(input, taskList);
            } else if (input.startsWith("delete")) {
                checkDelete(input, taskList);
            } else {
                System.out.println("No such command!");
            }
        }
    }
}
