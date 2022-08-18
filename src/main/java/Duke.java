import java.util.ArrayList;
import java.util.Scanner;

/*
Duke IP for CS2103T by Yuvaraj Kumaresan AY2022/23
 */
public class Duke {

    public static ArrayList<Task> storage = new ArrayList<Task>();

    /*
    Method list
    Description: Asks for user input using the scanner utility,
                 Adds input to memory if input is todo <description>, deadline <description> /by <time> or event <description> /at <time>,
                 if input is bye exit message is displayed and program exits,
                 if input is list program lists out the stored inputs,
                 if input is mark <number> program marks the task as complete and displays it,
                 if input is unmark <number> program marks the task as incomplete and displays it.
                 if input is delete <number> program deletes the task from the list.
     */

    public static void list() {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String text = input.nextLine();

            if (text.equalsIgnoreCase("list")) {
                if (storage.size() == 0) {
                    System.out.println("The list is empty, please add more items to display them here.");
                } else {
                    System.out.println("Here are the tasks in your list : ");
                    for (int i = 0; i < storage.size(); i++) {
                        System.out.println((i + 1) + ". " + storage.get(i).toString());
                    }
                }

            } else if (text.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");

            }  else if (text.startsWith("todo")) {
                if (text.equalsIgnoreCase("todo") || text.equalsIgnoreCase("todo ")) {
                    System.out.println("Please provide a description for your todo task");
                } else {
                    ToDo item = new ToDo(text.replace("todo ", ""));
                    storage.add(item);
                    System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                }

            } else if (text.startsWith("deadline")) {
                try {
                    String[] description = text.replace("deadline ", "").split("/by ");
                    Deadline item = new Deadline(description[0], description[1]);
                    storage.add(item);
                    System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                } catch (ArrayIndexOutOfBoundsException error) {
                    System.out.println("Please provide a deadline and a by time e.g. deadline <description of the deadline> /by <time of the deadline>");
                }

            } else if (text.startsWith("event")) {
                try {
                    String[] description = text.replace("event ", "").split("/at ");
                    Event item = new Event(description[0], description[1]);
                    storage.add(item);
                    System.out.println("Got it. I've added this task. \n" + item.toString() + "\nNow you have " + storage.size() + " tasks in the list");
                } catch (ArrayIndexOutOfBoundsException error) {
                    System.out.println("Please provide a event and an at time e.g. event <description of the event> /at <time of the event>");
                }

            } else if (text.startsWith("mark")) {
                try {
                    if (text.equalsIgnoreCase("mark") || text.equalsIgnoreCase("mark ")) {
                        System.out.println("Please provide a task number to mark as done");
                    } else {
                        if (Integer.parseInt(text.replace("mark ", "")) - 1 < storage.size() && Integer.parseInt(text.replace("mark ", "")) > 0) {
                            if (!storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).getIsDone()) {
                                storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).setIsDone(true);
                                System.out.println("Nice! I've marked this task as done \n" +
                                        storage.get(Integer.parseInt(text.replace("mark ", "")) - 1).toString() +
                                        "\nNow you have " + storage.size() + " tasks in the list");
                            } else {
                                System.out.println("This task is already marked done");
                            }
                        } else {
                            System.out.println("Such an item does not exist");
                        }
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Invalid command please add a space between mark and the list item you would like to interact with. \n" +
                            "additionally ensure you have entered a number after mark.");
                }

            } else if (text.startsWith("unmark")) {
                try {
                    if (text.equalsIgnoreCase("unmark") || text.equalsIgnoreCase("unmark ")) {
                        System.out.println("Please provide a task number to mark as not done");
                    } else {
                        if (Integer.parseInt(text.replace("unmark ", "")) - 1 < storage.size() && Integer.parseInt(text.replace("unmark ", "")) > 0) {
                            if (storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).getIsDone()) {
                                storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).setIsDone(false);
                                System.out.println("Ok, I've marked this task as not done yet \n" +
                                        storage.get(Integer.parseInt(text.replace("unmark ", "")) - 1).toString() +
                                        "\nNow you have " + storage.size() + " tasks in the list");
                            } else {
                                System.out.println("This task has already been marked not done");
                            }
                        } else {
                            System.out.println("Such an item does not exist");
                        }
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Invalid command please add a space between unmark and the list item you would like to interact with. \n" +
                            "additionally ensure you have entered a number after unmark.");
                }

            } else if (text.startsWith("help")) {
                System.out.println("Welcome to the user guide. This guide has all the commands that are necessary to operate Duke\n\n" +
                        "Main commands : \n\n" +
                        "Todo : adds a todo task to the task list :-> todo <description of the task> \n" +
                        "Deadline : adds a deadline task to the task list :-> deadline <description of the deadline> /by <time of the deadline> \n" +
                        "Event : adds an event task to the task list :-> event <description of the event> /at <time of the event> \n\n" +
                        "Other commands: \n\n" +
                        "Mark : marks a task as done :-> mark <task number> \n" +
                        "Unmark : marks a task as not done :-> unmark <task number> \n" +
                        "Delete : deletes a task :-> delete <task number>\n" +
                        "Help : brings up this display :-> help\n" +
                        "Bye : closes Duke :-> bye \n\n" +
                        "Please enter one of the above to continue.");

            } else {
                System.out.println("Please provide a proper command. Formats are as follows: \n\n" +
                        "Main commands : \n\n" +
                        "Todo : adds a todo task to the task list :-> todo <description of the task> \n" +
                        "Deadline : adds a deadline task to the task list :-> deadline <description of the deadline> /by <time of the deadline> \n" +
                        "Event : adds an event task to the task list :-> event <description of the event> /at <time of the event> \n\n" +
                        "Other commands: \n\n" +
                        "Mark : marks a task as done :-> mark <task number> \n" +
                        "Unmark : marks a task as not done :-> unmark <task number> \n" +
                        "Delete : deletes a task :-> delete <task number>\n" +
                        "Help : brings up this display :-> help\n" +
                        "Bye : closes Duke :-> bye\n\n" +
                        "Please enter one of the above to continue.");
            }
        }


    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I am \n" + logo + "\nType help to see user guide.\n\nWhat can I do for you?");
        list();
    }
}

