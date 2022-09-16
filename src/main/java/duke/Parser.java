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

    private Scanner sc = new Scanner(System.in);
    private String filepath = "dukes.txt";
    private Storage storage = new Storage(filepath);
    private Duke duke = new Duke();

    /**
     * Constructs a parser.
     */
    public Parser() {
    }

    /**
     * Responds to the mark command.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public void markRespond(String command, String[] descriptions, TaskList list, File file) {
        try {
            if (descriptions.length == 1) {
                throw new MarkException(command);
            }
            assert descriptions.length != 1 : "should have a mark index";
            int index = Integer.parseInt(descriptions[1]);
            Task task = list.get(index - 1);
            if (command.equals("mark")) {
                list.mark(list, index);
            } else if (command.equals("unmark")) {
                list.unmark(list, index);
            }
            storage.overwriteFile(file, list);
            respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respond();
        }
    }

    /**
     * Responds to the delete command.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public void deleteRespond(String command, String[] descriptions, TaskList list, File file) {
        try {
            if (descriptions.length == 1) {
                throw new MarkException(command);
            }
            Task task = list.get(Integer.parseInt(descriptions[1]) - 1);
            duke.minusCount();
            list.delete(list, task, Integer.parseInt(descriptions[1]) - 1);
            storage.overwriteFile(file, list);
            respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respond();
        }
    }

    /**
     * Responds to the add task command.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public void taskRespond(String command, String[] descriptions, TaskList list, File file) {
        try {
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
            int number = duke.getCount();
            duke.addCount();
            list.get(number).print();
            storage.addTaskToFile(file, task);
            respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respond();
        }
    }

    /**
     * Responds to the mark command of Gui.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public String markRespondGui(String command, String[] descriptions, TaskList list, File file) {
        try {
            if (descriptions.length == 1) {
                throw new MarkException(command);
            }
            int index = Integer.parseInt(descriptions[1]);
            Task task = list.get(index - 1);
            String st = "";
            if (command.equals("mark")) {
                st = list.markGui(list, index);
            } else if (command.equals("unmark")) {
                list.unmark(list, index);
                st = list.unmarkGui(list, index);
            }
            storage.overwriteFile(file, list);
            return st;
        } catch (Exception e) {
            if (e instanceof MarkException) {
                return e.getMessage();
            } else if (e instanceof IndexOutOfBoundsException) {
                return "Try a smaller number!";
            }
            return "Hey!! Please input a number";
        }
    }

    /**
     * Responds to the delete command of Gui.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public String deleteRespondGui(String command, String[] descriptions, TaskList list, File file) {
        try {
            if (descriptions.length == 1) {
                throw new MarkException(command);
            }
            Task task = list.get(Integer.parseInt(descriptions[1]) - 1);
            duke.minusCount();
            String st = list.deleteGui(list, task, Integer.parseInt(descriptions[1]) - 1);
            storage.overwriteFile(file, list);
            return st;
        } catch (Exception e) {
            if (e instanceof MarkException) {
                return e.getMessage();
            } else if (e instanceof NumberFormatException) {
                return "Hey! Please insert a number";
            }
            return "Hey friend! Please try a smaller number";
        }
    }

    /**
     * Responds to the find priority command.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public void findPriorityRespond(String command, String[] descriptions, TaskList list, File file) {
        if (descriptions.length == 1) {
            list.findPriority(list, command);
        } else {
            int index = Integer.parseInt(descriptions[1]);
            list.setPriority(list, index, Character.toUpperCase(command.charAt(0)));
        }
        storage.overwriteFile(file, list);
    }


    /**
     * Responds to the add task command of Gui.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public String taskRespondGui(String command, String[] descriptions, TaskList list, File file) {
        try {
            if (descriptions.length == 1) {
                throw new EmptyCommandException(command);
            }
            Task task = null;
            if (command.equals("todo")) {
                task = new Todo(descriptions[1]);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            if (command.equals("event")) {
                String[] deets = descriptions[1].split("/at ", 2);
                LocalDateTime date = LocalDateTime.parse(deets[1], formatter);
                task = new Event(deets[0], date);
            } else if (command.equals("deadline")) {
                String[] deets = descriptions[1].split("/by ", 2);
                LocalDateTime date = LocalDateTime.parse(deets[1], formatter);
                task = new Deadline(deets[0], date);
            }
            list.add(task);
            int number = duke.getCount();
            duke.addCount();
            String st = list.get(number).printGui();
            storage.addTaskToFile(file, task);
            return st;
        } catch (Exception e) {
            if (e instanceof DateTimeParseException) {
                return "Please use time in dd/MM/yyyy HH:mm format";
            } else if (e instanceof ArrayIndexOutOfBoundsException) {
                return "Please insert a time in dd/MM/yyyy HH:mm format";
            }
            return e.getMessage();
        }
    }

    /**
     * Responds to the find priority command of Gui.
     * @param command the command.
     * @param descriptions the command descriptions.
     * @param list the tasklist.
     * @param file the file to be stored.
     */
    public String findPriorityRespondGui(String command, String[] descriptions, TaskList list, File file) {
        try {
            String st = "";
            if (descriptions.length == 1) {
                st += list.findPriority(list, command);
            } else {
                int index = Integer.parseInt(descriptions[1]);
                st += list.setPriority(list, index, Character.toUpperCase(command.charAt(0)));
            }
            storage.overwriteFile(file, list);
            return st;
        } catch (NumberFormatException e) {
            return "Hey fren! Please insert a number.";
        }
    }

    /**
     * Interacts with the user based on the command line input.
     */
    public void respond() {
        try {
            String input = sc.nextLine();
            TaskList list = new Storage(filepath).load(filepath);
            String[] descriptions = input.split(" ", 2);
            String command = descriptions[0];
            File file = new File(filepath);
            if (command.equals("mark") || command.equals("unmark")) {
                markRespond(command, descriptions, list, file);
            } else if (command.equals("delete")) {
                deleteRespond(command, descriptions, list, file);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                taskRespond(command, descriptions, list, file);
            } else if (input.equals(("bye"))) {
                Ui.bye();
            } else if (input.equals("list")) {
                list.list();
            } else if (command.equals("find")) {
                list.find(list, descriptions[1]);
            } else if (command.equals("low") || command.equals("high") || command.equals("medium")) {
                findPriorityRespond(command, descriptions, list, file);
            } else {
                throw new InvalidCommandException(command);
            }
            respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            respond();
        }
    }

    /**
     * Interacts with the user based on command.
     *
     * @param input the command.
     * @return the according response.
     */
    public String respond(String input) {
        try {
            String filepath = this.filepath;
            Storage storage = new Storage(filepath);
            TaskList list = storage.load(filepath);
            String[] descriptions = input.split(" ", 2);
            String command = descriptions[0];
            File file = new File(filepath);
            String st = "";
            if (command.equals("mark") || command.equals("unmark")) {
                st = markRespondGui(command, descriptions, list, file);
            } else if (command.equals("delete")) {
                st = deleteRespondGui(command, descriptions, list, file);
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                st = taskRespondGui(command, descriptions, list, file);
            } else if (input.equals(("bye"))) {
                return Ui.byeGui();
            } else if (input.equals("list")) {
                return list.listGui();
            } else if (command.equals("find")) {
                return list.findGui(list, descriptions);
            } else if (command.equals("low") || command.equals("high") || command.equals("medium")) {
                return findPriorityRespondGui(command, descriptions, list, file);
            } else {
                throw new InvalidCommandException(command);
            }
            return st;
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    /**
     * Converts strings to date format.
     *
     * @param s string representation of the date.
     * @return the date as specified by the string.
     */
    public LocalDateTime parseString(String s) {
        LocalDateTime date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
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
        LocalDateTime date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            date = LocalDateTime.parse(s, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Please use time in dd/MM/yyyy HH:mm format");
            respond();
        }
        return date;
    }
}
