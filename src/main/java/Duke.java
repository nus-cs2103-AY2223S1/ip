import main.java.Task;
import main.java.ToDo;
import main.java.Deadline;
import main.java.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> ls = new ArrayList<>();
        String line = "-----------------------------";
        String answer;

        // Duke's self-intro
        System.out.println(line + "\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you\n" +
                line + "\n");

        try {
            String fileName = "./src/duke.txt";
            File file = new File(fileName);
            Scanner scReadFile = new Scanner(file); // Level 7: (Read and) Save

            while (true) {
                boolean hasTaskInFile = scReadFile.hasNextLine();
                answer = (hasTaskInFile ? scReadFile.nextLine() : sc.nextLine());

                if (answer.equals("bye")) {
                    System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" +
                            line + "\n");
                    break;
                }
                else if (answer.equals("list")) { // Task 2
                    System.out.println(line + "\n");
                    list(ls);
                    System.out.println(line + "\n");
                }
                else if (answer.startsWith("mark")) { // Task 3: mark
                    int idx = Integer.parseInt(answer.substring(5)) - 1;
                    Task t = ls.get(idx);
                    t.mark();
                    System.out.println(line + "\n" + "Nice! I've marked this task as done:" + "\n" +
                            "[X] " + t + "\n" +
                            line + "\n");
                    if (!hasTaskInFile) {
                        String toAppend = "mark " + (idx + 1);
                        appendToFile(fileName, toAppend);
                    }
                }
                else if (answer.startsWith("unmark")) { // Task 3: unmark
                    int idx = Integer.parseInt(answer.substring(7)) - 1;
                    Task t = ls.get(idx);
                    t.unmark();
                    System.out.println(line + "\n" + "OK, I've marked this task as not done yet:" + "\n" +
                            "[ ] " + t + "\n" +
                            line + "\n");
                    if (!hasTaskInFile) {
                        String toAppend = "unmark " + (idx + 1);
                        appendToFile(fileName, toAppend);
                    }
                }
                else if (answer.startsWith("todo")) { // Task 4: todo
                    try {
                        String toDoAction = answer.substring(5);
                        if (!toDoAction.equals("")) { // Level 5: handle todo exception
                            ToDo t = new ToDo(toDoAction);
                            ls.add(t);
                            System.out.println(line + "\n" +
                                    "Got it. I've added this task: " + "\n" +
                                    t + "\n" +
                                    "Now you have " + ls.size() + " tasks in the list." + "\n" +
                                    line + "\n");
                            if (!hasTaskInFile) {
                                String toAppend = "todo " + toDoAction;
                                appendToFile(fileName, toAppend);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(line + "\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                line + "\n");
                    }
                }
                else if (answer.equals("blah")) { // Level 5: handle blah exception
                    throw new Exception("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                else if (answer.startsWith("deadline")) { // Task 4: deadline, Task 8: Dates and Times
                    String deadlineAction = answer.substring(9, answer.indexOf("/") - 1);
                    String by = answer.substring(answer.indexOf("/") + 4); // Format: dd/MM/yyyy HHmm
    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
    
                    Deadline d = new Deadline(deadlineAction, dateTime);
                    ls.add(d);
                    System.out.println(line + "\n" +
                            "Got it. I've added this task: " + "\n" +
                            d + "\n" +
                            "Now you have " + ls.size() + " tasks in the list." + "\n" +
                            line + "\n");
                }
                else if (answer.startsWith("event")) { // Task 4: event
                    String eventAction = answer.substring(6, answer.indexOf("/") - 1);
                    String at = answer.substring(answer.indexOf("/") + 4); // Format: dd/MM/yyyy HHmm
    
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                    LocalDateTime dateTime = LocalDateTime.parse(at, formatter);
    
                    Event e = new Event(eventAction, dateTime);
                    ls.add(e);
                    System.out.println(line + "\n" +
                            "Got it. I've added this task: " + "\n" +
                            e + "\n" +
                            "Now you have " + ls.size() + " tasks in the list." + "\n" +
                            line + "\n");
                } else if (answer.startsWith("delete")) { // Level 6: delete
                    int idx = Integer.parseInt(answer.substring(7)) - 1;
                    Task t = ls.get(idx);
                    ls.remove(idx);
                    System.out.println(line + "\n" + "Noted. I've removed this task:" + "\n" +
                            t + "\n" +
                            "Now you have " + ls.size() + " tasks in the list.\n" +
                            line + "\n");
                }
                else { // for normal actions
                    System.out.println(line + "\n" + "added: " + answer + "\n" +
                            line + "\n");
                    ls.add(new Task(answer));
                }
            }
        } catch (FileNotFoundException e) { // Level 7: handle if file does not exist
            try { // to create new file
                String fileName = "./src/duke.txt";
                File newFile = new File(fileName);
                if (newFile.createNewFile()) {
                    System.out.println("File has been created.");
                }
            } catch (IOException ee) { // file cannot be created
                System.out.println("File already exists. Please search for it.");
            }
        }
    }

    // For Level-3
    private static void list(ArrayList ls) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            Task t = (Task) ls.get(i);
            System.out.println((i + 1) + "." + t);
        }
    }

    // For Level-7
    private static void appendToFile(String fileName, String text) {
        try {
            Files.write(Paths.get(fileName), ("\n" + text).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error occurred while appending text to file.");
        }
    }

}
