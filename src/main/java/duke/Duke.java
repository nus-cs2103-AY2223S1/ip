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
        try {
            ui = new Ui();
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that generates a response to the input from the user.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    String getResponse(String input) throws DukeException, IOException {
        if (input.equals("hi") || input.equals("bye")) {
            return getStringForGreetings(input);
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
            return taskList.find(input);
        } else if (firstWord.equals("help")) {
            return getStringForHelp();
        } else {
            String response = "Oops, sorry I don't know what you are talking about :(\n" +
                    "I require a specific set of commands, type 'help' to know more!";
            return response;
        }
    }

    /**
     * Method to get response for greetings.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    private String getStringForGreetings(String input) throws DukeException, IOException {
        if (input.equals("hi")) {
            String response = "Ayo wagwan, chat to me blud.";
            return response;
        }
        if (input.equals("bye")) {
            String response = "See you later fam. Ciao!";
            return response;
        }
        throw new DukeException("Invalid input.");
    }

    /**
     * Method to get response for Todo task inputs.
     * @param input the text given from the user.
     * @return A string of response from Duke.
     * @throws DukeException
     * @throws IOException
     */
    private String getStringForTodo(String input) throws DukeException, IOException {
        String todoConcat = input.replace("todo", "");
        if (todoConcat.isBlank()) {
            throw new DukeException("Oops, the description of todo cannot be empty!");
        }

        String todoTask = input.replaceAll(" ", "");

        if (todoTask.isBlank()) {
            throw new DukeException("Oops, the description of todo cannot be empty!");
        }
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
        String deadlineConcat = input.replace("deadline", "");
        if (deadlineConcat.isBlank()) {
            throw new DukeException("Oops, the description of deadline cannot be empty!");
        }

        String[] inputSplit = input.split("/by");
        String deadlineTask = inputSplit[0].replaceAll(" ", "");
        String deadlineBy = inputSplit[1];
        if (deadlineBy.isBlank()) {
            throw new DukeException("Oops, the description of deadline cannot be empty!");
        }
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
        String eventConcat = input.replace("event", "");
        if (eventConcat.isBlank()) {
            throw new DukeException("Oops, the description of event cannot be empty!");
        }

        String[] inputSplit = input.split("/at");
        String eventTask = inputSplit[0].replaceAll(" ", "");
        String eventAt = inputSplit[1];
        if (eventAt.isBlank()) {
            throw new DukeException("Oops, the description of event cannot be empty!");
        }
        return taskList.event(eventTask, eventAt);
    }

    /**
     * Method to get response for help input.
     * @return A string of response from Duke.
     */
    private String getStringForHelp() {
        String response = "Refer to this link:\n";
        String url = "https://mohamedsaf1.github.io/ip/";
        response += url;
        return response;
    }
}
