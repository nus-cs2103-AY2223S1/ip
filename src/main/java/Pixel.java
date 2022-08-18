import java.util.Scanner;

public class Pixel {

    private static int count = 0;
    private Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
    private Task[] inputTasks = new Task[100];

    // char[] numbers = {"s", "s"};

//    private int getListIndex(String userInput) {
//        char[] inputCharArray = userInput.toCharArray();
//        for (char character: inputCharArray) {
//            if (numArray.contains()) {
//                return Character.getNumericValue(character);
//            }
//        };
//        return -1;
//    }

    private void run() {
        String userInput = myScanner.nextLine();  // Read user input

        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");

        } else if (userInput.startsWith("mark ", 0)) {
            String temp = userInput.substring(5, userInput.length());
            // System.out.println(temp);
            int indexToChange = Character.getNumericValue(temp.charAt(0));
            // System.out.println(indexToChange);
            if ((indexToChange > 0) || (indexToChange < 100)) {
                inputTasks[indexToChange - 1].markAsDone();
            }
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("[" + inputTasks[indexToChange - 1].getStatusIcon() + "] " + inputTasks[indexToChange - 1]);            run();
            run();

        } else if (userInput.startsWith("unmark ", 0)) {
            String temp = userInput.substring(7, userInput.length());
            // System.out.println(temp);
            int indexToChange = Character.getNumericValue(temp.charAt(0));
            // System.out.println(indexToChange);
            if ((indexToChange > 0) || (indexToChange < 100)) {
                inputTasks[indexToChange - 1].markAsNotDone();
            }
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + inputTasks[indexToChange - 1].getStatusIcon() + "] " + inputTasks[indexToChange - 1]);
            run();

        } else if (userInput.equals("list")) {
            // System.out.println(inputMemory.length);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                Task currentTask = inputTasks[i];
                System.out.println((i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask);
            }
            run();

        } else {
            inputTasks[count] = new Task(userInput); // Stores user input
            System.out.println(userInput);  // Output user input
            count += 1;
            run();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        System.out.println("Hello from\n");
        System.out.println("Hello! I'm Pixel \r\nWhat can I do for you?");
        Pixel test = new Pixel();
        test.run();
    }
}

