package duke;

import java.util.Scanner;

/**
 * Ui class allows Duke to interact/print messages
 * to the user when changes are made to the user's task list
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class Ui {
  /**
   * This method prints out the greeting message
   * when the user starts the Duke program
   */
  public void greet(){
    System.out.print("Hello I'm duke.Duke\nWhat can I do for you?\n");
  }

  /**
   * This method prints out the quitting message
   * when the user exits the Duke program
   */
  public void quit(){
    System.out.println("Bye. Hope to see you again soon!");
  }

  /**
   * This method starts the Duke program
   * and start accepting String input from the user
   * @param sc Scanner that scans the user's String input
   * @param storage Storage to load and save the user's todo task list
   * @param taskList TaskList to update the user's task list
   */
  public void start(Scanner sc, Storage storage, TaskList taskList){
    while (true) {
      String command = sc.nextLine();
      if(command.equals("bye")) {
        break;
      }
      Parser.parseCommand(command,taskList,this);
      storage.save(taskList.getList());
    }
  }

  /**
   * This method lists out the tasks
   * in the user's task list
   * @param tasks List of tasks to be printed out
   */
  public void listOutTasks(TaskList tasks){
    tasks.printList();
  }

  /**
   * This method marks the task and
   * prints out a message
   * @param taskToMark The task from the list to be marked
   */
  public void markTask(Task taskToMark) {
    taskToMark.mark();
    System.out.println("Nice! I've marked this task as done:\n  " + taskToMark);
  }

  /**
   * This method unmarks the task and
   * prints out a message
   * @param taskToMark The task from the list to be unmarked
   */
  public void unmarkTask(Task taskToMark) {
    taskToMark.unmark();
    System.out.println("OK, I've marked this task as not done yet:\n  " + taskToMark);
  }

  /**
   * This method adds the new task to
   * the user's task list and prints out
   * a message
   * @param taskList TaskList of the user
   * @param taskToAdd The new task to be added to user's task list
   */
  public void addTask(TaskList taskList,Task taskToAdd) {
    taskList.addTask(taskToAdd);
    System.out.println("Got it. I've added this task:\n  " + taskToAdd + "\nNow you have "
            + taskList.getSize() + " tasks in the list.");
  }

  /**
   * This method deletes the task
   * from the user's task list and prints
   * out a message
   * @param taskList TaskList of the user
   * @param taskToDelete The task from the user's task list to be deleted
   */
  public void deleteTask(TaskList taskList, Task taskToDelete) {
    taskList.deleteTask(taskToDelete);
    System.out.println("Noted. I've removed this task:\n  " + taskToDelete + "\nNow you have "
                  + taskList.getSize() + " tasks in the list");
  }
}
