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
    public void exit() {
        System.out.println("_________________________________________________________________________");
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________________________________");
    }

    /**
    * Displays list of all tasks adeed/
    * @param array contains arraylist of all the tasks
    * @param counter integer value of the number of tasks in the array so far.
    */
    public void displayList(ArrayList<Task> array, int counter) {
        System.out.println("_________________________________________________________________________");
        for (int i = 0; i < counter; i++) {
            if (array.get(i) != null) {
                int j = i + 1;
                System.out.println(j + ". " + array.get(i).toString());
            }
        }
        System.out.println("_________________________________________________________________________");
    }

    /**
    * Marks as a task as done and displays output messages.
    * @param array arraylist of all the tasks so far.
    * @param number integer value of the task to be marked as done.
    */
    public void mark(ArrayList<Task> array, int number) {
        array.get(number-1).markAsDone();
        System.out.println("_________________________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(array.get(number-1).toString());
        System.out.println("_________________________________________________________________________");
        Storage.clearFile();
        Storage.writeToFile(array,array.size());
    }

    /**
     * Unmarks as a task as done and displays output messages.
     *
     * @param array arraylist of all the tasks so far.
     * @param number integer value of the task to be unmarked as not done.
     * */
     public void unmark(ArrayList<Task>array, int number) {
         array.get(number-1).markAsNotDone();
         System.out.println("_________________________________________________________________________");
         System.out.println("OK, I've marked this task as not done yet:");
         System.out.println(array.get(number-1).toString());
         System.out.println("_________________________________________________________________________");
         Storage.clearFile();
         Storage.writeToFile(array,array.size());
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

     public void findWord(String word, ArrayList<Task> array, int counter) {
         int j = 0;
         for (int i = 0; i < counter; i++) {
             if (array.get(i) != null && array.get(i).toString().contains(word)) {
                 j++;
                 System.out.println(j + ". " + array.get(i).toString());
             }
         }

     }



}
