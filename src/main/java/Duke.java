import java.util.Objects;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        System.out.println("               __\n" +
                "              / _)\n" +
                "     _.----._/ /\n" +
                "    /         /\n" +
                " __/ (  | (  |\n" +
                "/__.-'|_|--|_|\n");

        String line = "Dino:\n";
        Task[] ls = new Task[100];

        System.out.println(line
                + "\tHello! I'm Dino\n"
                + "\tWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String curr = "";
        int index;
        Task myTask;

        while (!Objects.equals(curr, "bye")) {
            curr = sc.nextLine();
            String[] str = curr.split("\\s", 2);
            System.out.print(line);
            switch(str[0]) {
                case "bye":
                    System.out.println("\tBye bye. Hope to see you again soon!");
                    break;
                case "list":
                    for (int i = 0; i < Task.lsSize(); i++) {
                        System.out.println("\t" + (i + 1)  + ". " + ls[i].toString());
                    }
                    break;
                case "mark":
                    index = Integer.parseInt(str[1]);
                    myTask = ls[index - 1];
                    myTask.markAsDone();
                    System.out.println("\tHooray! You have completed this task:\n\t" + myTask);
                    break;
                case "unmark":
                    index = Integer.parseInt(str[1]);
                    myTask = ls[index - 1];
                    myTask.markAsUndone();
                    System.out.println("\tOh no! You have more things to complete:\n\t" + myTask);
                    break;
                case "deadline":
                    String[] dl = str[1].split(" /by ");
                    myTask = new Deadline(dl[0], dl[1]);
                    ls[Task.lsSize() - 1] = myTask;
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " tasks!");
                    break;
                case "todo":
                    myTask = new ToDo(str[1]);
                    ls[Task.lsSize() - 1] = myTask;
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1? "s!" : "!"));
                    break;
                case "event":
                    String[] evnt = str[1].split(" /at ");
                    myTask = new Event(evnt[0], evnt[1]);
                    ls[Task.lsSize() - 1] = myTask;
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1? "s!" : "!"));
                    break;
                default:
                    myTask = new Task(curr);
                    ls[Task.lsSize() - 1] = myTask;
                    System.out.println("\tadded: " + myTask);
                    System.out.println("\tYou have " + Task.lsSize() + " task" + (Task.lsSize() > 1? "s!" : "!"));
                    break;
            }
            System.out.print("\n");
        }
    }
}
