import java.util.ArrayList;
import java.util.Scanner;
import dukeexceptions.*;
import tasks.*;

public class Duke {
    public static void main(String[] args) {
        Statements.entryStatement();
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        Statements.initStatement();
        while (true) {
            String userIn = myObj.nextLine();  // Read user input
            try {
                if (userIn.equals("bye")) {
                    Statements.byeStatement();
                    break;
                } else if (userIn.equals("list")) {
                    if (taskList.size() == 0) {
                        System.out.println("You have no tasks at the moment!");
                    }
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i - 1).toString());
                    }
                } else if (userIn.contains("unmark")) {
                    String[] inArr = userIn.split(" ", 2);
                    int ind = Integer.parseInt(inArr[1]) - 1;
                    taskList.get(ind).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + "  " + taskList.get(ind).toString());

                } else if (userIn.contains("mark")) {
                    String[] inArr = userIn.split(" ", 2);
                    int ind = Integer.parseInt(inArr[1]) - 1;
                    taskList.get(ind).mark();
                    System.out.println("Nice! I've marked this task as done:\n" + "  " + taskList.get(ind).toString());
                } else if (userIn.contains("todo")) {
                    try {
                        String[] des = userIn.split(" ", 2);
                        Task toAdd = new Todos(des[1]);
                        taskList.add(toAdd);
                        Statements.addStatement(toAdd.toString(), taskList.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new NoDescriptionException("todo");
                    }
                } else if (userIn.contains("deadline")) {
                    try {
                        String[] overall = userIn.split(" ", 2);
                        String[] descriptdate = overall[1].split("/by", 2);
                        Task toAdd = new Deadlines(descriptdate[0], descriptdate[1]);
                        taskList.add(toAdd);
                        Statements.addStatement(toAdd.toString(), taskList.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new NoDescriptionException("deadline");
                    }
                } else if (userIn.contains("event")) {
                    try {
                        String[] overall = userIn.split(" ", 2);
                        String[] descriptdate = overall[1].split("/at", 2);
                        Task toAdd = new Events(descriptdate[0], descriptdate[1]);
                        taskList.add(toAdd);
                        Statements.addStatement(toAdd.toString(), taskList.size());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new NoDescriptionException("event");
                    }
                } else if (userIn.contains("delete")) {
                    try {
                        String[] inArr = userIn.split(" ", 2);
                        int ind = Integer.parseInt(inArr[1]) - 1;
                        String temp = taskList.get(ind).toString();
                        taskList.remove(ind);
                        System.out.println("Okay! The task: \n" + temp + "\nhas been deleted forever.\n" +
                                "You have " + taskList.size() + " task" + ((taskList.size()!=1)?"s ":" ") + "left!");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new NoDescriptionException("delete");
                    }
                }
                else {
                    throw new NoSuchCommandException();
                }
            } catch (NoDescriptionException e) {
                System.err.print(e);
            } catch (NoSuchCommandException e) {
                System.err.print(e);
            }

        }

    }





}
