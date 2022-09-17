package components;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.String.valueOf;

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
  public String showTasks() {
    String s = "Here are the tasks in your list: \n";
    for (int i = 0; i < ls.size(); i++) {
      s += valueOf(i + 1) + "." + " " + ls.get(i).toString() + "\n";
    }
    return s;
  }

  /**
   * add tasks into an ArrayList.
   *
   * @param task task to be added into ArrayList.
   */
  public String add(Task task) {
    String s = "Got it. I've added this task: \n";
    ls.add(task);
    s += task.toString() + "\n";
    String file2 = "duke.txt";
    try {
      storage.writeToFile(file2, task.toString() + System.lineSeparator());
    } catch (IOException e) {
      return "Something went wrong: " + e.getMessage();
    }
    return s + "Now you have" + " " + valueOf(ls.size()) + " " + "tasks in the list.";
  }

  /**
   * remove tasks from ArrayList.
   *
   * @param task index of task to be removed from ArrayList.
   * @return
   */
  public String remove(int task) {
    String s = "Noted. I've removed this task: \n";
    s += ls.get(task).toString() + "\n";
    ls.remove(task);
    String file2 = "duke.txt";
    try {
      storage.deleteFromFile(file2, ls);
    } catch (IOException e) {
      return "Something went wrong: \n" + e.getMessage();
    }
    return s + "Now you have" + " " + valueOf(ls.size()) + " " + "tasks in the list.";
  }

  /**
   * mark task to completed or not.
   *  @param index  index of task to be marked.
   * @param status mark task to be completed with true or not completed with false.
   * @return
   */
  public String setTaskStatus(int index, boolean status) throws DukeException {
    try {
      ls.get(index);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("No element found");
    }
    ls.get(index).setStatus(status);
    String s = "";
    if (status == true) {
      s = "OK, I've marked this task as done : \n";

    } else {
      s = "OK, I've marked this task as not done yet: \n";
    }
    try {
      storage.deleteFromFile("duke.txt", ls);
    } catch (IOException e) {
      return "Something went wrong: \n" + e.getMessage();
    }
    return s + ls.get(index).toString();
  }

  public String findLine(String s) throws DukeException {
    boolean stop = false;
    for (int i = 0; i < ls.size(); i++) {
      if (ls.get(i).toString().contains(s)) {
        this.tracker.add(ls.get(i).toString());
        stop = true;
      }
    }
    if (stop) {
      String string = "Here are the matching tasks in your list: \n";
      for (int i = 0; i < ls.size(); i++) {
        string += valueOf(i + 1) + "." + " " + tracker.get(i) + "\n";
      }
      return string;
    } else {
      throw new DukeException("No matching tasks");
    }
  }


}
