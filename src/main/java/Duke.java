import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    public static void main(String[] args) throws DukeException {
        System.out.println(Constants.INDENTED_DOTTED_LINE);
        System.out.println(Constants.WELCOME_MESSAGE);
        System.out.println(Constants.INDENTED_DOTTED_LINE);

        Scanner sc = new Scanner(System.in);
        //handle folder-does-not-exist-yet case
        FileOps fileOps = new FileOps();
        fileOps.run();
        List<Task> history = fileOps.loadData();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + "Bye! Hope to see you again soon!");
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                ListIterator<Task> listIterator = history.listIterator();
                while (listIterator.hasNext()) {
                    Task t = listIterator.next();
                    System.out.println(Constants.indent + listIterator.nextIndex() +
                            ". " + t);
                }
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                t.markAsDone();
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + "Nice! I've marked this task as done");
                System.out.println(Constants.indent+ Constants.indent + t);
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                t.markAsNotDone();
                System.out.println(Constants.indent + "OK, I've marked this task as not done yet");
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + Constants.indent + t);
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("todo")) {
                Task t = new Todo(input);
                history.add(t);
                fileOps.write(t.stringToWrite());
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + "Got it. I've added this task:");
                System.out.println(Constants.indent + Constants.indent + t);
                System.out.println(Constants.indent + "Now you have " + history.size() + " tasks in the list.");
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("deadline")) {
                if(!input.contains("/by")) {
                    throw new DukeException("please use /by to indicate date for deadline");
                }
                String deadline = input.substring(input.lastIndexOf("/by") + 4);
                Pattern pattern = Pattern.compile("deadline(.*?)/by");
                Matcher matcher = pattern.matcher(input);
                String description = "";
                try{
                    LocalDate.parse(deadline);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                } finally {

                }
                while (matcher.find()) {
                    description = matcher.group(1);
                }
                Task t = new Deadline(description, LocalDate.parse(deadline));
                history.add(t);
                fileOps.write(t.stringToWrite());
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + "Got it. I've added this task:");
                System.out.println(Constants.indent + Constants.indent + t);
                System.out.println(Constants.indent +"Now you have " + history.size() + " tasks in the list.");
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("event")) {
                if(!input.contains("/at")) {
                    throw new DukeException("please use /by to indicate date for deadline");
                }
                String date = input.substring(input.lastIndexOf("/at") + 4);
                Pattern pattern = Pattern.compile("event(.*?)/at");
                Matcher matcher = pattern.matcher(input);
                String description = "";
                while (matcher.find()) {
                    description = matcher.group(1);
                }
                try{
                    LocalDate parsedDate = LocalDate.parse(date);
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }

                Task t = new Event(description, LocalDate.parse(date));
                history.add(t);
                fileOps.write(t.stringToWrite());
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent + "Got it. I've added this task:");
                System.out.println(Constants.indent + Constants.indent + t);
                System.out.println(Constants.indent + "Now you have " + history.size() + " tasks in the list.");
                System.out.println(Constants.INDENTED_DOTTED_LINE);
            } else if (input.startsWith("delete")){
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                System.out.println(Constants.INDENTED_DOTTED_LINE);
                System.out.println(Constants.indent +"Noted. I've removed this task:");
                System.out.println(Constants.indent + Constants.indent + t);
                history.remove(t);
                fileOps.rewrite(history);
                System.out.println(Constants.indent +"Now you have " + history.size() + " tasks in the list.");
                System.out.println(Constants.INDENTED_DOTTED_LINE);

            } else {
                throw new DukeException("Invalid command! Please try again");
            }
        }
    }
}

