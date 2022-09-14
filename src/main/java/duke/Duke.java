package duke;

import java.io.IOException;


/**
 * Main class.
 *
 * @author Safwan A0235287X
 */
public class Duke {

    private Storage storage;
    private String filePath = "data/duke.txt";
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Duke main class.
     */
    public Duke () {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Method that generates a response to the input from the user.
     *
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    String getResponse(String input) throws DukeException, IOException {
        if (input.equals("hi")) {
            String response = "Ayo wagwan, chat to me blud.";
            return response;
        }

        if (input.equals("bye")) {
            String response = "See you later fam. Ciao!";
            return response;
        }

        String[] inputByWords = input.split(" ");
        String firstWord = inputByWords[0];

        if (input.equals("list")) {
            return taskList.list();

        } else if (firstWord.equals("mark")) {
            Integer indexOfTask = Integer.parseInt(inputByWords[1]);
            return taskList.mark(indexOfTask);

        } else if (firstWord.equals("unmark")) {
            Integer indexOfTask = Integer.parseInt(inputByWords[1]);
            return taskList.unmark(indexOfTask);

        } else if (firstWord.equals("todo")) {
            return getStringForTodo(input);

        } else if (firstWord.equals("deadline")) {
            return getStringForDeadline(input);

        } else if (firstWord.equals("event")) {
            return getStringForEvent(input);

        } else if (firstWord.equals("delete")) {
            Integer indexOfTask = Integer.parseInt(inputByWords[1]);
            return taskList.delete(indexOfTask);

        } else if (firstWord.equals("find")) {
            String content = input.replace("find", "");
            return taskList.find(content);

        } else if (firstWord.equals("help")) {
            String response = "";

            response += "Refer to this link:\n" +
                    "https://mohamedsaf1.github.io/ip/";

            return response;

        } else {
            String response = "Oops, sorry I don't know what you are talking about :(\n" +
                    "I require a specific set of commands, type 'help' to know more!";
            return response;
        }
    }

    /**
     * Method to get response for Todo task inputs.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    private String getStringForTodo(String input) throws DukeException, IOException {
        if (input.endsWith("todo")) {
            throw new DukeException("Oops, the description of todo cannot be empty!");
        }

        String todoTask = input.replaceAll("todo ", "");
        return taskList.todo(todoTask);
    }

    /**
     * Method to get response for Deadline task inputs.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    private String getStringForDeadline(String input) throws DukeException, IOException {
        if (input.endsWith("deadline")) {
            throw new DukeException("Ooops, the description of deadline cannot be empty!");
        }

        String[] inputSplit = input.split("/by");
        String deadlineTask = inputSplit[0].replaceAll("deadline ", "");
        String deadlineBy = inputSplit[1];
        return taskList.deadline(deadlineTask, deadlineBy);
    }

    /**
     * Method to get response for Event task inputs.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    private String getStringForEvent(String input) throws DukeException, IOException {
        if (input.endsWith("event")) {
            throw new DukeException("Ooops, the description of event cannot be empty!");
        }

        String[] inputSplit = input.split("/at");
        String eventTask = inputSplit[0].replaceAll("event ", "");
        String eventAt = inputSplit[1];
        return taskList.event(eventTask, eventAt);
    }

}
