package duke;


import java.io.IOException;
import java.util.Scanner;

/**
 * A class that deals with the interaction of the user.
 *
 * @author Safwan A0235287X
 */
public class Parser {

    private TaskList taskList;

    /**
     * Constructor to create Parser object.
     * @param taskList the input list for the storage of data.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Method to read and process input given by the user.
     * @throws DukeException
     * @throws IOException
     */
    public void readInput() throws DukeException, IOException {
        while (true) {
            
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon.");
                break;
            }

            String[] inputByWords = input.split(" ");
            String firstWord = inputByWords[0];

            if (input.equals("list")) {
                taskList.list();

            } else if (firstWord.equals("mark")) {
                Integer indexOfTask = Integer.parseInt(inputByWords[1]);
                taskList.mark(indexOfTask);

            } else if (firstWord.equals("unmark")) {
                Integer indexOfTask = Integer.parseInt(inputByWords[1]);
                taskList.unmark(indexOfTask);

            } else if (firstWord.equals("todo")) {
                if (input.endsWith("todo")) {
                    throw new DukeException("Ooops, the description of todo cannot be empty!");
                }

                String todoTask = input.replaceAll("todo ", "");
                taskList.todo(todoTask);

            } else if (firstWord.equals("deadline")) {
                if (input.endsWith("deadline")) {
                    throw new DukeException("Ooops, the description of deadline cannot be empty!");
                }

                String[] inputSplit = input.split("/by");
                String deadlineTask = inputSplit[0].replaceAll("deadline", "");
                String deadlineBy = inputSplit[1];
                taskList.deadline(deadlineTask, deadlineBy);
                
            } else if (firstWord.equals("event")) {
                if (input.endsWith("event")) {
                    throw new DukeException("Ooops, the description of event cannot be empty!");
                }

                String[] inputSplit = input.split("/at");
                String eventTask = inputSplit[0].replaceAll("event", "");
                String eventBy = inputSplit[1];
                taskList.event(eventTask, eventBy);

            } else if (firstWord.equals("delete")) {
                Integer indexOfTask = Integer.parseInt(inputByWords[1]);
                taskList.delete(indexOfTask);

            } else if (firstWord.equals("find")) {
                String content = input.replace("find", "");
                taskList.find(content);

            } else {
                throw new DukeException("Oooops, sorry I don't know what you are talking about :(");
            }
        }
    }
}
