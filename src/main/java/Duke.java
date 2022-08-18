import java.util.Scanner;

public class Duke {
    static int counter = 0;

    /**
     * Initialises counter back to 0.
     */
    public static void initialiseCounter() {
        counter = 0;
    }


    /**
     * Prints the starting message.
     */
    public static void sayGreeting() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }


    /**
     * Prints the end message.
     */
    public static void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void printList(Task[] arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println(i + ". " + arr[i]);
            }
        }
    }


    public static void markTaskAsDone(Task[] arr, int index) {
        if (arr[index] != null) {
            Task currTask = arr[index];
            currTask.markAsDone();
        }
    }


    public static void unmarkTask(Task[] arr, int index) {
        if (arr[index] != null) {
            Task currTask = arr[index];
            currTask.markUndone();
        }
    }


    public static void createNewTask(String firstWord, String[] strArray, Task[] inputArray) throws DukeException {
        if (firstWord.equals("todo")) {
            // throw exception if no word after to-do
            if (strArray.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            StringBuilder todoStr = new StringBuilder();
            for (int i = 1; i < strArray.length; i++) {
                todoStr.append(" ").append(strArray[i]);
            }
            counter++;
            inputArray[counter] = new Todo(todoStr.toString());
            System.out.println("Got it. I've added this task:\n  " + inputArray[counter] +
                    "\nNow you have " + counter + " tasks in the list.");

        } else if (firstWord.equals("deadline")) {
            // throw exception if no word after deadline
            if (strArray.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }

            // throw exception if no deadline time
            int indexCheck = 1000;
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    indexCheck = i;
                }
            }
            if (indexCheck == 1000) {
                throw new DukeException("This description needs a timing! Add again with /by followed by the deadline timing.");
            }

            // create deadline string and deadline
            StringBuilder deadlineStr = new StringBuilder();
            StringBuilder deadline = new StringBuilder();
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    break;
                } else {
                    deadlineStr.append(" ");
                    deadlineStr.append(strArray[i]);
                }
            }
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/by")) {
                    for (int j = i + 1; j < strArray.length; j++) {
                        deadline.append(" ");
                        deadline.append(strArray[j]);
                    }
                    break;
                }
            }
            counter++;
            inputArray[counter] = new Deadline(deadlineStr.toString(), deadline.toString());
            System.out.println("Got it. I've added this task:\n  " + inputArray[counter] +
                    "\nNow you have " + counter + " tasks in the list.");

        } else if (firstWord.equals("event")) {
            // throw exception if no word after event
            if (strArray.length < 2) {
                throw new DukeException("The description of an event cannot be empty.");
            }

            // throw exception if no event time
            int indexCheck = 1000;
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/at")) {
                    indexCheck = i;
                }
            }
            if (indexCheck == 1000) {
                throw new DukeException("This description needs a timing! Add again with /at followed by the deadline timing.");
            }

            // create event string and deadline
            StringBuilder eventStr = new StringBuilder();
            StringBuilder eventTime = new StringBuilder();
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/at")) {
                    break;
                } else {
                    eventStr.append(" ");
                    eventStr.append(strArray[i]);
                }
            }
            for (int i = 1; i < strArray.length; i++) {
                if (strArray[i].equals("/at")) {
                    for (int j = i + 1; j < strArray.length; j++) {
                        eventTime.append(" ");
                        eventTime.append(strArray[j]);
                    }
                    break;
                }
            }
            counter++;
            inputArray[counter] = new Event(eventStr.toString(), eventTime.toString());
            System.out.println("Got it. I've added this task:\n  " + inputArray[counter] +
                    "\nNow you have " + counter + " tasks in the list.");

        }
    }


    public static void main(String[] args) throws DukeException {
        // initialise counter
        initialiseCounter();

        // print out starting message
        sayGreeting();

        // initialise variables
        Task[] inputArray = new Task[100];
        Scanner sc = new Scanner(System.in);

        while (true) {
            // take in input every loop
            String input = sc.nextLine();
            String[] arrOfInput = input.split(" ");
            String firstWord = arrOfInput[0];

            try {
                if (firstWord.equals("bye")) {
                    // end program when input is bye
                    sayGoodbye();
                    break;

                } else if (firstWord.equals("list")) {
                    // list out the current list
                    printList(inputArray);

                } else if (firstWord.equals("mark")) {
                    // to mark an element as done
                    int index = Integer.parseInt(arrOfInput[1]);
                    markTaskAsDone(inputArray, index);

                } else if (firstWord.equals("unmark")) {
                    // to mark an element as undone
                    int index = Integer.parseInt(arrOfInput[1]);
                    unmarkTask(inputArray, index);

                } else if (firstWord.equals("todo")) {
                    // adding the to-do to the list
                    createNewTask(firstWord, arrOfInput, inputArray);

                } else if (firstWord.equals("deadline")) {
                    // adding the deadline to the list
                    createNewTask(firstWord, arrOfInput, inputArray);

                } else if (firstWord.equals("event")) {
                    // adding the event to the list
                    createNewTask(firstWord, arrOfInput, inputArray);

                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
