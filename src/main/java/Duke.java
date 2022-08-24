import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;

enum Command {

    BYE,
    DEADLINE,
    DEFAULT,
    DELETE,
    EVENT,
    LIST,
    MARK,
    TODO,
    UNMARK

}

public class Duke {
    
    public static void main(String[] args) throws DukeException {
        final String ADDED_TASK = "Got it. I've added this task:";
        final String REMOVED_TASK = "Noted. I've removed this task:";
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<String> stringOfCommands = new ArrayList<>(Arrays.asList("BYE",
            "DEADLINE", "DELETE", "EVENT", "LIST", "MARK", "TODO", "UNMARK"));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Luke\nWhat can I do for you?");
        String input = "";
        System.out.println("hi");


        

        try {
            Path path = Paths.get("./data/");
            Files.createDirectories(path);
            File file = new File("./data/Duke.txt");
            file.createNewFile();

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                char action = data.charAt(1);
                if (action == 'T') {
                    Task task = new Todo(data.substring(7));
                    tasks.add(task);
                } else if(action =='E') {
                    int symbol = data.indexOf("(");
                    Task task = new Event(data.substring(7, symbol), data.substring(symbol + 5, data.length() - 1));
                    tasks.add(task);
                } else {
                    int symbol = data.indexOf("(");
                    Task task = new Deadline(data.substring(7, symbol), data.substring(symbol + 5, data.length() - 1));
                    tasks.add(task);
                }

                

            }
            reader.close();



        } catch (IOException e) {
            System.out.println(e);
        }
        
        while (!input.equals("bye")) {
            try {
                input = scanner.nextLine().strip();
                String commandString = input.split(" ")[0].toUpperCase();
                Command command = Command.DEFAULT;
                if (stringOfCommands.contains(commandString)) {
                    command = Command.valueOf(commandString);
                }
                int taskNumber;
                Task task;
                String[] split;

                switch(command) {

                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            task = tasks.get(i);
                            System.out.println(String.valueOf(i + 1) + "." + task);
                        }
                        break;
        
                    case MARK:
                        if (input.length() == 4) {
                            throw new DukeException("Choose which task to mark as done!");
                        }
                        taskNumber = Integer.parseInt(input.substring(5));
                        tasks.get(taskNumber - 1).setDone();
                        break;
        
        
                    case UNMARK:
                        if (input.length() == 6) {
                            throw new DukeException("Choose which task to mark as undone!");
                        }
                        taskNumber = Integer.parseInt(input.substring(7));
                        tasks.get(taskNumber - 1).setUndone();
                        break;
                        
        
                    case TODO:
                        if (input.length() == 4) {
                            throw new DukeException("The description of a todo cannot be empty");
                        }
                        
                        task = new Todo(input.substring(5));
                        tasks.add(task);
                        System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                        break;
                        
                    case EVENT:
                        if (input.length() == 5) {
                            throw new DukeException("The description of an event cannot be empty");
                        }

                        if (input.indexOf('/') == -1) {
                            throw new DukeException("The date of the event cannot be empty");
                        } 

                        split = input.substring(6).split("/");
                        task = new Event(split[0], split[1].substring(3));
                        tasks.add(task);
                        System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                        break;
        
                    case DEADLINE:
                        if (input.length() == 8) {
                            throw new DukeException("The description of an deadline cannot be empty");
                        }

                        if (input.indexOf('/') == -1) {
                            throw new DukeException("The date of the deadline cannot be empty");
                        }


                        split = input.substring(9).split("/");
                        task = new Deadline(split[0], split[1].substring(3));
                        tasks.add(task);
                        System.out.println(String.format("%s\n%s", ADDED_TASK, task));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                        break;

                    case DELETE:
                        if (input.length() == 6) {
                            throw new DukeException("Choose which task to delete!");
                        }
                        taskNumber = Integer.parseInt(input.substring(7)) - 1 ;
                        task = tasks.get(taskNumber);
                        tasks.remove(taskNumber);
                        System.out.println(String.format("%s\n%s", REMOVED_TASK, task));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ?" task" : " tasks") + " in the list.");
                        break;

                    case BYE:
                        System.out.println("Bye! Thanks for using Luke!");
                        scanner.close();
                        break;
            
                     

                    default:    
                        throw new DukeException("I'm sorry, but I dont know what you mean :(");
                }
                
                
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                try {
                    FileWriter writer = new FileWriter("./data/Duke.txt");
                    String str = "";
                    for (int i = 0; i <tasks.size(); i++) {
                        str += tasks.get(i).toString();
                        str += "\n";
                    }
                    writer.write(str);
                    writer.close();

                } catch (IOException e) {
                    System.out.println(e);
                }
            }

        }
    
    }

    
}
