import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
        String logo = "\n" +
        "   ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄\n" +
        "  ▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄\n" +
        "  ▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄\n" +
        "  ▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██\n" +
        "  ░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n" +
        "  ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n" +
        "  ░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n" +
        "    ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒\n" +
        "      ░  ░     ░  ░       ░      ░          ░  ░\n" +
        "                               ░\n";

        System.out.print(logo);
        System.out.println("Hi, I am LaMDA.\nHow may I assist you today?\n");
        greeting();

    }
    public static void greeting() {
        Scanner scn = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            String s = scn.next();
            System.out.println("\t____________________________________________");
            switch (s) {
                case "bye":
                    System.out.println("\t It's a great time talking with you.\n\t See you next time!");
                    System.out.println("\t____________________________________________");
                    cont = false;
                    scn.close();
                    break;
                case "list":
                    System.out.println("\t Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("\t " + (i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println("\t____________________________________________");
                    break;
                case "mark": {
                    int num = scn.nextInt();
                    try {
                        tasks.get(num - 1).markAsDone();
                        System.out.println("\t I've marked this task as done:");
                        System.out.println("\t   " + tasks.get(num - 1).toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The index you entered is invalid!").getMessage());
                    }
                    System.out.println("\t____________________________________________");
                    break;
                }
                case "unmark": {
                    int num = scn.nextInt();
                    try {
                        tasks.get(num - 1).markAsNotDone();
                        System.out.println("\t I've marked this task as not done yet:");
                        System.out.println("\t   " + tasks.get(num - 1).toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The index you entered is invalid!").getMessage());
                    }
                    System.out.println("\t____________________________________________");
                    break;
                }
                case "todo":
                    String description = scn.nextLine();
                    if (description.trim().isEmpty()){
                        System.out.println(new DukeException("The description of a todo cannot be empty.").getMessage());
                    }
                    else {
                        Todo newTodo = new Todo(description);
                        tasks.add(newTodo);
                        System.out.println("\t Got it. I've added this task:");
                        System.out.println("\t   " + newTodo.toString());
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                    System.out.println("\t____________________________________________");
                    break;
                case "event": {
                    String str = scn.nextLine();
                    if (!str.contains("/at")) {
                        System.out.println(new DukeException("You must specify /at for an event task.").getMessage());
                    }
                    else {
                        String[] strArray = str.split("/at");
                        Event newEvent = new Event(strArray[0], strArray[1]);
                        tasks.add(newEvent);
                        System.out.println("\t Got it. I've added this task:");
                        System.out.println("\t   " + newEvent.toString());
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                    System.out.println("\t____________________________________________");
                    break;
                }
                case "deadline": {
                    String str = scn.nextLine();
                    if (!str.contains("/by")) {
                        System.out.println(new DukeException("You must specify /by for a deadline task.").getMessage());
                    }
                    else {
                        String[] strArray = str.split("/by");
                        Deadline newDeadline = new Deadline(strArray[0], strArray[1]);
                        tasks.add(newDeadline);
                        System.out.println("\t Got it. I've added this task:");
                        System.out.println("\t   " + newDeadline.toString());
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    }
                    System.out.println("\t____________________________________________");
                    break;
                }
                case "delete": {
                    int num = scn.nextInt();
                    try {
                        Task toBeRemoved = tasks.get(num - 1);
                        tasks.remove(num - 1);
                        System.out.println("\t Noted. I've removed this task:");
                        System.out.println("\t   " + toBeRemoved.toString());
                        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The index you entered is invalid!").getMessage());
                    }
                    System.out.println("\t____________________________________________");
                    break;
                }
                default:
                    System.out.println(new DukeException("Sorry. I don't understand your command!!!").getMessage());
                    System.out.println("\t____________________________________________");
                    break;
            }
        }
    }

}
