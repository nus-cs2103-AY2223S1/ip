package duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {
    private static final String LOAD_STORAGE_SUCCESSFUL = "Load Storage is Successful";
    private static final String LOAD_STORAGE_UNSUCCESSFUL = "Load Storage is NOT Successful";
    private static final String taskDataPath = "data";
    private static final String taskDataFileName = "duke.txt";
    private static final String memoryDataFileName = "memory.txt";
    private static UI UI = new UI();
    private static Parser parser = new Parser();
    private static Storage storage = new Storage(taskDataPath, taskDataFileName, memoryDataFileName);
    private TaskList taskList;

    private HashMap<String, String> memory;


    Duke() {
        taskList = new TaskList();
        memory = new HashMap<>();
        loadStorage(taskList, memory);
        assertSuccessfulLoadStorage();
    }

    private String loadStorage(TaskList taskList, HashMap<String, String> memory) {
        try {
            storage.readTaskAndMemoryData(taskList, memory);
            return LOAD_STORAGE_SUCCESSFUL;
        } catch (DukeException e) {
            return LOAD_STORAGE_UNSUCCESSFUL;
        }
    }

    private void assertSuccessfulLoadStorage() {
        assert loadStorage(taskList, memory) == LOAD_STORAGE_SUCCESSFUL : LOAD_STORAGE_UNSUCCESSFUL;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";

        if (input.equals("list" ) || input.equals("list\n")) {
            response = taskList.printTaskList();
        } else if (input.equals("bye") || input.equals("bye\n")) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1500);
            response = UI.GOODBYE;
            try {
                storage.updateTaskAndMemoryData(taskList, memory);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else {
            response = processComplexInput(input);
        }

        return response;
    }

    private String processComplexInput(String input) {
        String response = "";

        if (memory.containsKey(input)) {
            return memory.get(input);
        }

        try {
            String[] commands = input.split(" ");

            if (commands.length < 2) {
                throw new DukeException(UI.DO_NOT_UNDERSTAND_COMMAND);
            }

            if (commands[0].equals("mark") || commands[0].equals("unmark") || commands[0].equals("delete")) {
                int index = Integer.parseInt(commands[1]) - 1;

                if (commands[0].equals("mark")) {
                    response = taskList.markTaskAsDone(index);
                } else if (commands[0].equals("unmark")) {
                    response = taskList.markTaskAsNotDone(index);
                } else if (commands[0].equals("delete")) {
                    response = taskList.deleteTask(index);
                } else {
                    throw new DukeException(UI.DO_NOT_UNDERSTAND_COMMAND);
                }
            } else if (commands[0].equals("find")) {
                String description = commands[1];
                response = taskList.find(description);
            } else if (commands[0].equals("teach")) {
                input = input.substring(6);
                commands = input.split("/");

                if (commands.length < 2) {
                    throw new DukeException(UI.DO_NOT_UNDERSTAND_COMMAND);
                }

                String question = commands[0];
                String answer = commands[1];

                memory.put(question, answer);
                response = UI.COMMAND_MEMORIZED;
            } else {
                Task newTask; 
                if (commands[0].equals("todo")) {
                    newTask = parser.generateToDoFromInput(input);
                } else if (commands[0].equals("deadline")) {
                    newTask = parser.generateDeadlineFromInput(input);
                } else if (commands[0].equals("event")) {
                    newTask = parser.generateEventFromInput(input);
                } else {
                    throw new DukeException(UI.DO_NOT_UNDERSTAND_COMMAND);
                }
                response = taskList.add(newTask);
            }
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }
}
