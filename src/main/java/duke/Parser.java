
package duke;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Parser {
    static ArrayList<Task> listOfTasks = new ArrayList<>(100);

    /**
     * checks what the user has input into the CLI.
     * @return return the String representation of the task that the user has asked to happen.
     */
    public static String startParse(String input) {
        DukeOperations ops = new DukeOperations();
        TaskList tl = new TaskList();
        String output = input;
        String arr[] = output.split(" ", 2);
        String firstWord = arr[0];



        if (output.equals("bye")) {
            assert firstWord.equals("bye") : "command should be bye";
                return ops.exit();

        } else if (output.equals("list")) {
            assert firstWord.equals("list") : "command should be list";
            return ops.displayList(listOfTasks);
        } else if (firstWord.equals("todo")) {
            assert firstWord.equals("todo") : "command should be todo";

            try {
                return tl.todo(listOfTasks, arr);
            } catch (DukeException e1) {
                    return(e1.toString());
            }
            } else if (firstWord.equals("deadline")) {
            String arr2[] = arr[1].split("/by ", 2);
            String arr3[] = arr2[1].split(" ", 2);
            return tl.deadline(listOfTasks, arr2[0], arr3[0], arr3[1]);
        } else if (firstWord.equals("event")) {
            String arr2[] = arr[1].split("/at ", 2);
            String arr3[] = arr2[1].split(" ", 2);
            return tl.event(listOfTasks, arr2[0], arr3[0], arr3[1]);
        } else if (firstWord.equals("mark")) {
            int num = Integer.parseInt(arr[1]);
            return ops.markTaskAsDone(listOfTasks, num);
        } else if (firstWord.equals("unmark")) {
            int num = Integer.parseInt(arr[1]);
            return ops.unMarkTask(listOfTasks, num);
        } else if (firstWord.equals("delete")) {
            int num = Integer.parseInt(arr[1]);
            return tl.delete(listOfTasks, num);
        } else if(firstWord.equals("find")) {
            String str = arr[1];
            return ops.findWord(str, listOfTasks);
        } else {
            try {
                ops.randomWordFromInput(output);
            } catch (DukeException e2) {
                return(e2.toString());
            }
        }
            return "hi";
        }


    }

