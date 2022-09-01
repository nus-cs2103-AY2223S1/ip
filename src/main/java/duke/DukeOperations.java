package duke;

import java.util.ArrayList;

/**
 * Class containing all the various Duke program methods.
 */
public class DukeOperations {



    /**
    * method that returns exit message.
    *
    */
    public String exit() {
        return("Bye. Hope to see you again soon!");
    }

    /**
    * Displays list of all tasks adeed/
    * @param array contains arraylist of all the tasks
    */
    public String displayList(ArrayList<Task> array) {

        String result = "";

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) != null) {
                int j = i + 1;
                result = result + j + ". " + array.get(i).toString() + "\n";
            }
        }

        return result;

    }

    /**
    * Marks as a task as done and displays output messages.
    * @param array arraylist of all the tasks so far.
    * @param number integer value of the task to be marked as done.
    */
    public String mark(ArrayList<Task> array, int number) {
        array.get(number-1).markAsDone();
        String result = "";
        result = result + "Nice! I've marked this task as done:" + "\n";
        result = result + array.get(number-1).toString();
        Storage.clearFile();
        Storage.writeToFile(array);
        return result;
    }

    /**
     * Unmarks as a task as done and displays output messages.
     *
     * @param array arraylist of all the tasks so far.
     * @param number integer value of the task to be unmarked as not done.
     * */
     public String  unmark(ArrayList<Task>array, int number) {
         array.get(number-1).markAsNotDone();
         String result = "";
         result = result + "OK, I've marked this task as not done yet:" +  "\n" ;
         result = result + array.get(number-1).toString();
         Storage.clearFile();
         Storage.writeToFile(array);
         return result;
     }

    /**
     * Method that throws exception when unexpected input entered
     *
     * @param word word entered by the user.
     * @exception DukeException exception thrown when random word input by user.
     * */
     public void randomword(String word) throws DukeException{
         throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
     }

     public String  findWord(String word, ArrayList<Task> array) {
         String result = "";
         int j = 0;
         for (int i = 0; i < array.size(); i++) {
             if (array.get(i) != null && array.get(i).toString().contains(word)) {
                 j++;
                 result = result + j + ". " + array.get(i).toString() + "\n";
             }
         }

         return result;

     }



}
