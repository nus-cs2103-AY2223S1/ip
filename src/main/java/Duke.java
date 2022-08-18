import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Duke class
 *
 * @author Khor Jun Wei
 * @version CS2103T AY22/23 Sem 1
 */
public class Duke {

    /**
     * Represents an indentation for replies.
     */
    private static final String INDENTATION = "     ";

    /**
     * Represents an extra indentation for replies.
     */
    private static final String EXTRA_INDENTATION = "  ";

    /**
     * Represents the work types.
     */
    enum Work_type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a formatted reply message.
     * @param message message to be used
     * @return string of well-formatted message
     */
    private static String reply(String message) {
        return "    ____________________________________________________________\n" +
                INDENTATION + message + "\n" +
                "    ____________________________________________________________";
    }

    /**
     * Crafts a list of tasks to be completed.
     * @param array an array of tasks
     * @param bArray an array of booleans describing whether a task is completed
     * @param workTypeArray an array of work_types
     * @return string of the list
     */
    private static String craftList(ArrayList<String> array, ArrayList<Boolean> bArray, ArrayList<Work_type> workTypeArray) {
        int length = array.size();
        String result = "Here are the task(s) in your list:";
        for (int x = 0; x < length; x++) {
            if (array.get(x) == null) {
                break;
            } else {
                result += "\n" + INDENTATION + (x + 1) + "." + workTypeBox(workTypeArray.get(x)) + checkBox(bArray.get(x)) + " " + array.get(x);
            }
        }
        return result;
    }

    /**
     * Crafts a message when user inputs a todo, deadline, or event.
     * @param work_type type of work
     * @param bool whether the work is completed
     * @param todo name of work
     * @param work_number order of work in the list
     * @return string of well-formatted message for the task
     */
    private static String craftTDEMessage(Work_type work_type, boolean bool, String todo, int work_number) {
        return "Got it. I've added this task:\n" +
                INDENTATION + EXTRA_INDENTATION + workTypeBox(work_type) + checkBox(bool) + " " + todo + "\n" +
                INDENTATION + "Now you have " + work_number + (work_number < 2 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Creates a checkbox for the task.
     * @param bool whether the task is completed
     * @return string containing the well-formatted checkbox
     */
    private static String checkBox(boolean bool) {
        if (bool) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Creates a checkbox for the work type.
     * @param type type of work
     * @return string containing the well-formatted type box
     */
    private static String workTypeBox(Work_type type) {
        if (type == Work_type.TODO) {
            return "[T]";
        } else if (type == Work_type.DEADLINE) {
            return "[D]";
        } else { // Has to be Work_type.EVENT
            return "[E]";
        }
    }

    /**
     * Main method.
     * @param args given arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(reply("Hello! I'm Duke\n" +
                "     What can I do for you?"));
        boolean conversation = true;
        ArrayList<String> work = new ArrayList<>(100);
        ArrayList<Boolean> workCompleted = new ArrayList<>(100);
        ArrayList<Work_type> workType = new ArrayList<>(100);
        int workCounter = 0;
        while (conversation) {
            try {
                String response = scan.nextLine();
                String[] splitResponse = response.split(" ");
                String keyword = splitResponse[0];
                if (splitResponse.length == 2 && (keyword.equals("mark") || keyword.equals("unmark"))) {
                    try {
                        int number = Integer.parseInt(splitResponse[1]) - 1;
                        if (splitResponse[0].equals("mark")) {
                            workCompleted.set(number, true);
                            System.out.println(reply("Nice! I've marked this task as done:\n" +
                                    INDENTATION + EXTRA_INDENTATION + workTypeBox(workType.get(number)) + checkBox(true) + " " + work.get(number)));
                        } else {
                            workCompleted.set(number, false);
                            System.out.println(reply("OK, I've marked this task as not done yet:\n" +
                                    INDENTATION + EXTRA_INDENTATION + workTypeBox(workType.get(number)) + checkBox(false) + " " + work.get(number)));
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("non integer input when marking");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("index out of bounds");
                    }
                } else if (splitResponse.length == 2 && keyword.equals("delete")) {
                    try {
                        int location = Integer.parseInt(splitResponse[1]) - 1;
                        Work_type type = workType.get(location);
                        boolean completed = workCompleted.get(location);
                        String task = work.get(location);
                        workType.remove(location);
                        workCompleted.remove(location);
                        work.remove(location);
                        workCounter--;
                        System.out.println(reply("Noted. I've removed the task:\n" +
                                INDENTATION + EXTRA_INDENTATION + workTypeBox(type) + checkBox(completed) + " " + task + "\n" +
                                INDENTATION + "Now you have " + workCounter + (workCounter < 2 ? " task" : " tasks") + " in the list."));
                    } catch (NumberFormatException e) {
                        throw new DukeException("non integer input when deleting");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("index out of bounds");
                    }
                } else if (keyword.equals("todo")) {
                    if (splitResponse.length == 1) {
                        throw new DukeException("todo");
                    } else {
                        String todo = response.substring(5);
                        work.add(todo);
                        workType.add(Work_type.TODO);
                        workCompleted.add(false);
                        workCounter++;
                        System.out.println(reply(craftTDEMessage(Work_type.TODO, false, todo, workCounter)));
                    }
                } else if (keyword.equals("deadline")) {
                    if (splitResponse.length == 1) {
                        throw new DukeException("deadline");
                    } else {
                        String[] newSplit = response.split(" /by ");
                        if (newSplit.length == 1) {
                            throw new DukeException("deadline format");
                        } else {
                            String todo = newSplit[0].substring(9);
                            String by = newSplit[1];
                            String newTodo = todo + " (by: " + by + ")";
                            work.add(newTodo);
                            workType.add(Work_type.DEADLINE);
                            workCompleted.add(false);
                            workCounter++;
                            System.out.println(reply(craftTDEMessage(Work_type.DEADLINE, false, newTodo, workCounter)));
                        }
                    }
                } else if (keyword.equals("event")) {
                    if (splitResponse.length == 1) {
                        throw new DukeException("event");
                    } else {
                        String[] newSplit = response.split(" /at ");
                        if (newSplit.length == 1) {
                            throw new DukeException("event format");
                        } else {
                            String todo = newSplit[0].substring(6);
                            String at = newSplit[1];
                            String newTodo = todo + " (at: " + at + ")";
                            work.add(newTodo);
                            workType.add(Work_type.EVENT);
                            workCompleted.add(false);
                            workCounter++;
                            System.out.println(reply(craftTDEMessage(Work_type.EVENT, false, newTodo, workCounter)));
                        }
                    }
                } else if (response.equals("bye")) {
                    System.out.println(reply("Bye. Hope to see you again soon!"));
                    conversation = false;
                } else if (response.equals("list")) {
                    System.out.println(reply(craftList(work, workCompleted, workType)));
                } else {
                    throw new DukeException("unknown");
                }
            } catch (DukeException e) {
                System.out.println(reply(e.toString()));
            }
        }
    }
}
