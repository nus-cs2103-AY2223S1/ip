import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;

public class Jarvis {
    public static void main(String[] args) throws JarvisException {
        Scanner inputScanner = new Scanner(System.in);

        String userInput;
        String introduction = "Hello. I am Jarvis \n"
                + "What can I do for you today?";
        String farewell = "Goodbye, have a good day.";

        List<Task> taskList = new ArrayList<>();

        try {
            IOhandler.readFile(taskList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(introduction);
        try {
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
                    for (int i = 0; i < taskList.size(); i++) {
                        if (taskList.get(i) == null) {
                            break;
                        }
                        System.out.println((i + 1) + ". " + taskList.get(i).toString());
                    }
                    continue;
                }
                //if userinput equals mark, check which task and mark it
                if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
                    int toMark = Integer.parseInt(userInput.substring(5)) - 1;
                    taskList.get(toMark).mark();
                    String markResponse = "Great. I have marked this task as done:\n ";
                    System.out.println(markResponse + taskList.get(toMark).toString());
                    continue;
                }
                //if userinput equals unmark, check which task and unmark
                if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
                    int toMark = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.get(toMark).unmark();
                    String markResponse = "Ok, I have marked this task as not done yet:\n ";
                    System.out.println(markResponse + taskList.get(toMark).toString());
                    continue;
                }
                //if userinput equals delete, check which task and delete
                if (userInput.length() > 6 && userInput.substring(0, 6).equals("delete")) {
                    int toDelete = Integer.parseInt(userInput.substring(7)) - 1;
                    Task deleteTask = taskList.get(toDelete);
                    taskList.remove(toDelete);
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
                    taskList.add(new ToDo(description));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                    continue;
                }

                //if userinput equals deadline add new deadline task to list
                if (userInput.length() > 8 && userInput.substring(0, 8).equals("deadline")) {
                    int divisor = userInput.indexOf("/by");
                    String description = userInput.substring(9, divisor - 1);
                    String date = userInput.substring(divisor + 4);
                    taskList.add(new Deadline(description, date));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                    continue;
                }
                //if userinput equals event add new event task to list
                if (userInput.length() > 7 && userInput.substring(0, 5).equals("event")) {
                    int divisor = userInput.indexOf("/at");
                    String description = userInput.substring(6, divisor - 1);
                    String date = userInput.substring(divisor + 4);
                    taskList.add(new Event(description, date));
                    System.out.println("Got it. I've added this task:\n " + taskList.get(Task.count - 1)
                            + "\nNow you have " + (Task.count) + " tasks in the list.");
                    continue;
                }
                throw new JarvisException("I'm sorry, but I don't know what that means");
            }
            try {
                File dir = new File("data");
                File myFile = new File(dir, "taskList.txt");
                myFile.createNewFile();
                FileWriter myWriter = new FileWriter(myFile);
                for (int i = 0; i < taskList.size(); i++ ) {
                    Task curr = taskList.get(i);
                    if (curr instanceof Deadline) {
                        if (curr.isDone) {
                            myWriter.write("D" + " | 1 | " + curr.description + " | " + ((Deadline) curr).by + "\n");
                        } else {
                            myWriter.write("D" + " | 0 | " + curr.description + " | " + ((Deadline) curr).by + "\n");
                        }
                    } else if (curr instanceof Event) {
                        if (curr.isDone) {
                            myWriter.write("E" + " | 1 | " + curr.description + " | " + ((Event) curr).at + "\n");
                        } else {
                            myWriter.write("E" + " | 0 | " + curr.description + " | " + ((Event) curr).at + "\n");
                        }
                    } else {
                        if (curr.isDone) {
                            myWriter.write("T" + " | 1 | " + curr.description + "\n");
                        } else {
                            myWriter.write("T" + " | 0 | " + curr.description + "\n");
                        }
                    }
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            System.out.print(farewell);
        }
        catch (JarvisException e) {
            System.out.println((e));
        }
    }
}
