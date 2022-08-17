import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Prints the string representation of all tasks that has been
     * added to the arraylist via the Task toString method.
     * @param inputList
     */
    public static void printList(ArrayList<Task> inputList) {
        Task[] inputArray = inputList.toArray(new Task[inputList.size()]);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= inputArray.length; i++) {
            String item = String.format("%s. %s", i, inputArray[i-1].toString());
            System.out.println(item);
        }
    }

    /**
     * Marks the task as completed via the index of the task on the
     * arraylist. The method throws an ArrayIndexOutOfBoundsException if the
     * task is found to be non-existent.
     * @param index
     * @param inputList
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void markTask(int index, ArrayList<Task> inputList) throws ArrayIndexOutOfBoundsException {
        if (index == 0 || index > inputList.size()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            Task task = inputList.get(index - 1);
            System.out.println(task.markAsDone());
        }
    }


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> inputList = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = myObj.nextLine();
        String[] parts = input.split(" ");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(inputList);
            } else if (parts[0].equals("mark")) {
                try {
                    int index = Integer.parseInt(parts[1]);
                    markTask(index, inputList);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Sorry, there is no such Task!");
                }
            } else {
                System.out.println("added: " + input);
                Task inputTask = new Task(input);
                inputList.add(inputTask);
            }
            input = myObj.nextLine();
            parts = input.split(" ");
        }

        myObj.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
