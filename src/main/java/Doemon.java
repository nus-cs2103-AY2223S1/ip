import java.util.Scanner;

public class Doemon {
    /**
     * Picture of Doemon made using ASCII art
     */
    private static String logo = "　　　　　　　　　 　　　　　  ＿＿＿＿＿\n" +
            "　　　　　　　　　　　　　　／ －､ －､　　 ＼\n" +
            "　　　　　　　　　　　　　/  | ・|・ |  ､　　＼\n" +
            "　　　　　　　　　　　　/ ／　`-●－  ′　  ＼   ヽ\n" +
            "　　　　　　　　　　　　|.　──　|　 ──　 　 |　 |\n" +
            "　　　　　　　　　　　　|　──　 |　 ── 　 　| 　l\n" +
            "　　　　　　　　　　　　ヽ （＿＿|＿＿＿＿)　/　 /\n" +
            "　　　　　　　　　　　 　＼　　　　　　　　 /　/\n" +
            "　　　　　　　　　　　 　 　l━━（ｔ）━━━━┥＿___";

    /**
     * Introduction string that is printed when Doemon is started.
     */
    private static String introStr = "Hello I'm\n" + logo + "\tDoemon!";

    /**
     * The string that is printed when Doemon is exited.
     */
    private static String exitStr = "I'm going to sleep now...See you again soon!";

    /**
     * A fixed-sized string array that stores user input.
     */
    private Task[] tasks = new Task[100];
    /**
     * An integer representing the index of the array that the next task will be stored.
     */
    private int taskIndex = 0;

    public static void main(String[] args) {
        // Introduction
        System.out.println(output(introStr));

        new Doemon().start();
    }

    /**
     * Starts the Doemon chatbot.
     */
    private void start() {
        // Handling inputs
        Scanner sc = new Scanner(System.in);

        while (true) {
            String inputStr = sc.nextLine();
            // Exit the chatbot
            if (inputStr.equals("bye")) {
                System.out.println(output(exitStr));
                break;
            }
            // List tasks
            if (inputStr.equals("list")) {
                int listNum = 1;
                String listStr = "";
                for (Task task: tasks) {
                    if (task == null) break;
                    listStr += listNum++ + "." + task.toString() + "\n\t";
                }
                listStr = listStr == "" ? "You have no tasks!" : listStr.trim();
                System.out.println(output(listStr));
                continue;
            }
            // Check for mark/unmark
            String[] inputArr = inputStr.split(" ");
            if (inputArr.length == 2
                    && (inputArr[0].equals("mark") || inputArr[0].equals("unmark"))
                    && isInteger(inputArr[1])) {
                int index = Integer.parseInt(inputArr[1]) - 1;
                if (index >= 0 && index < this.taskIndex) {
                    if (inputArr[0].equals("mark")) {
                        this.tasks[index].mark();
                        System.out.println(
                                output("Yay! This task is now marked as done:\n\t  "
                                        + this.tasks[index].toString()));
                    } else {
                        this.tasks[index].unmark();
                        System.out.println(
                                output("I guess you weren't done with that one:\n\t  "
                                        + this.tasks[index].toString()));
                    }
                    continue;
                }
            }
            // Add item to list of tasks
            this.tasks[this.taskIndex++] = new Task(inputStr);
            System.out.println(output("added: " + inputStr));
        }
    }

    /**
     * Used to check if the input string after mark/unmark is an integer.
     * @param text the string input to be checked
     * @return a boolean indicating if the text can be parsed into an integer
     */
    private static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns a formatted string to display the given text.
     *
     * @param text the text to be formatted
     * @return the formatted string
     */
    private static String output(String text) {
        String line = "____________________________________________________________";
        String outputStr = "\t" + line + "\n" +
                "\t" + text + "\n" +
                "\t" + line;
        return outputStr;
    }
}
