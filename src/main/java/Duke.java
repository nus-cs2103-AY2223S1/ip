import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run() throws DukeException{
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        List<Task> history = storage.loadData();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                ui.showByeMessage();
                sc.close();
                break;
            } else if (input.equals("list")) {
                ui.listAllTasks(history);
                ui.showIndentedDottedLines();
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                t.markAsDone();
                ui.showTaskMarkMessage(t);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                t.markAsNotDone();
                ui.showTaskUnmarkMessage(t);
            } else if (input.startsWith("todo")) {
                Task t = new Todo(input);
                history.add(t);
                storage.write(t.stringToWrite());
                ui.newItemAdded(t, history.size());
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
                storage.write(t.stringToWrite());
                ui.newItemAdded(t, history.size());
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
                storage.write(t.stringToWrite());
                ui.newItemAdded(t, history.size());
            } else if (input.startsWith("delete")){
                int index = Integer.parseInt(input.replaceAll("[\\D]", ""));
                Task t = history.get(index - 1);
                history.remove(t);
                storage.rewrite(history);
                ui.showTaskDeletedMessage(t, history.size());

            } else {
                throw new DukeException("Invalid command! Please try again");
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("saved.txt").run();
    }
}

