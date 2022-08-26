package duke;

import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

/**
 * Parses the user command.
 */
public class Parser {

    Scanner sc = new Scanner(System.in);
    private String filepath = "duke.txt";
    private Storage storage = new Storage(filepath);

    /**
     * Constructs a parser.
     */
    public Parser() {
    }

    /**
     * Interacts with the user based on the command line input.
     */
    public void respond() {
        try {
            String input = sc.nextLine();
            TaskList list= new Storage(filepath).load(new File(filepath));
            String[] descriptions = input.split(" ", 2);
            String command = descriptions[0];
            File file = new File(filepath);
            if (command.equals("mark") || command.equals("unmark")) {
                if (descriptions.length == 1) {
                    throw new MarkException(command);
                }
                int index = Integer.parseInt(descriptions[1]);
                Task task = list.get(index-1);
                if (command.equals("mark")) {
                    list.mark(list, index);
                } else if (command.equals("unmark")) {
                    list.unmark(list, index);
                }
                storage.overwriteFile(file, list);
                respond();
            } else if (command.equals("delete")) {
                if (descriptions.length == 1) {
                    throw new MarkException(command);
                }
                Task task = list.get(Integer.parseInt(descriptions[1]) - 1);
                Duke.count--;
                list.delete(list, task,Integer.parseInt(descriptions[1]) - 1);
                storage.overwriteFile(file, list);
                respond();
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                if (descriptions.length == 1) {
                    throw new EmptyCommandException(command);
                }
                Task task = null;
                if (command.equals("todo")) {
                    task = new Todo(descriptions[1]);
                } else if (command.equals("event")) {
                    String[] deets = descriptions[1].split("/at ", 2);
                    task = new Event(deets[0], parseString(deets[1]));
                } else if (command.equals("deadline")) {
                    String[] deets = descriptions[1].split("/by ", 2);
                    task = new Deadline(deets[0], parseString(deets[1]));
                }
                list.add(task);
                list.get(Duke.count++).print();
                storage.addTaskToFile(file, task);
                respond();
            } else if (input.equals(("bye"))) {
                Ui.bye();
            } else if (input.equals("list")) {
                list.list();
                respond();
            } else if (command.equals("find")) {
                list.find(list, descriptions[1]);
                respond();
            } else {
                throw new InvalidCommandException(command);
            }
        } catch (EmptyCommandException e){
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

    /**
     * Converts strings to date format.
     *
     * @param s string representation of the date.
     * @return the date as specified by the string.
     */
    public LocalDateTime parseString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
        }
        return date;
    }

    /**
     * Converts the strings in a file to date format.
     *
     * @param s string representation of the date.
     * @return the date as specified by the string.
     */
    public LocalDateTime parseFileString(String s) {
        DateTimeFormatter formatter = null;
        LocalDateTime date = null;
        try {
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
           System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
       }
        return date;
    }
}
