import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // print out starting message
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // initialise variables
        Task inputArray[] = new Task[100];
        int counter = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            // take in input every loop
            String input = sc.nextLine();
            String[] arrOfInput = input.split(" ");
            String firstWord = arrOfInput[0];

            if (firstWord.equals("bye")) {
                // end program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (firstWord.equals("list")) {
                // list out the current list
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < inputArray.length; i++) {
                    if (inputArray[i] != null) {
                        System.out.println(i + ". " + inputArray[i]);
                    }
                }

            } else if (firstWord.equals("mark")) {
                // to mark an element as done
                int index = Integer.parseInt(arrOfInput[1]);
                if (inputArray[index] != null) {
                    Task currTask = inputArray[index];
                    currTask.markAsDone();
                }

            } else if (firstWord.equals("unmark")) {
                // to mark an element as undone
                int index = Integer.parseInt(arrOfInput[1]);
                if (inputArray[index] != null) {
                    Task currTask = inputArray[index];
                    currTask.markUndone();
                }

            } else if (firstWord.equals("todo")) {
                // adding the to-do to the list
                String todoStr = "";
                for (int i = 1; i < arrOfInput.length; i++) {
                    todoStr += " " + arrOfInput[i];
                }
                counter++;
                inputArray[counter] = new Todo(todoStr);
                System.out.println("Got it. I've added this task:\n  " + inputArray[counter] +
                        "\nNow you have " + counter + " tasks in the list.");

            } else if (firstWord.equals("deadline")) {
                // adding the deadline to the list

                // create deadline string and deadline
                StringBuilder deadlineStr = new StringBuilder();
                StringBuilder deadline = new StringBuilder();
                for (int i = 1; i < arrOfInput.length; i++) {
                    if (arrOfInput[i].equals("/by")) {
                        break;
                    } else {
                        deadlineStr.append(" ");
                        deadlineStr.append(arrOfInput[i]);
                    }
                }
                for (int i = 1; i < arrOfInput.length; i++) {
                    if (!arrOfInput[i].equals("/by")) {
                    } else {
                        for (int j = i + 1; j < arrOfInput.length; j++) {
                            deadline.append(" ");
                            deadline.append(arrOfInput[j]);
                        }
                        break;
                    }
                }
                counter++;
                inputArray[counter] = new Deadline(deadlineStr.toString(), deadline.toString());
                System.out.println("Got it. I've added this task:\n  " + inputArray[counter] +
                        "\nNow you have " + counter + " tasks in the list.");

            } else if (firstWord.equals("event")) {
                // adding the event to the list

                // create event string and deadline
                StringBuilder eventStr = new StringBuilder();
                StringBuilder eventTime = new StringBuilder();
                for (int i = 1; i < arrOfInput.length; i++) {
                    if (arrOfInput[i].equals("/at")) {
                        break;
                    } else {
                        eventStr.append(" ");
                        eventStr.append(arrOfInput[i]);
                    }
                }
                for (int i = 1; i < arrOfInput.length; i++) {
                    if (!arrOfInput[i].equals("/at")) {
                    } else {
                        for (int j = i + 1; j < arrOfInput.length; j++) {
                            eventTime.append(" ");
                            eventTime.append(arrOfInput[j]);
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
    }
}
