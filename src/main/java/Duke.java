import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner input = new Scanner(System.in);

        // load list from Duke.txt file
        List<Task> l = loadList();

        while (true) {
            String line = input.nextLine();
            String command = line.replaceAll("\\s+", "");
            System.out.println("____________________________________________________________");

            // throw exception at incomplete commands
            if (checkIncompleteCommand(command)) {
                continue;
            }

            if ("bye".equals(line.trim())) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if ("list".equals(line.trim())) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : l) {
                    System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(),
                            t.getDescription()));
                    count += 1;
                }
            } else if ("mark".equals(line.split(" ")[0])) {
                int number = Integer.parseInt(line.substring(5));
                // throw exception if index out of task list length
                if (checkIndexBound(l, number)) {
                    continue;
                }
                try {
                    if (number > l.size() || number < 1) {
                        throw new DukeException("☹ OOPS!!! There is no such task in the list.");
                    }
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
                Task t = l.get(number - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            } else if ("unmark".equals(line.split(" ")[0])) {
                int number = Integer.parseInt(line.substring(7));
                // throw exception if index out of task list length
                if (checkIndexBound(l, number)) {
                    continue;
                }
                Task t = l.get(number - 1);
                t.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
            } else if ("delete".equals(line.split(" ")[0])) {
                int number = Integer.parseInt(line.substring(7));
                // throw exception if index out of task list length
                Task t = l.remove(number - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else if ("todo".equals(line.split(" ")[0])) {
                String task = line.substring(5).trim();
                ToDo t = new ToDo(task);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else if ("deadline".equals(line.split(" ")[0])) {
                String task = line.substring(9).split("/")[0].trim();
                String end = line.split("/")[1].trim();
                Deadline t = new Deadline(task, end);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else if ("event".equals(line.split(" ")[0])) {
                String task = line.substring(6).split("/")[0].trim();
                String time = line.split("/")[1].trim();
                Event t = new Event(task, time);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
            }
            saveList(l);
            System.out.println("____________________________________________________________");
        }
    }

    public static boolean checkIndexBound(List<Task> l, int number) {
        try {
            if (number > l.size() || number < 1) {
                throw new DukeException("☹ OOPS!!! The task index cannot be found in the list.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            System.out.println("____________________________________________________________");
            return true;
        }
        return false;
    }

    public static boolean checkIncompleteCommand(String command) {
        try {
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", command));
            }
            if (command.length() >= 9 && command.substring(0,9).equals("deadline/")) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a deadline cannot be empty."));
            }
            if (command.length() >= 6 && command.substring(0,6).equals("event/")) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a event cannot be empty."));
            }
            if (command.equals("mark") || command.equals("unmark")) {
                throw new DukeException(String.format("☹ OOPS!!! The task index to %s cannot be empty.", command));
            }
            if (command.length() >= 4 && "mark".equals(command.substring(0, 4)) && !command.matches(".*[0-9]")) {
                throw new DukeException("☹ OOPS!!! There is no task index to mark.");
            }

            if (command.length() >= 6 && "delete".equals(command.substring(0, 6)) && !command.matches(".*[0-9]")) {
                throw new DukeException("☹ OOPS!!! There is no task index to delete.");
            }

            if (command.length() >= 6 && "unmark".equals(command.substring(0, 6)) && !command.matches(".*[0-9]")) {
                throw new DukeException("☹ OOPS!!! There is no task index to unmark.");
            }
            if (command.length() >= 5 && "event".equals(command.substring(0, 5)) && !command.contains("/")) {
                throw new DukeException("☹ OOPS!!! There is no start and end time given for a event.");
            }
            if (command.length() >= 8 && "deadline".equals(command.substring(0, 8)) && !command.contains("/")) {
                throw new DukeException("☹ OOPS!!! There is no end time given for a deadline.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            System.out.println("____________________________________________________________");
            return true;
        }
        return false;
    }

    public static void saveList(List<Task> l) throws DukeException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            File f = new File("data/Duke.txt");
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter("data/Duke.txt");
            fw.write("Task Type | Status | Description & Time\n");
            for (Task t : l) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Oops! IO exception.");
        }
    }

    public static List<Task> loadList() throws DukeException {
        File f = new File("data/Duke.txt");
        List<Task> l = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(f);
            s.nextLine(); //skip header line
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] line = str.split("\\|");
                if (line[0].equals("Todo      ")) {
                    l.add(new ToDo(line[2].trim(), " Done   ".equals(line[1])));
                } else if (line[0].equals("Deadline  ")) {
                    l.add(new Deadline(line[2].trim(), " Done   ".equals(line[1]),
                            line[3].replaceAll("\\s+","")));
                } else {
                    l.add(new Event(line[2].trim(), " Done   ".equals(line[1]),
                            line[3].replaceAll("\\s+","")));
                }
            }
            return l;
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        }
    }
}

