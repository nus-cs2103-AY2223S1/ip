import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> listOfThings = new ArrayList<Task>();

    public static void main(String[] args) {

        // Special Commands
        String BYE = "bye";
        String LIST = "list";
        String MARK = "mark";
        String UNMARK = "unmark";
        String TODO = "todo";
        String DEADLINE = "deadline";
        String EVENT = "event";

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while(true) {
            String userInput = sc.nextLine();
            try {
                String[] userInputSpilt = userInput.split(" ", 2);
                if (userInputSpilt[0].equals(BYE)) {
                    break;
                } else if (userInputSpilt[0].equals(LIST)) {
                    ListOut();
                } else if (userInputSpilt[0].equals(MARK)) {
                    int taskNum = Integer.valueOf(userInputSpilt[1]) - 1;
                    MarkTask(taskNum);
                } else if (userInputSpilt[0].equals(UNMARK)) {
                    int taskNum = Integer.valueOf(userInputSpilt[1]) - 1;
                    UnmarkTask(taskNum);
                } else if (userInputSpilt[0].equals(TODO)) {
                    if (userInputSpilt.length == 1) {
                        throw new MissingDescriptionException("TODO");
                    } else {
                        ToDo currToDo = new ToDo(userInputSpilt[1]);
                        AddToList(currToDo);
                    }
                } else if (userInputSpilt[0].equals(DEADLINE)) {
                    if (userInputSpilt.length == 1) {
                        throw new MissingDescriptionException("DEADLINE");
                    } else {
                        String[] deadlineSpilt = userInputSpilt[1].split("/by", 2);
                        if (deadlineSpilt.length == 1) {
                            throw new MissingDeadlineDescriptionException();
                        } else {
                            Deadline currDeadline =  new Deadline(deadlineSpilt[0], deadlineSpilt[1]);
                            AddToList(currDeadline);
                        }
                    }

                } else if (userInputSpilt[0].equals(EVENT)) {
                    if (userInputSpilt.length == 1) {
                        throw new MissingDescriptionException("EVENT");
                    } else {
                        String[] eventSpilt = userInputSpilt[1].split("/at", 2);
                        if (eventSpilt.length == 1) {
                            throw new MissingEventDescriptionException();
                        } else {
                            Event currEvent =  new Event(eventSpilt[0], eventSpilt[1]);
                            AddToList(currEvent);
                        }

                    }

                } else {
                    throw new InvalidInputException();
                }
            }
            catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }

            catch (MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }

            catch (MissingDeadlineDescriptionException e) {
                System.out.println(e.getMessage());
            }

            catch (MissingEventDescriptionException e) {
                System.out.println(e.getMessage());
            }

        }

        Bye();
    }

    public static void AddToList(Task task) {
        listOfThings.add(task);
        System.out.println("--------------------------------");
        System.out.println("Got it. I've added this task:\n " + task.TaskInfo());
        if (listOfThings.size() == 1) {
            System.out.println("Now you have " + listOfThings.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + listOfThings.size() + " tasks in the list.");
        }
        System.out.println("--------------------------------");
    }

    public static void ListOut() {
        int size = listOfThings.size();
        System.out.println("--------------------------------");
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + listOfThings.get(i).TaskInfo());
        }
        System.out.println("--------------------------------");
    }

    public static void MarkTask(int taskNum) {
        try {
            Task currentTask = listOfThings.get(taskNum);
            currentTask.markAsDone();
            System.out.println("--------------------------------");
            System.out.println("Nice! I've marked this task as done:\n " + currentTask.TaskInfo());
            System.out.println("--------------------------------");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The task you are trying to mark does not exist");
        }

    }

    public static void UnmarkTask(int taskNum) {
        try {
            Task currentTask = listOfThings.get(taskNum);
            currentTask.markAsNotDone();
            System.out.println("--------------------------------");
            System.out.println("Ok, I've marked this task as not done yet:\n " + currentTask.TaskInfo());
            System.out.println("--------------------------------");
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("OOPS!!! The task you are trying to mark as not done does not exist");
        }


    }

    public static void Bye() {
        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
    }
}
