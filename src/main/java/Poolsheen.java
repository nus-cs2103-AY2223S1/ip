import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for the Poolsheen CLI program.
 * @author Ong Wee, Marcus (Tut Grp 03)
 * @version CS2103 AY22/23 Sem 1
 */

public class Poolsheen {
    private static final String logo = "\n" +
            "██████╗░░█████╗░░█████╗░██╗░░░░░░██████╗██╗░░██╗███████╗███████╗███╗░░██╗\n" +
            "██╔══██╗██╔══██╗██╔══██╗██║░░░░░██╔════╝██║░░██║██╔════╝██╔════╝████╗░██║\n" +
            "██████╔╝██║░░██║██║░░██║██║░░░░░╚█████╗░███████║█████╗░░█████╗░░██╔██╗██║\n" +
            "██╔═══╝░██║░░██║██║░░██║██║░░░░░░╚═══██╗██╔══██║██╔══╝░░██╔══╝░░██║╚████║\n" +
            "██║░░░░░╚█████╔╝╚█████╔╝███████╗██████╔╝██║░░██║███████╗███████╗██║░╚███║\n" +
            "╚═╝░░░░░░╚════╝░░╚════╝░╚══════╝╚═════╝░╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░╚══╝";

    private static final String welcomeMessage = "Hello from" + logo + "\n" +
            "Type something in for Poolsheen to respond to you:";

    private static final String lastReply = "meow *_*";

    private static final String horizontalLine = "---------------";

    private static final String startReply = "      ";

    private static final String exitMessage = "MeoAww... See you next time :(";

    private static final String endMessage = "THE POOLSHEEN PROGRAM HAS STOPPED RUNNING";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The current input that the poolsheen object has received */
    private String userInput;

    /** The scanner object which this poolsheen uses */
    private Scanner scanner;

    /** The list of tasks that the poolsheen object has */
    private ArrayList<Task> listOfTasks;

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen() {
        this.listOfTasks = new ArrayList<>(100);
        this.hasExited = false;
        this.userInput = "";
        this.scanner = new Scanner(System.in);
    }

    /**
     * The first method to be run for Poolsheen to listen to our user.
     */
    private void run() {
        while (!this.hasExited) {
            this.userInput = this.scanner.nextLine();
            //parse a string
            String[] arr = this.userInput.split(" ");
            //to convert primitive string array to arraylist<string>
            ArrayList<String> arl = new ArrayList<>();
            for (String s : arr) {
                arl.add(s);
            }
            String command = arl.get(0);
            arl.remove(0);

            switch (command) {
                case "bye":
                    this.exit();
                    break;
                case "list":
                    this.displayList();
                    break;
                case "mark":
                    this.mark(java.lang.Integer.parseInt(arl.get(0)));
                    break;
                case "unmark":
                    this.unmark(java.lang.Integer.parseInt(arl.get(0)));
                    break;
                case "todo":
                    String descTD = String.join(" ", arl);
                    ToDo t = new ToDo(descTD);
                    this.listOfTasks.add(t);
                    this.say("Poolsheen now remembers: " + descTD);
                    break;
                case "deadline":
                    String descD = String.join(" ", arl.subList(0, arl.indexOf("/by")));
                    String timeD = String.join(" ", arl.subList(arl.indexOf("/by") + 1, arl.size()));
                    Deadline d = new Deadline(descD, timeD);
                    this.listOfTasks.add(d);
                    this.say("Poolsheen now remembers: " + descD);
                    break;
                case "event":
                    String descE = String.join(" ", arl.subList(0, arl.indexOf("/at")));
                    String timeE = String.join(" ", arl.subList(arl.indexOf("/at") + 1, arl.size()));
                    Event e = new Event(descE, timeE);
                    this.listOfTasks.add(e);
                    this.say("Poolsheen now remembers: " + descE);
                    break;
                default:
                    this.say("Type in a valid command");
            }
        }
    }

    /**
     * The last method to be run for Poolsheen to stop listening to our user.
     */
    private void exit() {
        this.hasExited = true;
        this.say(Poolsheen.exitMessage);
    }

    /**
     * Prints the list of tasks this Poolsheen remembers.
     */
    private void displayList() {
        if (this.listOfTasks.isEmpty()) {
            this.say("Poolsheen thinks back... " +
                    "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... " +
                    "and remembers you said:";
            int currPos = 1;
            for (Task task : this.listOfTasks) {
                if (task != null) {
                    String line = currPos + ". " + task.toString();
                    displayStr += "\n" + Poolsheen.startReply + line;
                    currPos += 1;
                }
            }
            this.say(displayStr);
        }
    }

    /**
     * A method to format and print a message by Poolsheen.
     * @param message The message to be printed.
     */
    private void say(String message) {
        System.out.println(Poolsheen.horizontalLine + "\n" +
                Poolsheen.startReply + message + "\n" +
                Poolsheen.startReply +
                Poolsheen.lastReply +
                "\n" + Poolsheen.horizontalLine);
    }

    /**
     * Marks a task as done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void mark(int pos) {
         Task selectedTask = this.listOfTasks.get(pos-1);
         selectedTask.markAsDone();
         this.say("Poolsheen thinks you are done with "
                 + selectedTask.description);
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void unmark(int pos) {
        Task selectedTask = this.listOfTasks.get(pos-1);
        selectedTask.markAsNotDone();
        this.say("Poolsheen thinks you are not done with "
                + selectedTask.description);
    }

    public static void main(String[] args) {
        Poolsheen ps = new Poolsheen();
        System.out.println(ps.welcomeMessage);
        ps.run();
        System.out.println(ps.endMessage);
    }
}
