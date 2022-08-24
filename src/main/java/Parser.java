import java.text.ParseException;
import java.util.ArrayList;

public class Parser {

    private final Ui ui;
    private SavedTaskHandler storage;

    public Parser(SavedTaskHandler storage) {
        this.storage = storage;
        ui = new Ui();

    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
    }

    public void parse(String input) throws ParseException {
        String[] splitStr = input.split("\\s+");
        TaskList taskList =  storage.getTaskList();


        if (input.equalsIgnoreCase("bye")) {
            storage.write(taskList);
            ui.goodbyeMessage();

        } else if (input.equalsIgnoreCase("list")) {
            int counter = 1;
            ui.lineBreak();
            taskList.list();

        } else if (input.substring(0, Math.min(input.length(), 4)).equals("mark") || input.substring(0, Math.min(input.length(), 6)).equals("unmark") || input.substring(0, Math.min(input.length(), 6)).equals("delete")) {
            String str = "";
            boolean error = false;
            String type = splitStr[0];

            try {

                if (splitStr.length > 2 || splitStr.length == 1 || !isNumeric(splitStr[1])) {
                    error = true;
                    throw new DukeException("LUNA is unsure of what you are asking of her... ");
                }
            }

            catch (DukeException e) {
                System.out.println(e);
            }

            if (!error) {
                str = splitStr[1];
                int index = Integer.valueOf(str) - 1;

                if (input.contains("unmark")) {
                    Task task = taskList.get(index);
                    task.unmark();
                    String taskName = task.toString();
                    taskList.set(index, task);
                    ui.unmarkMessage(taskName);

                } else if (input.contains("mark")) {
                    Task task = taskList.get(index);
                    task.mark();
                    String taskName = task.toString();
                    taskList.set(index, task);
                    ui.markMessage(taskName);
                } else {
                    Task task = taskList.get(index);
                    taskList.remove(index);
                    String taskName = task.toString();
                    ui.deleteMessage(taskName, taskList.size());

                }
            }
        } else {
            String type = splitStr[0];
            String[] findTask = input.split(type);
            String actualTask = "";
            boolean error = false;

            try {
                if (findTask.length == 0) {
                    error = true;

                    if (type.equals("todo")) {
                        throw new DukeException("To do? To do what? ");
                    } else if (type.equals("event")) {
                        throw new DukeException("And what event exactly? When is the event? ");
                    } else if (type.equals("deadline")){
                        throw new DukeException("You'll probably miss it if you continue to give LUNA vague requests... ");
                    } else {
                        throw new DukeException("LUNA has consulted the Moon Goddess and even she has no idea what you're saying. ");
                    }
                } else {
                    String theTask = findTask[1].split(" /")[0];
                    actualTask = theTask;
                }
            }
            catch(DukeException e) {
                System.out.println(e);
            }

            if (!error) {
                if (type.equals("todo")) {

                    ToDos todo = new ToDos(actualTask);
                    taskList.add(todo);
                    ui.todoMessage(todo, taskList.size());

                } else if (type.equals("event")) {
                    String[] splitStr2 = input.split("/at");
                    Events event = new Events(actualTask, splitStr2[1]);

                    taskList.add(event);
                    ui.eventMessage(event, taskList.size());

                } else if (type.equals("deadline")) {
                    String[] splitStr2 = input.split("/by ");
                    Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);

                    taskList.add(deadline);
                    ui.deadlineMessage(deadline, taskList.size());

                } else {
                    try {
                        throw new DukeException("LUNA has consulted the Moon Goddess and even she has no idea what you're saying. ");
                    }
                    catch(DukeException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}

