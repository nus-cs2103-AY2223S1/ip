package chacha;
import java.util.Scanner;

import chacha.commands.Command;
import chacha.commands.ListCommand;
import chacha.tasks.Deadline;
import chacha.tasks.Event;
import chacha.tasks.Task;
import chacha.tasks.Todo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Chacha {
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.printWelcome());
        String s = ui.readInput();
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (!s.equals("bye")) {
            //parse 
            //run command
            //print ui
            if (s.equals("list")) {
                //run command
                Command command = new ListCommand();
                command.execute(taskList, ui);
            } else if (s.contains("unmark")) {
                
                String[] split = s.split("\\s+");
                Task task = taskList.get(Integer.valueOf(split[1]) - 1);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());

            } else if (s.contains("mark")) {
                String[] split = s.split("\\s+");
                Task task = taskList.get(Integer.valueOf(split[1]) - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + task.toString());

            } else if (s.contains("delete")) {
                String[] split = s.split("\\s+");
                Task task = taskList.get(Integer.valueOf(split[1]) - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(task.toString());
                taskList.remove(Integer.valueOf(split[1]) - 1); 
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                
            } else if (s.contains("deadline")) {
                try {
                    String date = s.substring(s.indexOf("/by ") + 4);
                    date.trim();
                    String description = s.substring(0,s.indexOf("/"));
                    description = description.substring(s.indexOf("deadline ") + 9);
                    description.trim();
                    Deadline deadline = new Deadline(description, date);
                    taskList.add(deadline);  
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline.toString()); 
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty."); 
                }
            } else if (s.contains("todo")) {
                try {
                    String description = s.substring(s.indexOf("todo ") + 5);
                    System.out.println("heree");
                    description.trim();
                    Todo todo = new Todo(description);
                    taskList.add(todo);  
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo.toString()); 
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");  
                }

            } else if (s.contains("event")) {
                try {
                    String range = s.substring(s.indexOf("/at ") + 4);
                    range.trim();
                    String description = s.substring(0,s.indexOf("/"));
                    description = description.substring(s.indexOf("event ") + 6);
                    description.trim();
                    Event event = new Event(description, range);
                    taskList.add(event);  
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event.toString()); 
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } catch(Exception e) {
                    System.out.println("OOPS!!! The description of a event cannot be empty."); 
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } 
            s = ui.readInput();   
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void loadData(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            //load task objects into array
            System.out.println(s.nextLine());
        }
    }
}
