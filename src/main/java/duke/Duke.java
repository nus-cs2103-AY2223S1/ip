package duke;

import java.time.DateTimeException;
import java.util.ArrayList;

/**
 * Main DukeBot class.
 */
public class Duke {

    private Storage storage;
    private TaskList tList;
    private Ui ui;
    private Boolean isActive;

    /**
     * Constructor for a Duke object.
     *
     * @param dirPath Path of directory that storage use to locate save files.
     * @param dataName Name of the save file in directory.
     */
    public Duke(String dirPath, String dataName) {
        this.storage = new Storage("data/", "save.txt");
        this.ui = new Ui();
        this.tList = new TaskList();
        this.isActive = true;

        storage.loadFile(tList);
    }

    /**
     * Simple method that exits the Duke's run method.
     */
    public void exit() {
        this.isActive = false;
    }

    /**
     * Method that check if the bot is active.
     *
     * @return Status of current bot if it is active.
     */
    public boolean isActive() {
        return this.isActive;
    }


    /**
     * Method that processes a user's input after parsed
     * and does the corresponding actions.
     *
     * @param s String that the user inputs.
     * @return The string to be displayed by bot.
     */
    public String getResponse(String s) {
        try {
            String[] parsed = Parser.parseInput(s);
            assert parsed.length == 3 : "Parser error occurred.";

            if (parsed[0].equals("bye")) {
                return getByeResponse();

            } else if (parsed[0].equals("list")) {
                return getListResponse();

            } else if (parsed[0].equals("mark")) {
                return getMarkResponse(parseInteger(parsed[1]));

            } else if (parsed[0].equals("unmark")) {
                return getUnmarkResponse(parseInteger(parsed[1]));

            } else if (parsed[0].equals("delete")) {
                return getDeleteResponse(parseInteger(parsed[1]));

            } else if (parsed[0].equals("find")) {
                return getFindResponse(parsed[1]);

            } else if (parsed[0].equals("todo")) {
                return getTodoResponse(parsed[1]);

            } else if (parsed[0].equals("deadline")) {
                return getDeadlineResponse(parsed[1], parsed[2]);

            } else if (s.indexOf("event") == 0) {
                return getEventResponse(parsed[1], parsed[2]);

            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }

        } catch (EmptyDescriptionException | InvalidTaskException
                 | InvalidFormatException e) {
            return ui.printException(e);
        } catch (DateTimeException e) {
            return ui.printErrorMessage("Error! Date format incorrect (YYYY-MM-DD HH:mm)");
        } catch (IndexOutOfBoundsException e) {
            return ui.printErrorMessage("Invalid index access detected!");
        } finally {
            assert tList != null : "TaskList has to be present";
            storage.saveFile(this.tList);
        }

    }

    private int parseInteger(String s) {
        return Integer.parseInt(s) - 1;
    }

    private String getByeResponse() {
        exit();
        return ui.printFarewell();
    }

    private String getListResponse() {
        return ui.printList(tList);
    }

    private String getMarkResponse(int index) {
        tList.getTask(index).markDone();
        return ui.printMarkTaskDone(tList.getTask(index));
    }

    private String getUnmarkResponse(int index) {
        tList.getTask(index).markUndone();
        return ui.printMarkTestUndone(tList.getTask(index));
    }

    private String getDeleteResponse(int index) {
        Task temp = tList.getTask(index);
        tList.removeTask(index);
        return ui.printDelete(temp, tList);

    }

    private String getFindResponse(String target) {
        ArrayList<Task> temp;
        temp = tList.find(target);
        return ui.printFind(temp);
    }

    private String getTodoResponse(String description) {
        Todo a = new Todo(description);
        tList.addTask(a);
        return ui.printTaskAdded(a, tList);
    }

    private String getDeadlineResponse(String description, String by) {
        Deadline d = new Deadline(description, by);
        tList.addTask(d);
        return ui.printTaskAdded(d, tList);
    }

    private String getEventResponse(String description, String at) {
        Event e = new Event(description, at);
        tList.addTask(e);
        return ui.printTaskAdded(e, tList);
    }

}

