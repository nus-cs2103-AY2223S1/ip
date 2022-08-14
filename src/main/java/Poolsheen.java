import java.util.Scanner;

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

    private static final String exitCommand = "bye";

    private static final String listCommand = "list";

    private static final String exitMessage = "MeoAww... See you next time :(";

    private static final String endMessage = "THE POOLSHEEN PROGRAM HAS STOPPED RUNNING";

    /** Whether if this poolsheen object has stopped running */
    private boolean hasExited;

    /** The current input that the poolsheen object has received */
    private String userInput;

    /** The scanner object which this poolsheen uses */
    private Scanner scanner;

    /** The list of tasks that the poolsheen object has */
    private String[] listOfTasks;

    /** The index position to place the next task into the list */
    private int nextEmptyIndex;

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen() {
        this.listOfTasks = new String[100];
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
            if (this.userInput.equals(this.exitCommand)) {
                this.exit();
            } else if (this.userInput.equals(this.listCommand)) {
                this.displayList();
            } else {
                this.addTask(this.userInput);
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
    private void addTask(String task) {
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
            for (String task : this.listOfTasks) {
                if (task != null) {
                    String line = currPos + "." + " " + task;
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

    public static void main(String[] args) {
        Poolsheen ps = new Poolsheen();
        System.out.println(ps.welcomeMessage);
        ps.run();
        System.out.println(ps.endMessage);
    }
}
