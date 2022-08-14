import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Class to encapsulate Task object
     */
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        /**
         * String encapsulates Task description and isDone status.
         * @return String representing Task object
         */
        @Override
        public String toString() {
            String status;
            if(this.isDone) {
                status = String.format("[✓] %s\n", this.description);
            } else {
                status = String.format("[ ] %s\n", this.description);
            }
            return status;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }
    }

    private static ArrayList<Task> userInputHistory = new ArrayList<>();
    private static void greetUser() {
        String logo = "_______     _\n" +
                "|  ___|    | |\n" +
                "|  |_  ____| |_____ ____  _  __\n" +
                "|   _|/ _  \\ | ___|/  _ \\| |/  \\\n"+
                "|  | | |_| | | |___| |_| |  / \\ |\n" +
                "|__|  \\__|_|_|____|\\____/|_|  |_|\n";
        System.out.println("Hello from" );
        System.out.println(logo);
        // prompt user
        System.out.println("Where would you like to go next?");
        System.out.print(">> ");
    }

    /**
     * Method to add user input to history
     * @param s String description to add to userInputHistory
     */
    private static void addToHistory(String s) {
        Task newTask = new Task(s);
        userInputHistory.add(newTask);
        //echo request
        System.out.printf("Noted down: %s\n", s);
    }

    /**
     * Method to show history
     */
    private static void showHistory() {
        int count = 1;
        System.out.print("______\n");
        System.out.println("Tasks in your list are: ");
        for (Task t: userInputHistory) {
            System.out.printf("%d. %s",count, t);
            count ++;
        }
        userInputHistory.forEach(input -> {

        });
        System.out.print("______\n");
    }

    /**
     * Mark task at index n in list.
     * No checks performed to check if task is already marked.
     * @param n task to mark as done (n - 1) index in actual list
     */
    public static void markTask(int n){
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsDone();
        System.out.printf("Marked task %d \n%s", n, taskToModify);
    }

    /**
     * Unmark task at index n in list.
     * No checks performed to check if task is already unmarked.
     * @param n task to mark as not done (n - 1) index in actual list
     */
    public static void unmarkTask(int n) {
        Task taskToModify = userInputHistory.get(n - 1);
        taskToModify.markAsNotDone();
        System.out.printf("Unmarked task %d \n%s", n, taskToModify);
    }

    /**
     * Extract task number from string input
     * @param s extracts task number from user input
     * @return index of the task in the list plus one
     */
    private static int getTaskNumber(String s) {
        // credit: https://stackoverflow.com/questions/14974033/extract-digits-from-string-stringutils-java
        String numberOnly= s.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberOnly);
    }

    private static void handleInput(String userInput) {
        if (userInput.equals("bye")) {
            //exit
            System.out.println("Thank you for swinging by :)");
            System.exit(0);
        } else if (userInput.equals("list")) {
            showHistory();
        } else if (userInput.startsWith("mark")) {
            markTask(getTaskNumber(userInput));
        } else if (userInput.startsWith("unmark")) {
            unmarkTask(getTaskNumber(userInput));
        } else {
                addToHistory(userInput);
            }
        System.out.print(">> ");
    }

    public static void main(String[] args) {
        greetUser();
        Scanner in = new Scanner(System.in);
        String s;
        while(true) {
            s = in.nextLine();
            handleInput(s);
        }
    }
}
