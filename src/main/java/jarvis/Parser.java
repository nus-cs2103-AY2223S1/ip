package jarvis;

import jarvis.task.*;
import jarvis.exception.JarvisException;

import java.util.Scanner;

public class Parser {
    private String userInput;
    private Scanner inputScanner = new Scanner(System.in);
    private static String introduction = "Hello. I am Jarvis \n"
            + "What can I do for you today?";
    private static String farewell = "Goodbye, have a good day.";
    private TaskList tasks;
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void readCommand() throws JarvisException {
        while (true) {
            //receive user input
            userInput = inputScanner.nextLine();
            //check if userinput is bye, break if true
            if (userInput.equals("bye")) {
                break;
            }
            //if userinput equals list, return task list
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.getList().size(); i++) {
                    if (tasks.getList().get(i) == null) {
                        break;
                    }
                    System.out.println((i + 1) + ". " + tasks.getList().get(i).toString());
                }
                continue;
            }
            // if userInput equals find, find tasks which match given string
            if (userInput.length() > 4 && userInput.substring(0,4).equals("find")) {
                String keyword = userInput.substring((5));
                System.out.println("Here are the matching tasks in your list:");
                for (int i = 0; i < tasks.getList().size(); i++) {
                    Task currTask = tasks.getList().get(i);
                    if (currTask == null) {
                        break;
                    }
                    if (currTask.toString().contains(keyword)) {
                        System.out.println((i + 1) + ". " + currTask.toString());
                    }
                }
                continue;
            }

            //if userinput equals mark, check which task and mark it
            if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
                int toMark = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.getList().get(toMark).mark();
                String markResponse = "Great. I have marked this task as done:\n ";
                System.out.println(markResponse + tasks.getList().get(toMark).toString());
                continue;
            }
            //if userinput equals unmark, check which task and unmark
            if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
                int toMark = Integer.parseInt(userInput.substring(7)) - 1;
                tasks.getList().get(toMark).unmark();
                String markResponse = "Ok, I have marked this task as not done yet:\n ";
                System.out.println(markResponse + tasks.getList().get(toMark).toString());
                continue;
            }
            //if userinput equals delete, check which task and delete
            if (userInput.length() > 6 && userInput.substring(0, 6).equals("delete")) {
                int toDelete = Integer.parseInt(userInput.substring(7)) - 1;
                Task deleteTask = tasks.getList().get(toDelete);
                tasks.getList().remove(toDelete);
                String deleteResponse = "Noted. I have removed this task:\n ";
                System.out.println(deleteResponse + deleteTask.toString());
                continue;
            }

            //if userinput equals to do add new to do task to list
            if (userInput.length() > 3 && userInput.substring(0, 4).equals("todo")) {
                String description = userInput.substring(4);
                if (description.equals("")) {
                    throw new JarvisException("The description of a todo cannot be empty");
                }
                tasks.getList().add(new ToDo(description));
                System.out.println("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                continue;
            }

            //if userinput equals deadline add new deadline task to list
            if (userInput.length() > 8 && userInput.substring(0, 8).equals("deadline")) {
                int divisor = userInput.indexOf("/by");
                String description = userInput.substring(9, divisor - 1);
                String date = userInput.substring(divisor + 4);
                tasks.getList().add(new Deadline(description, date));
                System.out.println("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                continue;
            }
            //if userinput equals event add new event task to list
            if (userInput.length() > 7 && userInput.substring(0, 5).equals("event")) {
                int divisor = userInput.indexOf("/at");
                String description = userInput.substring(6, divisor - 1);
                String date = userInput.substring(divisor + 4);
                tasks.getList().add(new Event(description, date));
                System.out.println("Got it. I've added this task:\n " + tasks.getList().get(Task.count - 1)
                        + "\nNow you have " + (Task.count) + " tasks in the list.");
                continue;
            }
            throw new JarvisException("I'm sorry, but I don't know what that means");
        }
    }

    public void introduction() {
        System.out.println(introduction);
    }

    public void farewell() {
        System.out.print(farewell);
    }
}

