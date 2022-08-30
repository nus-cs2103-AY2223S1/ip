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

            if (parsed[0].equals("bye")) {
                exit();
                return ui.printFarewell();

            } else if (parsed[0].equals("list")) {
                return ui.printList(tList);

            } else if (parsed[0].equals("mark")) {
                int a = Integer.parseInt(parsed[1]) - 1;
                tList.getTask(a).markDone();

                return ui.printMarkTaskDone(tList.getTask(a));

            } else if (parsed[0].equals("unmarked")) {
                int a = Integer.parseInt(parsed[1]) - 1;
                tList.getTask(a).markUndone();

                return ui.printMarkTestUndone(tList.getTask(a));

            } else if (parsed[0].equals("delete")) {
                int a = Integer.parseInt(parsed[1]) - 1;

                Task temp = tList.getTask(a);
                tList.removeTask(a);
                return ui.printDelete(temp, tList);

            } else if (parsed[0].equals("find")) {
                ArrayList<Task> temp;
                temp = tList.find(parsed[1]);
                return ui.printFind(temp);

            } else if (parsed[0].equals("todo")) {
                Todo a = new Todo(parsed[1]);
                tList.addTask(a);
                return ui.printTaskAdded(a, tList);


            } else if (parsed[0].equals("deadline")) {
                Deadline d = new Deadline(parsed[1], parsed[2]);
                tList.addTask(d);
                return ui.printTaskAdded(d, tList);

            } else if (s.indexOf("event") == 0) {
                Event e = new Event(parsed[1], parsed[2]);
                tList.addTask(e);
                return ui.printTaskAdded(e, tList);

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
            storage.saveFile(this.tList);
        }

    }

}

