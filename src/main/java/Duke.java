import java.sql.SQLOutput;
import java.util.Scanner;
public class Duke {

    private static final Task[] tasks = new Task[100];
    private static int pointer = 0;
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
        String s = scn.next();
        System.out.println("\t____________________________________________");
        switch (s) {
            case "bye":
                System.out.println("\t It's a great time talking with you.\n\t See you next time!");
                System.out.println("\t____________________________________________");
                scn.close();
                break;
            case "list":
                System.out.println("\t Here are the tasks in your list:");
                for (int i = 0; i < pointer; i++) {
                    System.out.println("\t " + (i + 1) + "." + tasks[i].toString());
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            case "mark": {
                int num = scn.nextInt();
                try {
                    tasks[num - 1].markAsDone();
                    System.out.println("\t I've marked this task as done:");
                    System.out.println("\t   " + tasks[num - 1].toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("The index is invalid!").getMessage());
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            case "unmark": {
                int num = scn.nextInt();
                try {
                    tasks[num - 1].markAsNotDone();
                    System.out.println("\t I've marked this task as not done yet:");
                    System.out.println("\t   " + tasks[num - 1].toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(new DukeException("The index is invalid!").getMessage());
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            case "todo":
                String description = scn.nextLine();
                if (description.trim().isEmpty()){
                    System.out.println(new DukeException("The description of a todo cannot be empty.").getMessage());
                }
                else {
                    tasks[pointer] = new Todo(description);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t   " + tasks[pointer].toString());
                    pointer++;
                    System.out.println("\t Now you have " + pointer + " tasks in the list.");
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            case "event": {
                String str = scn.nextLine();
                if (!str.contains("/at")) {
                    System.out.println(new DukeException("You must specify /at for an event task.").getMessage());
                }
                else {
                    String[] strArray = str.split("/at");
                    tasks[pointer] = new Event(strArray[0], strArray[1]);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t   " + tasks[pointer].toString());
                    pointer++;
                    System.out.println("\t Now you have " + pointer + " tasks in the list.");
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            case "deadline": {
                String str = scn.nextLine();
                if (!str.contains("/by")) {
                    System.out.println(new DukeException("You must specify /by for a deadline task.").getMessage());
                }
                else {
                    String[] strArray = str.split("/by");
                    tasks[pointer] = new Deadline(strArray[0], strArray[1]);
                    System.out.println("\t Got it. I've added this task:");
                    System.out.println("\t   " + tasks[pointer].toString());
                    pointer++;
                    System.out.println("\t Now you have " + pointer + " tasks in the list.");
                }
                System.out.println("\t____________________________________________");
                greeting();
                break;
            }
            default:
                System.out.println(new DukeException("Sorry. I don't understand your command!!!").getMessage());
                System.out.println("\t____________________________________________");
                greeting();
                break;
        }
    }

}
