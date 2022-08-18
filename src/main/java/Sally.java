import java.util.ArrayList;
import java.util.Scanner;

public class Sally {
    private static ArrayList<Task> list = new ArrayList<>();
    private static Scanner sc;

    public static void main(String[] args) {
        border();
        System.out.println("Hello! I'm Sally");
        System.out.println("What can I do for you?");
        border();
        sc = new Scanner(System.in);
        messaging();
    }

    public static void messaging() {
        String message = sc.nextLine();

        if (message.equals("bye")) {
            border();
            System.out.println("Until next time!");
            border();
            return;
        }

        try {
            if (message.equals("list")) {
                border();
                if (list.size() == 0) {
                    System.out.println("You don't have any list right now");
                } else {
                    System.out.println("Here's your current list:");
                    System.out.println(printList());
                }
                border();
            } else if (message.contains("unmark")) {
                int taskNum = Integer.parseInt(message.substring(7)) - 1; // -1 so that index is constant
                if (taskNum >= 0 && taskNum < list.size()) {
                    Task task = list.get(taskNum);
                    String description = task.toString();
                    border();
                    if (task.isDone) {
                        task.markAsUndone();
                        String unmarkTask = task.toString();
                        System.out.println("Got it, I've unmarked this task for you!\n" + unmarkTask);
                    } else {
                        System.out.printf("You have not marked: \n  " + description + "\n");
                    }
                    border();
                } else {
                    throw new SallyException.SallyTaskNotFoundException();
                }
            } else if (!message.contains("unmark") && message.contains("mark")) {
                int taskNum = Integer.parseInt(message.substring(5)) - 1; // -1 so that index is constant
                if (taskNum >= 0 && taskNum < list.size()) {
                    Task task = list.get(taskNum);
                    String description = task.toString();
                    border();
                    if (!task.isDone) {
                        task.markAsDone();
                        String markTask = task.toString();
                        System.out.println("Got it, I've marked this task for you!\n" + markTask);
                    } else {
                        System.out.printf("You have previously done: \n    " + description + "\n");
                    }
                    border();
                } else {
                    border();
                    throw new SallyException.SallyTaskNotFoundException();
                }
            } else {
                //ToDos
                if (message.startsWith("todo")) {
                    if (message.length() > 4) {
                        String description = message.substring(5);
                        list.add(new ToDo(description));
                        //Message printed out
                        String newTask = list.get(list.size() - 1).toString();
                        int totalTasks = list.size();
                        border();
                        System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
                        if (totalTasks == 1) {
                            System.out.println("Now you have 1 task in the list.\n");
                        } else {
                            System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
                        }
                        border();
                    } else {
                        throw new SallyException.SallyNoDescriptionException();
                    }
                }
                //Deadlines
                else if (message.startsWith("deadline")) {
                    String description, by;
                    if (message.length() > 8) {
                        if (message.contains("/by")) {
                            if (message.length() > 12) {
                                description = message.substring(9, message.indexOf("/by") - 1);
                                by = message.substring(message.indexOf("/by") + 4);
                                list.add(new Deadline(description, by));
                                //Message printed out
                                String newTask = list.get(list.size() - 1).toString();
                                int totalTasks = list.size();
                                border();
                                System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
                                if (totalTasks == 1) {
                                    System.out.println("Now you have 1 task in the list.\n");
                                } else {
                                    System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
                                }
                                border();
                            } else {
                                // No /by
                                throw new SallyException.SallyNoDeadlineException();
                            }
                        } else {
                            throw new SallyException.SallyInvalidInputException();
                        }
                    }
                }
                //Events
                else if (message.startsWith("event")) {
                    String description, at;
                    if (message.length() <= 5) {
                        throw new SallyException.SallyInvalidInputException();
                    } else if (message.contains("/at ")) {
                        description = message.substring(6, message.indexOf("/at") - 1);
                        at = message.substring(message.indexOf("/at") + 4);
                        list.add(new Event(description, at));
                        //Message printed out
                        String newTask = list.get(list.size() - 1).toString();
                        int totalTasks = list.size();
                        border();
                        System.out.println("Got it. I've added this task:\n    " + newTask + "\nto your to-do list!");
                        if (totalTasks == 1) {
                            System.out.println("Now you have 1 task in the list.\n");
                        } else {
                            System.out.println("Now you have " + totalTasks + " tasks in the list.\n");
                        }
                        border();
                    } else {
                        throw new SallyException.SallyNoPlaceException();
                    }
                }
                //Any other messages
                else {
                    throw new SallyException.SallyInvalidInputException();
                }
            }
        } catch (SallyException e) {
            System.out.println(e);
        } finally {
            messaging();
        }
    }

    private static String printList() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            int number = i + 1;
            output = output + number + ". " + list.get(i).toString() + "\n";
        }
        return output;
    }

    public static void border () {
        System.out.println("-------------------------------------------------------------------------------------");
    }
}