import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;


public class Duke {
    private static final ArrayList<Task> TaskList = new ArrayList<>();
    private static final String folderPath = "data";
    private static final String filePath = "data/duke.txt";

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToAdd = listToString();
            fw.write(textToAdd);
            fw.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    public static void addTask(Task task) {
        TaskList.add(task);
        int total = TaskList.size();
        String printLine = "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(printLine);
        writeToFile();
    }

    public static void mark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setCompleted();
        String taskCompletion = "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n";
        System.out.println(taskCompletion);
        writeToFile();
    }

    public static void unmark(int number) {
        Task task =  TaskList.get(number - 1);
        task.setUncompleted();
        String taskUnCompletion = "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n";
        System.out.println(taskUnCompletion);
        writeToFile();
    }

    public static void delete(int number) {
        Task task = TaskList.get(number - 1);
        TaskList.remove(number - 1);
        int total = TaskList.size();
        String message = "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(message);
        writeToFile();
    }

    public static String listToString() {
        String newList = "";
        int count = 1;
        for (Task item: TaskList) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        return newList;
    }

    public static void echo(String command) throws DukeException {
        if (command.trim().equals("bye")) {
            String bye = "Bye. Hope to see you again soon!\n";
            System.out.println(bye);
        } else if (command.trim().equals("list")) {
            String newList = "Here are the tasks in your list:\n";
            newList += listToString();
            System.out.println(newList);

        } else {
            if (command.startsWith("todo")) {
                    try {
                        ToDo toDo = new ToDo(command.substring(5));
                        addTask(toDo);
                    } catch (StringIndexOutOfBoundsException e){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                    }
            } else if (command.trim().startsWith("deadline")) {
                int end = command.indexOf('/');
                String name = command.substring(9, end);
                try {
                    String dateTime = command.substring(end + 4);
                    // an array containing a date and time respectively
                    String[] arrOfStr = dateTime.split(" ");
                    LocalDate date = LocalDate.parse(arrOfStr[0]);
                    LocalTime time = LocalTime.of(Integer.parseInt(arrOfStr[1].substring(0,2)),
                            Integer.parseInt(arrOfStr[1].substring(2)));
                    Deadline deadline = new Deadline(name, date, time);
                    addTask(deadline);
                }
                catch (Exception e){
                    System.out.println("Invalid Input for date and time");
                }

            } else if (command.trim().startsWith("event")) {
                int end = command.indexOf('/');
                String name = command.substring(6, end );
                String time = command.substring(end + 4);
                Event event = new Event(name,time);
                addTask(event);
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
    }
    public static void main(String[] args)  {
        String logo = "____________________________________________________________\n" + "Hello from\n" +
                " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" +
                "How may I assist you?\n" +
                "____________________________________________________________\n";
        System.out.println(logo);
        Scanner myScanner = new Scanner(System.in);

        // Folder data
        File newFolder = new File(folderPath);
        // File duke.txt
        File newFile = new File(filePath);

        if (!newFolder.exists()) {
            boolean result = newFolder.mkdir();
            if (result) {
                System.out.println("New directory created since no folder data is found");
            }
        }

        if (!newFile.exists()) {
            try {
                boolean result = newFile.createNewFile();
                if (result) {
                    System.out.println("New file created since no duke.txt is found");
                }

            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.println("Please enter your command:");
                String command = myScanner.nextLine();
                if (command.trim().startsWith("mark")) {
                    int number = Integer.parseInt(command.substring(5));
                    mark(number);
                } else if (command.trim().startsWith("unmark")) {
                    int number = Integer.parseInt(command.substring(7));
                    unmark(number);
                } else if (command.trim().startsWith("delete")) {
                    int number = Integer.parseInt(command.substring(7));
                    delete(number);
                } else {
                    echo(command);
                    if (command.startsWith("bye")) {
                        break;
                    }
                }
            }
            catch (DukeException e) {
                System.out.println(e.toString());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input :( ");
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid command");
            }

        }
    }
}
