package duke.task;

import duke.common.DukeException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

  private List<Task> list;

  public TaskList(List<Task> list) {
    this.list = list;
  }

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public List<Task> getTaskList() {
    return list;
  }

  public TaskList add(Task task) {
    list.add(task);
    return this;
  }

  public TaskList delete(int index) throws DukeException {
    if (index < 0 || index >= list.size()) {
      throw new DukeException("Task " + (index + 1) + " does not exist!");
    }
    list.remove(index);
    return this;
  }

  public TaskList mark(int index, boolean status) throws DukeException {
    if (index < 0 || index >= list.size()) {
      throw new DukeException("Task " + (index + 1) + " does not exist!");
    }
    list.get(index).setStatus(status);
    return this;
  }

  public TaskList filter(String keyword) {
    List<Task> filtered = list
      .stream()
      .filter(task -> task.title.contains(keyword))
      .collect(Collectors.<Task>toList());
    return new TaskList(filtered);
  }

  @Override
  public String toString() {
    String ret = "";
    for (int i = 0; i < list.size(); i++) {
      ret += (i + 1) + ") " + list.get(i).toString() + "\n";
    }
    return ret;
  }
}
