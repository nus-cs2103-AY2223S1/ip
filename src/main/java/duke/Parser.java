package duke;

import duke.task.Event;
import duke.task.TaskList;
import duke.expenses.Expense;
import duke.expenses.ExpenseList;
import duke.task.Deadline;
import duke.task.Todo;
import duke.exception.InvalidInputException;

/**
 * The Parser class parses inputs for the Duke program. The Parser takes in inputs from the user and executes
 * the requests by calling methods of the TaskList it holds.
 */
public class Parser {
    
    /**
     * Allows for the Parser to call methods of the TaskList.
     */
    private final TaskList taskList;

    private final ExpenseList expenseList;

    /**
     * Constructor of class Parser.
     * @param taskList Takes in a TaskList for the Parser to call methods for.
     */
    public Parser(TaskList taskList, ExpenseList expenseList) {
        this.taskList = taskList;
        this.expenseList = expenseList;
    }

    /**
     * Parses String inputs from the user and determines the appropriate method of TaskList to call upon to
     * execute user requests. User instructions available include listing, marking and unmarking tasks, 
     * adding and deleting tasks, and finding tasks.
     * @param request Takes in user requests as Strings.
     * @return Returns a boolean to let the Duke program know whether the user has given instructions to terminate.
     */
    public String parse(String request) throws InvalidInputException {
        if (request.equals("bye")) { // 1. Terminates Greg
            System.exit(1);
            return "Goodbye, your tasks have been saved! See you soon!";
        }

        else if (request.equals("list")) { // 2. Lists out tasks
            return taskList.printList();
        } 

        else if (request.startsWith("mark")) { // 3. Mark task as done
            int taskNumber = Integer.parseInt(request.split(" ")[1]) - 1;
            try {
                return taskList.markTask(taskNumber);
            } catch (NumberFormatException e) {
                return "Please enter an appropriate number!";
            }
        }

        else if (request.startsWith("unmark")) { // 4. Mark task as undone
            int taskNumber = Integer.parseInt(request.split(" ")[1]) - 1;
            try {
                return taskList.unmarkTask(taskNumber);
            } catch (NumberFormatException e) {
                return "Please enter an appropriate number!";
            }
        }

        else if (request.startsWith("todo ")) { // 5a. Adding in tasks (Todo)
            String todoTask = request.replace("todo ", "");
            Todo todo = new Todo(todoTask);
            return taskList.addTask(todo);
        }

        else if (request.startsWith("deadline ")) { // 5b. Adding in tasks (Deadline)
            String[] deadlineTask = (request.replace("deadline ", "")).split(" /by ");
            Deadline deadline;
            if (deadlineTask[1].length() > 10) { // Deadline has a due time
                String[] dlDateAndTime = deadlineTask[1].split(" ");
                String time = dlDateAndTime[1].substring(0, 2) + ":" + dlDateAndTime[1].substring(2, 4);
                deadline = new Deadline(deadlineTask[0], dlDateAndTime[0], time);
            } else { // Deadline without a specific due time
                deadline = new Deadline(deadlineTask[0], deadlineTask[1]); 
            }
            return taskList.addTask(deadline);
        }

        else if (request.startsWith("event ")) { // 5c. Adding in tasks (Event)
            String[] eventTask = request.replace("event ", "").split(" /at ");
            String[] eventDateAndTime = eventTask[1].split(" ");
            try {
                String eventTime = eventDateAndTime[1].substring(0, 2) + ":" + eventDateAndTime[1].substring(2, 4);
                Event event = new Event(eventTask[0], eventDateAndTime[0], eventTime);
                return taskList.addTask(event);
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Event needs a time!";
            }
        }

        else if (request.startsWith("delete ")) { // 6. Deleting tasks
            String[] deleteTask = request.split(" ");
            int taskIndex = Integer.parseInt(deleteTask[1]);
            return taskList.deleteTask(taskIndex);
        }

        else if (request.startsWith("find ")) { // 7. Find tasks
            String matchWith = request.replace("find ", "");
            return taskList.find(matchWith);
        } 
        else if (request.startsWith("expense ")) {
            String expenseRequest = request.replace("expense ", "");
            if (expenseRequest.startsWith("/p")) { // Print expense list
                return expenseList.printList();
            }
            else if (expenseRequest.startsWith("/a ")) { // Add expense
                String[] expeStrings = expenseRequest.replace("/a ", "").split("/c ");
                try {
                    Expense newExpense = new Expense(expeStrings[0], expeStrings[1]);
                    return expenseList.addExpense(newExpense);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return "Please type in the activity followed by /c and then the costs!";
                }
            }
            else if (expenseRequest.startsWith("/d ")) { // Delete expense
                String position = expenseRequest.replace("/d ", "");
                try {
                    return expenseList.deleteExpense(Integer.parseInt(position));
                } catch (NumberFormatException e) {
                    return "Please give a position number!";
                }
            }
            else if (expenseRequest.startsWith("/f ")) { // Find expense
                String keyword = expenseRequest.replace("/f ", "");
                return expenseList.find(keyword);
            } else {
                throw new InvalidInputException();
            }
        } else if (request.equals("help")) {
            String output = "Welcome to the help section!\n"; 
            output += "Here are the available commands:\n\n";
            output += "  todo/event/deadline XXX /at/by date time\n";
            output += "  mark/unmark/delete <position>\n";
            output += "  list\n";
            output += "  expense /a XXX /c <cost>  |  /d <position>\n";  
            output += "  expense /p  |  /f <keyword>";
            return output;
        } else { // Inappropriate input
            throw new InvalidInputException();
        }
    }
}
