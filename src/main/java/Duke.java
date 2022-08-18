import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskList;
    private static final String LINE = "____________________________________________________________";

    private static final String LOGO = "Hello from\n" +
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_LOGO = "Hello from\n" + LOGO;

    private static final String GREETING = "Hello! I'm Duke\n" +
            "What can I do for you?";

    private static final String GOODBYE_MESSAGE = "Bye, Hope to see you again soon!";

    //method to print in list format
    public static void printList(ArrayList<Task> taskArrayList) {
        int len = taskArrayList.size();
        if (len < 1) {
            throw new DukeException("List is empty, you have no tasks!");
        }
        int counter = 0;
        int numbering = 1;
        System.out.println(LINE);
        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            System.out.println(numbering + "." + temp);
            counter++; numbering++;
        }
        System.out.println(LINE);
    }

    //method to mark as done
    public static void markAsDone(ArrayList<Task> taskArrayList, String removeTaskNumberString) {
        String numberToRemove = removeTaskNumberString.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToRemove) - 1;
        Task tsk = taskArrayList.get(numberToRemoveInt);
        tsk.markAsDone();
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + tsk);
        System.out.println(LINE);
    }

    public static void markAsUndone(ArrayList<Task> taskArrayList, String addTaskNumberString) {
        String numberToAddAgain = addTaskNumberString.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToAddAgain) - 1;
        Task tsk = taskArrayList.get(numberToRemoveInt);
        tsk.markAsUndone();
        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " + tsk);
        System.out.println(LINE);
    }

    public static void printAddition(Task task) {
        int tasksLeft = taskList.size();
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:\n " + " " + task.toString() +
                "\nNow you have " + tasksLeft + " tasks in the list.");
        System.out.println(LINE);

    }

    public static void addTaskToArray(String s, Task.TYPE type) {
        Task t;

        switch (type) {
            case DEADLINE:
                if (s.length() < 1) {
                    throw new DukeException( "☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String[] splitStringDL = s.split("/by");
                String taskStringDL = splitStringDL[0];
                String by = splitStringDL[1];
                t = new Deadline(taskStringDL,by);
                break;

            case TODO:
                if (s.length() < 1) {
                    throw new DukeException( "☹ OOPS!!! The description of a todo cannot be empty.");
                }
                t = new Todo(s);
                break;

            case EVENT:
                if (s.length() < 1) {
                    throw new DukeException( "☹ OOPS!!! The description of an event cannot be empty.");
                }
                String[] splitStringTD = s.split("/at");
                String taskStringTD = splitStringTD[0];
                String at = splitStringTD[1];
                t = new Event(taskStringTD, at);
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        taskList.add(t);
        printAddition(t);
    }

    public static void deleteTaskfromArray(ArrayList<Task> taskArrayList, String taskNumber) {
        String numberToRemove = taskNumber.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToRemove) - 1;
        Task t = taskArrayList.get(numberToRemoveInt);
        taskArrayList.remove(numberToRemoveInt);
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task:\n " + " " + t.toString() +
                "\nNow you have " + taskArrayList.size() + " tasks in the list.");
    }

    public static void end() {
        System.out.println(LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        //System.out.println(LOGO);
        System.out.println(GREETING);
        System.out.println(LINE);

        taskList = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                String str1 = sc.nextLine();
                String[] wordArray = str1.strip().split(" ", 2);
                String word1 = wordArray[0].toLowerCase();
                String str2 = "";

                if (wordArray.length >= 2) {
                    str2 = wordArray[1];
                }


                switch(word1) {

                    case "bye":
                        end();
                        running = false;
                        break;

                    case "list":
                        printList(taskList);
                        break;

                    case "mark":
                        markAsDone(taskList, str2);
                        break;

                    case "unmark":
                        markAsUndone(taskList, str2);
                        break;

                    case "delete":
                        deleteTaskfromArray(taskList, str2);
                        break;

                    case "deadline":
                        addTaskToArray(str2, Task.TYPE.DEADLINE);
                        break;

                    case "todo":
                        addTaskToArray(str2, Task.TYPE.TODO);
                        break;

                    case "event":
                        addTaskToArray(str2, Task.TYPE.EVENT);
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }

            } catch (InputMismatchException e) {
                System.out.println(LINE);
                System.out.println("Invalid input");
                System.out.println(LINE);

            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println(e);
                System.out.println(LINE);
            }
        }
    }
}
