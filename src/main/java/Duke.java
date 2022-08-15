import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    //method to print in list format
    public static void print(ArrayList<Task> taskArrayList) {
        int counter = 0;
        int numbering = 1;
        int len = taskArrayList.size();
        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            System.out.println(numbering + ". " + "[" + temp.getStatusIcon() + "]" + temp);
            counter++; numbering++;
        }
    }

    //method to mark as done
    public static void markAsDone(ArrayList<Task> taskArrayList, String removeTaskNumberString) {
        String numberToRemove = removeTaskNumberString.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToRemove) - 1;
        Task tsk = taskArrayList.get(numberToRemoveInt);
        tsk.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                "[" + tsk.getStatusIcon() + "]" + tsk.toString());
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");

        ArrayList<Task> taskList = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String str1 = sc.nextLine();

                if (str1.equals("bye")) {
                    System.out.println("Bye, Hope to see you again soon!");
                    break;

                } else if (str1.equals("list")) {
                    print(taskList);

                } else if (str1.contains("mark")) {
                    markAsDone(taskList, str1);

                } else {
                    Task t = new Task(str1);
                    taskList.add(t);
                    System.out.println("added: " + str1);
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input");

            } catch (IndexOutOfBoundsException e1) {
                System.out.println("List cannot be empty");
            }
        }


//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
