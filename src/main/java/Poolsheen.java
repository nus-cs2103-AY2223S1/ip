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

    private static final String poolsheenLastReply = "meow *_*";

    private static final String horizontalLine = "---------------";

    private static final String poolshenStartReply = "      ";

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

    /**
     * A private constructor to initialise the Poolsheen object.
     */
    private Poolsheen() {
        this.listOfTasks = new String[100];
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
            } else {
                System.out.println(this.horizontalLine + "\n" + this.poolshenStartReply + this.userInput + " " +
                        this.poolsheenLastReply + "\n" + this.horizontalLine);
            }
        }
    }

    /**
     * The last method to be run for Poolsheen to stop listening to our user.
     */
    private void exit() {
        this.hasExited = true;
        System.out.println(this.horizontalLine + "\n" + this.poolshenStartReply
                + this.exitMessage + "\n" + this.horizontalLine);
    }

    public static void main(String[] args) {
        Poolsheen ps = new Poolsheen();
        System.out.println(ps.welcomeMessage);
        ps.run();
        System.out.println(ps.endMessage);
    }
}
