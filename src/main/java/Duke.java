import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String logo  = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static ArrayList<Task> list;

    public static void main(String[] args) throws DukeException {
        list = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you today, Master?");
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine().toLowerCase();
        boolean open = true;
        while (open) {
            try {
                // Bye
                if (text.equals("bye") || text.equals("exit")) {
                    scanner.close();
                    open = false;
                    System.out.println("Goodbye, Master! Thank you for visiting\n" + logo);
                    // List
                } else if (text.equalsIgnoreCase("list")) {
                    if (list.isEmpty()) {
                        throw new DukeException("There is nothing in your list yet!");
                    } else {
                        System.out.println("Here is your to-do list, Master:");
                        for (int i = 1; i <= list.size(); i++) {
                            System.out.println(i + ". " + list.get(i - 1).toString());
                        }
                    }
                    text = scanner.nextLine().toLowerCase();

                    // Mark
                } else if (text.startsWith("mark")) {
                    if (text.length() <= 5) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else {
                        String taskNumber = String.valueOf(text.charAt(5));
                        if (text.length() == 7) {
                            taskNumber += String.valueOf(text.charAt(6));
                        }
                        int number = Integer.parseInt(taskNumber) - 1;
                        if (number >= list.size() || number < 0) {
                            throw new DukeException("There is no task " + taskNumber + " just yet, Master.");
                        } else {
                            Task curr = list.get(number);
                            if (curr.isDone) {
                                throw new DukeException("This task was already marked done, Master.");
                            } else {
                                curr.markDone();
                                System.out.println("Well done! I have marked "
                                        + curr.toString() +
                                        " as done, Master.");
                            }
                        }
                    }
                    text = scanner.nextLine().toLowerCase();
                } else if (text.startsWith("unmark")) {
                    if (text.length() <= 7) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else {
                        String taskNumber = String.valueOf(text.charAt(7));
                        if (text.length() == 9) {
                            taskNumber += String.valueOf(text.charAt(8));
                        }
                        int number = Integer.parseInt(taskNumber) - 1;
                        if (number >= list.size() || number < 0) {
                            throw new DukeException("There is no task " + taskNumber + " just yet, Master.");
                        } else {
                            Task curr = list.get(number);
                            if (!curr.isDone) {
                                throw new DukeException("This task was already marked undone, Master.");
                            } else {
                                curr.markUndone();
                                System.out.println("Oh no :( I have marked " +
                                        curr.toString()
                                        + " as undone, Master.");
                            }
                        }
                    }
                    text = scanner.nextLine().toLowerCase();
                } else if (text.startsWith("delete")) {
                    if (text.length() <= 7) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else {
                        String taskNumber = String.valueOf(text.charAt(7));
                        if (text.length() == 9) {
                            taskNumber += String.valueOf(text.charAt(8));
                        }
                        int number = Integer.parseInt(taskNumber) - 1;
                        if (number >= list.size() || number < 0) {
                            throw new DukeException("There is no task " + taskNumber + " just yet, Master.");
                        } else {
                            Task tmp = list.get(number);
                            list.remove(number);
                            System.out.println("Very well. I have deleted " + tmp + " from the list, Master.");
                        }
                        text = scanner.nextLine().toLowerCase();
                    }
                } else if (text.startsWith("deadline")) {
                    if (text.length() <= 9) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else if (text.contains("/by")) {
                        int splitNum = text.indexOf("/");
                        String taskName = text.substring(9, splitNum - 1);
                        String time = text.substring(splitNum + 4, text.length());
                        Deadline newTask = new Deadline(taskName, time);
                        list.add(newTask);
                        System.out.println("I have added " + newTask.toString() + " to the list, Master.");
                    } else {
                        throw new DukeException("I need to know the deadline to add this task to the list, Master.");
                    }
                    text = scanner.nextLine().toLowerCase();
                } else if (text.startsWith("event")) {
                    if (text.length() <= 6) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else if (text.contains("/on") || text.contains("/at")) {
                        int splitNum = text.indexOf("/");
                        String taskName = text.substring(6, splitNum - 1);
                        String time = text.substring(splitNum + 4, text.length());
                        Event newTask = new Event(taskName, time);
                        list.add(newTask);
                        System.out.println("I have added " + newTask.toString() + " to the list, Master.");
                    } else {
                        throw new DukeException("I need to know the time to add this task to the list, Master.");
                    }
                    text = scanner.nextLine().toLowerCase();
                } else if (text.startsWith("todo")) {
                    if (text.length() <= 5) {
                        throw new DukeException("You'll have to provide more information than that, Master.");
                    } else {
                        String taskName = text.substring(5, text.length());
                        ToDo newTask = new ToDo(taskName);
                        list.add(newTask);
                        System.out.println("I have added " + newTask.toString() + " to the list, Master.");
                    }
                    text = scanner.nextLine().toLowerCase();
                } else {
                    throw new DukeException("I beg your pardon?");
                }
            } catch (DukeException e){
                System.out.println(e);
                text = scanner.nextLine().toLowerCase();
            }
        }
    }
}
