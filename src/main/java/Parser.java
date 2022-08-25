import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    private TaskList taskList;


    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parse(String cmd) throws DukeException {
        String[] temp = cmd.split(" ");
        String mainCmd = temp[0];
        String[] subCmd = Arrays.copyOfRange(temp, 1, temp.length);

        switch (mainCmd) {
            case "list" :
                this.parseList(subCmd);
                break;
            case "mark" :
                this.parseMark(subCmd);
                break;
            case "unmark" :
                this.parseUnmark(subCmd);
                break;
            case "todo" :
                this.parseTodo(subCmd);
                break;
            case "deadline" :
                this.parseDeadline(subCmd);
                break;
            case "event" :
                this.parseEvent(subCmd);
                break;
            case "delete" :
                this.parseDelete(subCmd);
                break;
            default:
                this.taskList.addTask(new Task(cmd));

                System.out.println(String.format("added: %s\n", cmd));

        }
    }

    public void parseList(String[] subCmd) throws InvalidDescriptionException {
        if (subCmd.length != 0) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.listTasks();
        }
    }

    public void parseMark(String[] subCmd) throws InvalidDescriptionException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).mark();
        }
    }

    public void parseUnmark(String[] subCmd) throws InvalidDescriptionException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.getTask(Integer.parseInt(subCmd[0]) - 1).unmark();
        }
    }

    public void parseTodo(String[] subCmd) throws EmptyDescriptionException {

        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            Todo tmpTask = new Todo(tmp);

            this.taskList.addTask(tmpTask);
        }
    }

    public void parseDeadline(String[] subCmd) throws DukeException {
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            try {
                String[] tempSplit = tmp.split(" /by ");
                LocalDate tempDate = LocalDate.parse(tempSplit[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Deadline tmpTask = new Deadline(tempSplit[0], tempDate);

                this.taskList.addTask(tmpTask);

            } catch (DateTimeParseException e) {
                throw new DukeException("Please change Date format to dd/mm/yyyy");
            }
        }
    }

    public void parseEvent(String[] subCmd) throws EmptyDescriptionException{
        String tmp = String.join(" ", subCmd);
        if (tmp.equals("")) {
            throw new EmptyDescriptionException();
        } else {
            String[] tempSplit = tmp.split(" /at ");
            Event tmpTask = new Event(tempSplit[0], tempSplit[1]);

            this.taskList.addTask(tmpTask);
        }
    }

    public void parseDelete(String[] subCmd) throws InvalidDescriptionException {
        if (Integer.parseInt(subCmd[0]) <= 0 || Integer.parseInt(subCmd[0]) > this.taskList.size()) {
            throw new InvalidDescriptionException();
        } else {
            this.taskList.deleteTask(Integer.parseInt(subCmd[0]));
        }
    }
}
