import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<Task> botArray = new ArrayList<Task>();

    public static void main(String[] args) {
        Greet();
        Scanner userInput = new Scanner(System.in);
        String userReply = userInput.nextLine().strip();
        while(!userReply.toLowerCase().equals("bye")) {
            if (userReply.equals("")) {
                System.out.println("##############################################");
                System.out.println("Please enter some valid text");
                System.out.println("##############################################");
            } else if (userReply.toLowerCase().equals("list")) {
                List();
            } else if (userReply.toLowerCase().startsWith("mark")) {
                try {
                    String[] markedTask = userReply.split(" ",2);
                    if (markedTask.length < 2) {
                        throw new DukeException("Please input a number after mark");
                    }
                    int number = Integer.parseInt(markedTask[1]);
                    if (number > botArray.size() || number <= 0) {
                        throw new DukeException("Sorry! Please choose a valid item number in the list");
                    }
                    Mark(number);
                } catch (NumberFormatException e) {
                    System.out.println("##############################################");
                    System.out.println("Please enter a valid number");
                    System.out.println("##############################################");
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if(userReply.toLowerCase().startsWith("unmark")) {
                try {
                    String[] unMarkedTask = userReply.split(" ",2);
                    if (unMarkedTask.length < 2) {
                        throw new DukeException("Please input a number after unmark");
                    }
                    int number = Integer.parseInt(unMarkedTask[1]);
                    if (number > botArray.size() || number <= 0) {
                        throw new DukeException("Sorry! Please choose a valid item number in the list");
                    }
                    UnMark(number);
                } catch (NumberFormatException e) {
                    System.out.println("##############################################");
                    System.out.println("Please enter a valid number");
                    System.out.println("##############################################");
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userReply.toLowerCase().startsWith("todo")) {
                try {
                    String task = userReply.split(" ", 2)[1];
                    ToDo(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("##############################################");
                    System.out.println("Sorry! Please include a description after todo");
                    System.out.println("##############################################");
                }
            } else if (userReply.toLowerCase().startsWith("deadline")) {
                try {
                    String[] taskWithDescription = userReply.split(" ",2);
                    if (taskWithDescription.length < 2) {
                        throw new DukeException("Sorry! Please include a " +
                                "valid description after deadline");
                    }
                    String[] taskWithDeadline = taskWithDescription[1]
                            .split(" /by ",2);
                    if (taskWithDeadline.length < 2) {
                        throw new DukeException("Sorry! Please include a valid " +
                                "deadline after the description");
                    }
                    String task = taskWithDeadline[0];
                    String by = taskWithDeadline[1];
                    Deadline(task, by);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userReply.toLowerCase().startsWith("event")) {
                try {
                    String[] taskWithDescription = userReply.split(" ",2);
                    if (taskWithDescription.length < 2) {
                        throw new DukeException("Sorry! Please include a " +
                                "valid description after event");
                    }
                    String[] taskWithPeriod = taskWithDescription[1]
                            .split(" /at ",2);
                    if (taskWithPeriod.length < 2) {
                        throw new DukeException("Sorry! Please include a valid " +
                                "time period after the description");
                    }
                    String task = taskWithPeriod[0];
                    String period = taskWithPeriod[1];
                    Event(task, period);
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            } else if (userReply.toLowerCase().startsWith("delete")) {
              try {
                  String[] taskDeleted = userReply.split(" ", 2);
                  if (taskDeleted.length < 2) {
                      throw new DukeException("Sorry! Please include a number after delete");
                  }
                  int number = Integer.parseInt(taskDeleted[1]);
                  if (number > botArray.size() || number <= 0) {
                      throw new DukeException("Sorry! Please select a valid item number in the list");
                  }
                  Delete(number);
              } catch (DukeException e) {
                  System.out.println(e.toString());
              } catch (NumberFormatException e) {
                  System.out.println("##############################################");
                  System.out.println("Please enter a valid number");
                  System.out.println("##############################################");
              }
            } else {
                System.out.println("##############################################");
                System.out.println("Sorry! Please key in a valid command");
                System.out.println("##############################################");
            }
            userReply = userInput.nextLine().strip();
        }
        GoodBye();
    }

    private static void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        System.out.println("##############################################");
    }

    public static void List() {
        System.out.println("##############################################");
        if (botArray.size() == 0) {
            System.out.println("\t\t\t" + "No items are in the list");
        }
        for (int i = 0; i < botArray.size(); i++) {
            int pos = i + 1;
            String itemDisplayed = String.format("\t\t\t%d. %s",pos, botArray.get(i));
            System.out.println(itemDisplayed);
        }
        System.out.println("##############################################");
    }

    public static void Mark(int num) {
        System.out.println("##############################################");
        System.out.println("Congratulations! This task has been marked as done!");
        Task currentTask = botArray.get(num - 1);
        currentTask.setCompleted(true);
        System.out.println("\t\t\t" + currentTask);
        System.out.println("##############################################");
    }

    public static void UnMark(int num) {
        System.out.println("##############################################");
        System.out.println("Congratulations! This task has been marked as done!");
        Task currentTask = botArray.get(num - 1);
        currentTask.setCompleted(false);
        System.out.println("\t\t\t" + currentTask);
        System.out.println("##############################################");
    }

    public static void ToDo(String task) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        ToDo toDo = new ToDo(task);
        System.out.println("\t\t\t" + toDo.toString());
        botArray.add(toDo);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    public static void Deadline(String task, String by) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        Deadline deadline = new Deadline(task, by);
        System.out.println("\t\t\t" + deadline.toString());
        botArray.add(deadline);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    public static void Event(String task, String at) {
        System.out.println("##############################################");
        System.out.println("Nice! This task has been successfully added!");
        Event event = new Event(task, at);
        System.out.println("\t\t\t" + event.toString());
        botArray.add(event);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    private static void Delete(int num) {
        System.out.println("##############################################");
        System.out.println("Noted! This task has been successfully removed!");
        System.out.println("\t\t\t" + botArray.get(num - 1));
        botArray.remove(num - 1);
        String numOfTasks = String.format("You currently have %d tasks in the list",botArray.size());
        System.out.println(numOfTasks);
        System.out.println("##############################################");
    }

    private static void GoodBye() {
        System.out.println("##############################################");
        System.out.println("So Long, farewell!");
        System.out.println("##############################################");
    }
}
