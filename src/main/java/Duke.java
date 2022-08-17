import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // initialise variables
        Task inputArray[] = new Task[100];
        int counter = 0;

        // print out starting message
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // new scanner object to take in input
        Scanner sc = new Scanner(System.in);

        while (true) {
            // take in input every loop
            String input = sc.nextLine();
            String[] arrOfInput = input.split(" ");

            if (!input.equals("bye")) {
                // for when input is anything but bye

                if (!input.equals("list")) {
                    // for when input is anything but list

                    if (arrOfInput[0].equals("mark")) {
                        // to mark an element as done
                        int index = Integer.parseInt(arrOfInput[1]);
                        if (inputArray[index] != null) {
                            Task currTask = inputArray[index];
                            currTask.markAsDone();
                        }

                    } else if (arrOfInput[0].equals("unmark")) {
                        // to mark an element as undone
                        int index = Integer.parseInt(arrOfInput[1]);
                        if (inputArray[index] != null) {
                            Task currTask = inputArray[index];
                            currTask.markUndone();
                        }

                    } else {
                        // adding the element to the task list
                        counter++;

                        // message when new element is added to list
                        System.out.println("added: " + input);

                        // add the element to the array
                        inputArray[counter] = new Task(input);
                    }

                } else {
                    // list out the current list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputArray.length; i++) {
                        if (inputArray[i] != null) {
                            System.out.println(i + ". " + inputArray[i]);
                        }
                    }
                }

            } else {
                // end program when input is bye
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
