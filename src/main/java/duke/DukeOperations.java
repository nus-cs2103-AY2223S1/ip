package duke;

import java.util.ArrayList;

/**
 * Represents all the operations required by the Duke application.
 */
public class DukeOperations {


    /**
     * Displays list of all tasks added.
     *
     * @param array contains arraylist of all the tasks.
     */
    public String displayList(ArrayList<Task> array) {
        if (array.size() > 0) {
            String result = "";
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i) != null) {
                    int j = i + 1;
                    result = result + j + ". " + array.get(i).toString() + "\n";
                }
            }
            return result;
        } else {
            return new DukeException("You currently do not have any tasks in your list").toString();
        }
    }

    /**
     * Returns string representation of the task we are marking to be done.
     *
     * @param array arraylist of all the tasks so far.
     * @param number integer value of the task to be marked as done.
     */
    public String markTaskAsDone(ArrayList<Task> array, int number) {
        array.get(number-1).markAsDone();
        String result = "";
        result = result + "Nice! I've marked this task as done:" + "\n";
        result = result + array.get(number-1).toString();
        Storage.clearFile();
        Storage.writeToFile(array);
        return result;
    }

     /**
      * Edits a task as not done and displays it.
      *
      * @param array arraylist of all the tasks so far.
      * @param number integer value of the task to be unmarked as not done.
      * @return String representation showing which item has been unmarked.
      */
     public String unMarkTask(ArrayList<Task>array, int number) {
         array.get(number-1).markAsNotDone();
         String result = "";
         result = result + "OK, I've marked this task as not done yet:" +  "\n" ;
         result = result + array.get(number-1).toString();
         Storage.clearFile();
         Storage.writeToFile(array);
         return result;
     }



     /**
      * Returns tasks with the word that the user is looking for.
      *
      * @param word word entered by the user.
      * @param array Arraylist of all the tasks so far
      * @return String value of all the tasks related to the word.
      */
     public String findWord(String word, ArrayList<Task> array) {
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
