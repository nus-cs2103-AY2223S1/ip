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

        } else if (userInput.startsWith("todo ", 0)) {
            int indexOfSlash = userInput.indexOf("/");
            String temp = userInput.substring(indexOfSlash + 1, userInput.length());
            Task newToDo = new ToDo(userInput.substring(5, indexOfSlash), temp); // Stores user input
            inputTasks[count] = newToDo;
            count += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println(newToDo);
            System.out.println("Now you have " + this.count + " tasks in the list.");
            run();

        } else if (userInput.startsWith("deadline ", 0)) {
            int indexOfSlash = userInput.indexOf("/");
            String temp = userInput.substring(indexOfSlash + 1, userInput.length());
            Task newDeadline = new Deadline(userInput.substring(9, indexOfSlash), temp); // Stores user input
            inputTasks[count] = newDeadline;
            count += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println(newDeadline);
            System.out.println("Now you have " + this.count + " tasks in the list.");
            run();

        } else if (userInput.startsWith("event ", 0)) {
            int indexOfSlash = userInput.indexOf("/");
            String temp = userInput.substring(indexOfSlash + 1, userInput.length());
            Task newEvent = new Event(userInput.substring(6, indexOfSlash), temp); // Stores user input
            inputTasks[count] = newEvent;
            count += 1;
            System.out.println("Got it. I've added this task:");
            System.out.println(newEvent);
            System.out.println("Now you have " + this.count + " tasks in the list.");
            run();

        } else if (userInput.startsWith("mark ", 0)) {
            // truncate the front part
            String temp = userInput.substring(5, userInput.length());
            // System.out.println(temp);
            int indexToChange = Character.getNumericValue(temp.charAt(0));
            // System.out.println(indexToChange);
            if ((indexToChange > 0) || (indexToChange < 100)) {
                inputTasks[indexToChange - 1].markAsDone();
            }
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(inputTasks[indexToChange - 1]);
            run();

        } else if (userInput.startsWith("unmark ", 0)) {
            // truncate the front part
            String temp = userInput.substring(7, userInput.length());
            // System.out.println(temp);
            int indexToChange = Character.getNumericValue(temp.charAt(0));
            // System.out.println(indexToChange);
            if ((indexToChange > 0) || (indexToChange < 100)) {
                inputTasks[indexToChange - 1].markAsNotDone();
            }
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(inputTasks[indexToChange - 1]);
            run();

        } else if (userInput.equals("list")) {
            // System.out.println(inputMemory.length);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                Task currentTask = inputTasks[i];
                System.out.println((i + 1) + ". " + currentTask);
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
        // System.out.println("Hello from\n");
        System.out.println("Hello! I'm Pixel \r\nWhat can I do for you?");
        Pixel test = new Pixel();
        test.run();
    }
}

