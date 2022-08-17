import java.util.Scanner;

public class Duke {
    // Class Fields
    private static final String logo = " ___  ___  __ __ \n"
                                     + "| . \\| __>|  \\  \\\n"
                                     + "|   /| _> |     |\n"
                                     + "|_\\_\\|___>|_|_|_|";
    private static final String SPACER = "----------------------------------------------------";
    private static final String WELCOME = "こんにちは (Konnichiwa)! Rem だよ! (I'm Rem!) :>\n"
            + "今日は何ができますか? (What can I do for you today?)";

    protected CheckList checklist;

    // Constructor
    public Duke() {
        checklist = new CheckList();
    }

    // Class Methods
    private void start() {
        System.out.println(logo + "\n");
        System.out.println(WELCOME);
        System.out.println(SPACER);
    }

    private void run() {
        String input;
        Scanner sc = new Scanner(System.in);
        // Keep input on standby until user enters "bye"
        while (true) {
            System.out.print(">> ");
            input = sc.nextLine();
            String keyWord = input.split(" ")[0];
            switch (keyWord) {
                case "list":
                    System.out.println(SPACER);
                    System.out.println("Here's your list ^3^:");
                    System.out.println(checklist.printList());
                    System.out.println(SPACER);
                    break;
                case "bye":
                    System.out.println("またね! (See you soon!) <3");
                    System.exit(0);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(input.split(" ")[1]);
                    checklist.tasks.get(taskNum - 1).markDone();
                    System.out.println(SPACER);
                    System.out.println("Great Job on completing this task! ^.^ :");
                    System.out.println(checklist.printTaskStatus(taskNum - 1));
                    System.out.println(SPACER);
                    break;
                case "unmark":
                    taskNum = Integer.parseInt(input.split(" ")[1]);
                    checklist.tasks.get(taskNum - 1).markUndone();
                    System.out.println(SPACER);
                    System.out.println("Grrr, remember to finish your task! =3=:");
                    System.out.println(checklist.printTaskStatus(taskNum - 1));
                    System.out.println(SPACER);
                    break;
                default:
                    Task task = new Task(input);
                    checklist.addTask(task);
                    System.out.println(SPACER);
                    System.out.println("I've added '" + input + "' to your list. :)");
                    System.out.println(SPACER);
                    break;
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        Duke dk = new Duke();
        dk.start();
        dk.run();
    }
}
