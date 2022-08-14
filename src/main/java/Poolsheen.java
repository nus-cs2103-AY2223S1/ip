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

    private static final String exitCommand = "bye";

    private static final String listCommand = "list";

    private static final String markCommand = "mark ";

    private static final String unmarkCommand = "unmark ";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The current input that the poolsheen object has received */
    private String userInput;

    /** The scanner object which this poolsheen uses */
    private Scanner scanner;

    /** The list of tasks that the poolsheen object has */
    private Task[] listOfTasks;

    /** The index position to place the next task into the list */
    private int nextEmptyIndex;

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen() {
        this.listOfTasks = new Task[100];
        this.nextEmptyIndex = 0;
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
            if (this.userInput.equals(Poolsheen.exitCommand)) {
                this.exit();
            } else if (this.userInput.equals(Poolsheen.listCommand)) {
                this.displayList();
            } else if (this.userInput.length() >= 5 &&
                    this.userInput.substring(0, 5).
                            equals(Poolsheen.markCommand)) {
                int pos = java.lang.Integer.parseInt(
                        this.userInput.substring(5));
                this.mark(pos);
            } else if (this.userInput.length() >= 7 &&
                this.userInput.substring(0, 7).
                        equals(Poolsheen.unmarkCommand)) {
                int pos = java.lang.Integer.parseInt(
                        this.userInput.substring(7));
                this.unmark(pos);
            } else {
                this.addTask(new Task(this.userInput));
                this.say("Poolsheen now remembers: " + this.userInput);
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
     * Stores a task into the list of task.
     * @param task The task to be added.
     */
    private void addTask(Task task) {
        this.listOfTasks[this.nextEmptyIndex] = task;
        this.nextEmptyIndex += 1;
    }

    /**
     * Prints the list of tasks this Poolsheen remembers.
     */
    private void displayList() {
        if (this.nextEmptyIndex == 0) {
            this.say("Poolsheen thinks back... " +
                    "and remembers you said nothing :(");
        } else {
            String displayStr = "Poolsheen thinks back... " +
                    "and remembers you said:";
            int currPos = 1;
            for (Task task : this.listOfTasks) {
                if (task != null) {
                    String line = currPos + "." + " [" + task.getStatusIcon() + "] " + task.description;
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
         Task selectedTask = this.listOfTasks[pos-1];
         selectedTask.markAsDone();
         this.say("Poolsheen thinks you are done with "
                 + selectedTask.description);
    }

    /**
     * Marks a task as not done assuming the user input is correct.
     * @param pos The index position of the task in the list.
     */
    private void unmark(int pos) {
        Task selectedTask = this.listOfTasks[pos-1];
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
