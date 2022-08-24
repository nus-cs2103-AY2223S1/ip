import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("========================================================================================");
        System.out.println("Hello! I'm Duke, your personalized chatbot to arrange your tasks!");
        System.out.println("________________________________________________________________________________________");
        System.out.println("There are 3 types of task implemented: " +
                "\n1. todo     : tasks without any date/time attached to it" +
                "\n2. deadline : tasks that need to be done before a specific date/time" +
                "\n3. event    : tasks that start at a specific time and ends at a specific time");
        System.out.println("________________________________________________________________________________________");
        System.out.println("Below is all the command you can use: ");
        System.out.println("  Command                | Command Format");
        System.out.println("  1. Add todo            | todo {task description}");
        System.out.println("  2. Add deadline        | deadline {task description} /by {end date}");
        System.out.println("  3. Add event           | event {task description} /at {start date} to {end date}");
        System.out.println("  7. List all tasks      | list");
        System.out.println("  4. Delete task         | delete {task index in the list}");
        System.out.println("  5. Mark task as done   | mark {task index in the list}");
        System.out.println("  6. Mark task as undone | unmark {task index in the list}");
        System.out.println("  8. Leave chatbot       | bye");
        System.out.println("________________________________________________________________________________________");
        System.out.println("Remarks:  ");
        System.out.println("1. Acceptable date formats include dd/MM/yyyy, yyyy/MM/dd, yyyy-MM-dd, dd-MM-yyyy, ");
        System.out.println("   dd MM yyyy, yyyy MM dd.");
        System.out.println("2. Task list will be auto-saved after bye command and auto-loaded when chatbot starts up.");
        System.out.println("========================================================================================");
        Scanner input = new Scanner(System.in);

        // load list from Duke.txt file
        List<Task> l = loadList();

        while (true) {
            String line = input.nextLine();
            String command = line.replaceAll("\\s+", "");
            System.out.println("________________________________________________________________________________________");

            // throw exception at incomplete commands
            if (checkIncompleteCommand(command)) {
                continue;
            }

            if ("bye".equals(line.trim())) {
                System.out.println("Chatbot stopped, all previous tasks will be auto-saved.");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________________________________________________________");
                break;
            } else if ("list".equals(line.trim())) {
                System.out.println("Here are the tasks in your list:");
                printTasks(l);
            }else if ("list".equals(line.split(" ")[0])) {
                String time = line.substring(5).trim();
                if (checkTimeFormat(time)) {
                    continue;
                };
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat(time));
                LocalDate date = LocalDate.parse(time, formatter);
                List<Task> ddlAtDate = new ArrayList<Task>();
                List<Task> ddlBeforeDate = new ArrayList<Task>();
                List<Task> eventAtDate = new ArrayList<Task>();
                // find all event and deadlines related to this date
                for (Task t : l) {
                    if (!t.getStatus() && t.taskType().equals("event")) {
                        Event e = (Event) t;
                        if (checkTimeDifference(e.getStartDate(), date, false) != 1 && checkTimeDifference(e.getEndDate(), date, false) != -1) {
                            eventAtDate.add(e);
                        }
                    } else if (!t.getStatus() && t.taskType().equals("deadline")) {
                        Deadline d = (Deadline) t;
                        if (checkTimeDifference(d.getEnd(), date, false) == 1) {
                            ddlBeforeDate.add(d);
                        } else if (checkTimeDifference(d.getEnd(), date, false) == 0) {
                            ddlAtDate.add(d);
                        }
                    }
                }
                System.out.println(String.format("!   You have these events undone on %s:", date));
                printTasks(eventAtDate);
                System.out.println(String.format("!!  You have these unfinished deadlines due after %s:", date));
                printTasks(ddlBeforeDate);
                System.out.println(String.format("!!! You have these unfinished deadlines due on %s:", date));
                printTasks(ddlAtDate);
            }else if ("mark".equals(line.split(" ")[0])) {
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
                    System.out.println("________________________________________________________________________________________");
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
                String end = line.split("/by")[1].trim();
                if (checkTimeFormat(end)) {
                    continue;
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat(end));
                LocalDate endDate = LocalDate.parse(end, formatter);
                Deadline t = new Deadline(task, endDate);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else if ("event".equals(line.split(" ")[0])) {
                String task = line.substring(6).split("/")[0].trim();
                String[] time = line.split("/at")[1].split("to");
                String start = time[0].trim();
                String end = time[1].trim();
                if (checkTimeFormat(start) || checkTimeFormat(end)) {
                    continue;
                };
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat(start));
                LocalDate startDate = LocalDate.parse(start, formatter);
                formatter = DateTimeFormatter.ofPattern(dateFormat(end));
                LocalDate endDate = LocalDate.parse(end, formatter);
                if (checkTimeDifference(startDate, endDate, true) == 1) {
                    continue;
                }
                Event t = new Event(task, startDate, endDate);
                l.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(String.format("  %s %s", t.getStatusIcon(), t.getDescription()));
                System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("________________________________________________________________________________________");
                    continue;
                }
            }
            saveList(l);
            System.out.println("________________________________________________________________________________________");
        }
    }
    public static void printTasks(List<Task> l) {
        int count = 1;
        for (Task t : l) {
            System.out.println(String.format("%d.%s %s", count, t.getStatusIcon(),
                    t.getDescription()));
            count += 1;
        }
    }

    public static int checkTimeDifference(LocalDate start, LocalDate end, Boolean excep) {
        int result;
        if (start.isBefore(end)) {
            result = -1;
        } else if (start.equals(end)) {
            result = 0;
        } else {
            result = 1;
        }
        if (excep && result == 1) {
            try {
                throw new DukeException("☹ OOPS!!! The date range for the event is invalid (start date > end date).");
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
                System.out.println("________________________________________________________________________________________");
            }
        }
        return result;
    }

    public static boolean checkIndexBound(List<Task> l, int number) {
        try {
            if (number > l.size() || number < 1) {
                throw new DukeException("☹ OOPS!!! The task index cannot be found in the list.");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
            System.out.println("________________________________________________________________________________________");
            return true;
        }
        return false;
    }

    public static boolean checkTimeFormat(String end) {
        try {
            if (dateFormat(end).equals("")) {
                throw new DukeException("☹ OOPS!!! This is not a proper time format, please refer to Remarks 1.");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat(end));
                LocalDate endDate = LocalDate.parse(end, formatter);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("________________________________________________________________________________________");
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
            System.out.println("________________________________________________________________________________________");
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
                            LocalDate.parse(line[3].trim())));
                } else {
                    String[] time = line[3].split("to");
                    String start = time[0].trim();
                    String end = time[1].trim();
                    LocalDate endDate = LocalDate.parse(end);
                    LocalDate startDate = LocalDate.parse(start);
                    l.add(new Event(line[2].trim(), " Done   ".equals(line[1]), startDate, endDate));
                }
            }
            return l;
        } catch (FileNotFoundException e) {
            return new ArrayList<Task>();
        }
    }
    public static String dateFormat(String time) {
        if (time.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
            return "dd/MM/yyyy";
        } else if (time.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
            return "yyyy/MM/dd";
        } else if (time.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
            return "dd-MM-yyyy";
        } else if (time.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
            return "yyyy-MM-dd";
        } else if (time.matches("([0-9]{4}) ([0-9]{2}) ([0-9]{2})")) {
            return "yyyy MM dd";
        } else if (time.matches("([0-9]{2}) ([0-9]{2}) ([0-9]{4})")) {
            return "dd MM yyyy";
        } else {
            return "";
        }


    }


}
