package duke;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    Scanner sc = new Scanner(System.in);
    String filepath = "duke.txt";
    Storage storage = new Storage(filepath);

    public Parser() {
    }

    public void respond()  {
        try {
            String input = sc.nextLine();
            TaskList l= new Storage(filepath).load(new File(filepath));
            String[] arr = input.split(" ", 2);
            String command = arr[0];
            File file = new File(filepath);
            if (command.equals("mark") || command.equals("unmark")) {
                if (arr.length == 1) {
                    throw new MarkException(command);
                }
                int index = Integer.parseInt(arr[1]);
                Task b = l.get(index-1);
                if (command.equals("mark")) {
                    l.mark(l, index);
                } else if (command.equals("unmark")) {
                    l.unmark(l, index);
                }
                storage.overwriteFile(file, l);
                respond();
            } else if (command.equals("delete")) {
                if (arr.length == 1) {
                    throw new MarkException(command);
                }
                Task b = l.get(Integer.parseInt(arr[1]) - 1);
                Duke.count--;
                l.delete(l,b,Integer.parseInt(arr[1]) - 1);
                storage.overwriteFile(file,l);
                respond();
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (arr.length == 1) {
                    throw new EmptyCommandException(command);
                }
                Task task = null;
                if (command.equals("todo")) {
                    task = new Todo(arr[1]);
                } else if (command.equals("event")) {
                    String[] deets = arr[1].split("/at ", 2);
                    task = new Event(deets[0], parseString(deets[1]));
                } else if (command.equals("deadline")) {
                    String[] deets = arr[1].split("/by ", 2);
                    task = new Deadline(deets[0], parseString(deets[1]));
                }
                l.add(task);
                l.get(Duke.count++).print();
                storage.addTaskToFile(file,task);
                respond();
            } else if (input.equals(("bye"))) {
                Ui.bye();
            } else if (input.equals("list")) {
                l.list();
                respond();
            } else {
                throw new InvalidCommandException(command);
            }
        }
        catch (EmptyCommandException e){
            System.out.println(e.getMessage());
            respond();
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            respond();
        } catch (MarkException e) {
            System.out.println(e.getMessage());
            respond();
        }
    }
    public LocalDateTime parseString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            date = LocalDateTime.parse(s,formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
        }
        return date;
    }

    public LocalDateTime parseFileString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            date = LocalDateTime.parse(s,formatter);
        } catch (DateTimeParseException e) {
           System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
       }
        return date;
    }
}
