package parser;

import duke.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import task.TaskList;

public class Parser {

    protected TaskList taskList;

    protected ArrayList<Task> listOfActions;

    protected Boolean isAction = false;

    private Boolean isErreneous = true;

    private String type = "error";

    /**
     * Creates a new Parser.
     * @param list The TaskList to be read to.
     * @param str The userInput to be interpreted.
     * @throws DukeException
     */

    public Parser(TaskList list, String str) throws DukeException {
        String input[] = str.split(" ");
        this.taskList = list;
        this.listOfActions = this.taskList.getTaskList();
        if (input.length == 1) {
            if (input[0].equals("bye")) {
                this.type = "bye";
                bye(listOfActions, Path.of("data/duke.txt").toFile());
                this.isErreneous = false;
            } else if (input[0].equals("list")) {
                this.type = "list";
                this.isErreneous = false;
            }
        } else if (input[0].equals("todo")) {
            if (input.length > 1) {
                this.type = "todo";
                isAction = true;
                list.add(new Todo(str.substring(5)));
                this.isErreneous = false;
            }
        } else if (input[0].equals("deadline")) {
            if (input.length > 5) {
                int pos = str.indexOf("/by") + 1;
                this.type = "deadline";
                isAction = true;
                list.add(new Deadline(str.substring(9, pos - 1), str.substring(pos + 3)));
                this.isErreneous = false;
            }
        } else if (input[0].equals("event")) {
            if (input.length > 4) {
                int pos = str.indexOf("/") + 1;
                this.type = "event";
                isAction = true;
                list.add(new Event(str.substring(6, pos - 1), str.substring(pos + 3)));
                this.isErreneous = false;
            }

        } else if (input[0].equals("mark")) {
            if (input.length == 2) {
                try {
                    this.type = "mark";
                    list.getTaskList().get(Integer.parseInt(input[1]) - 1).mark();
                    System.out.println("----------------------\n" + "Congrats on completing :)\n" +
                            listOfActions.get(Integer.parseInt(input[1]) - 1) + "\n----------------------\n");
                    this.isErreneous = false;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("");
                }
            }
        } else if (input[0].equals("unmark")) {
            if (input.length == 2) {
                try {
                    this.type = "unmark";
                    list.getTaskList().get(Integer.parseInt(input[1]) - 1).unMark();
                    System.out.println("----------------------\n" + "One more mission ;)\n" +
                            listOfActions.get(Integer.parseInt(input[1]) - 1) + "\n----------------------\n");
                    this.isErreneous = false;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException(" ");
                }
            }

        } else if (input[0].equals("delete")) {
            if (input.length == 2) {
                try {
                    int pos = Integer.parseInt(input[1]) - 1;
                    this.type = "delete";
                    list.delete(pos);
                    this.isErreneous = false;
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("");
                }
            }
        } else if (input[0].equals("find")) {
            String out = findTasks(input[1]);
            this.type = "find";
            this.isErreneous = false;
            if (out.isBlank()) {
                System.out.println("----------------------\n" + "Oops nothing fits the description :(\n"
                        + "----------------------\n");
            } else {
                System.out.println("----------------------\n" + out + "----------------------\n");
            }
        }


        if (this.type.equals("error")) {
            this.isErreneous = true;
        }
    }

    /**
     * Gets the type of userInput.
     * @return The String representation of the type of userInput.
     */

    public String getType() {
        return this.type;
    }

    /**
     * Gets the Boolean value of whether the input is valid.
     * @return The Boolean value of the input if valid.
     */
    public Boolean isErreneous() {
        return this.isErreneous;
    }

    /**
     * Gets the Boolean value if userInput is a Task.
     * @return The Boolean value if userInput is a Task.
     */
    public Boolean getIsAction() {
        return this.isAction;
    }

    private void bye(ArrayList<Task> listOfActions, File file) {
        try {
            FileWriter writer = new FileWriter(file.getPath());
            for (Task t : listOfActions) {
                writer.write(t.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops");
        }
    }

    private String findTasks(String variable) {
        String output = "";
        int counter = 1;
        ArrayList<Task> listOfTasks = taskList.getTaskList();
        for (int i = 0; i < taskList.taskListSize(); i++) {
            if(listOfTasks.get(i).hasWord(variable)) {
                output = output + String.format("%d. ",counter) + listOfTasks.get(i) + "\n";
                counter++;
            }
        }
        return output;
    }



}


