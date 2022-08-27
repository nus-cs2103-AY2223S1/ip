package components;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents TaskList, which keeps tracks of all the user's inputted tasks.
 */
public class TaskList {
  private ArrayList<Task> ls;
  private Storage storage;
  private ArrayList<String> tracker = new ArrayList<>();

  public TaskList(ArrayList<Task> tasks) {
    this.ls = tasks;
  }

  public TaskList() {
    this.ls = new ArrayList<>();
  }

  /**
   * Connects storage with TaskList.
   *
   * @param storage Storage to be connected with
   */
  public void setStorage(Storage storage) {
    this.storage = storage;
  }

  /**
   * shows the list of tasks the user has inputted.
   */
  public void showTasks() {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < ls.size(); i++) {
      System.out.println(i + 1 + "." + " " + ls.get(i).toString());
    }
  }

  /**
   * add tasks into an ArrayList.
   *
   * @param task task to be added into ArrayList.
   */
  public void add(Task task) {
    System.out.println("Got it. I've added this task:");
    ls.add(task);
    System.out.println(task.toString());
    String file2 = "duke.txt";
    try {
      storage.writeToFile(file2, task.toString() + System.lineSeparator());
    } catch (IOException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
  }

  /**
   * remove tasks from ArrayList.
   *
   * @param task index of task to be removed from ArrayList.
   */
  public void remove(int task) {
    System.out.println("Noted. I've removed this task:");
    System.out.println(ls.get(task).toString());
    ls.remove(task);
    String file2 = "duke.txt";
    try {
      storage.deleteFromFile(file2, ls);
    } catch (IOException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
    System.out.println("Now you have" + " " + ls.size() + " " + "tasks in the list.");
  }

  /**
   * mark task to completed or not.
   *
   * @param index  index of task to be marked.
   * @param status mark task to be completed with true or not completed with false.
   */
  public void setTaskStatus(int index, boolean status) {
    ls.get(index).setStatus(false);
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.println(ls.get(index).toString());
  }

  public void findLine(String s) throws DukeException {
    boolean stop = false;
    for (int i = 0; i < ls.size(); i++) {
      if (ls.get(i).toString().contains(s)) {
        this.tracker.add(ls.get(i).toString());
        stop = true;
      }
    }
    if (stop) {
      System.out.println("Here are the matching tasks in your list:");
      for (int i = 0; i < ls.size(); i++) {
        System.out.println(i + 1 + "." + " " + tracker.get(i));
      }
    } else {
      throw new DukeException("No matching tasks");
    }
  }


}
