package Rabbit;

import Rabbit.util.Parser;
import Rabbit.util.TaskList;
import Rabbit.util.Storage;
import Rabbit.RabbitException.RabbitException;
import Rabbit.RabbitException.ImportDataException;
import Rabbit.RabbitException.InvalidInputException;


/**
 * Rabbit is a short-tempered, annoyed bot that puts in her 30% efforts
 *  to help you solve some simple problems as her part-time jpb.
 *
 * @author Jiang Zhimeng
 */
public class Rabbit {
    private Storage storage;
    private TaskList taskList;

    public final String GREET = "Yo...nice to meet you. This is rabbit...\n"
            + "Ughhhhh I hate this job.\n"
            + "You can input stuff that you want me to write on this grandma-aged notebook.\n"
            + "Type 'instructions' to check what you can do."
            + "Actually why not just do me a favour? Close this window and free both of us.\n";

    public final String[] INSTRUCTIONS =  {"1. Type the type of a task followed by its content and time to add it into the list.\n"
            + "   There are three types: todo, deadline and event.\n"
            + "   - To add todo, type 'todo the content' such as 'todo do homework'.\n"
            + "   - To add deadline, type 'deadline the content /year-month-day-time' "
            + "such as 'deadline do homework /2022-08-22-1800'.\n"
            + "   - To add event, type 'event the content /year-month-day-time' "
            + "such as 'deadline do homework /2022-08-22-1800'.\n",
            "2. Type 'list' then I'll show all the existing lines to you.\n"
            + "3. Type 'mark + the index of an existing task' to marks it as done. Like 'mark 1'.\n"
            + "4. Type 'unmark + the index of an existing task' to unmark a task.\n"
            + "5. Type 'delete + the index of an existing task' to delete it.\n"
            + "6. Type 'find + a keyword' to find tasks with the keyword.\n",
            "In case you have nothing better to do, you can also type 'change' to change my look."};
    private final String BYE = "Thanks a lot. I'm gonna have some carrot tea later. See you...\n"
            + "Ermmm....why are you still here? Just close the window.\n";

    /**
     * Constructor of Rabbit.
     */
    public Rabbit() throws ImportDataException {
        this.taskList = new TaskList();
        try {
            this.storage = new Storage(this.taskList);
        } catch (ImportDataException e) {
            throw e;
        }
    }

    /**
     * Returns a string as Rabbit's response
     * when the user keys in a command.
     *
     * @param input the user's command
     * @return Rabbit's response.
     */
    public String getResponse(String input) {
        while (true) {
            if (input.equals("bye")) {
                return BYE;
            }
            // the function that the input is calling
            String function = input.substring(0, Parser.parseFunction(input));
            try {
                String content = "";
                switch (function) {
                case "list":
                    content = this.taskList.list();
                    return "Here are the tasks:\n" + content;
                case "find ":
                    content = this.taskList.find(input);
                    return "Here are the matching tasks:\n" + content;
                case "mark ":
                    content = this.taskList.mark(input);
                    this.storage.exportData(this.taskList);
                    return "Okay...task: " + content + " is marked as done.\n";
                case "unmark ":
                    content = this.taskList.unmark(input);
                    this.storage.exportData(this.taskList);
                    return "Okay...task: " + content + " is unmarked as done.\n";
                case "todo ":
                    content = this.taskList.addToList(TaskList.TaskType.TODO, input);
                    this.storage.exportData(this.taskList);
                    return "Okay...noted.\n" + content + "...Huh? Hope you can remember it.\n";
                case "deadline ":
                    content = this.taskList.addToList(TaskList.TaskType.DEADLINE, input);
                    this.storage.exportData(this.taskList);
                    return "Okay...noted.\n" + content + "...Huh? Hope you can remember it.\n";
                case "event ":
                    content = this.taskList.addToList(TaskList.TaskType.EVENT, input);
                    this.storage.exportData(this.taskList);
                    return "Okay...noted.\n" + content + "...Huh? Hope you can remember it.\n";
                case "delete ":
                    content = this.taskList.delete(input);
                    this.storage.exportData(this.taskList);
                    return "Okay...task: " + content + " is deleted.\n";
                case "change":
                    return "Fine...Hold up.";
                default:
                    // the user keyed in an invalid input
                    throw new InvalidInputException();
                }

            } catch (RabbitException e) {
                return e.toString();
            }
        }
    }
}
