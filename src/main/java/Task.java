public abstract class Task {

  protected String task;
  protected boolean isComplete = false;

  /**
   * Constructor to create new Task class
   *
   * @param task
   */
  protected Task(String task) {
    this.task = task;
  }

  /**
   * Factory method used to create a new Task
   *
   * @param userCommand the command entered by the user to be parsed by the method
   * @return a Task obj, either a Todo, Deadline or Event
   * @throws DukeException
   */
  public static Task createTask(String[] commandArray) throws DukeException {
    String task;
    String date;
    String command = commandArray[0];
    try {
      switch (Command.valueOf(command)) {
        case todo:
          Task.validateTaskCreation(commandArray, TaskType.TODO);
          task = commandArray[1].trim();
          return new Todo(task);
        case deadline:
          Task.validateTaskCreation(commandArray, TaskType.DEADLINE);
          task = commandArray[1].split("/by", 2)[0].trim();
          date = commandArray[1].split("/by", 2)[1].trim();
          return new Deadline(task, date);
        case event:
          Task.validateTaskCreation(commandArray, TaskType.EVENT);
          task = commandArray[1].split("/at", 2)[0].trim();
          date = commandArray[1].split("/at", 2)[1].trim();
          return new Event(task, date);
        default:
          throw new DukeException();
      }
    } catch (IllegalArgumentException e) {
      throw new DukeException();
    }
  }

  /** Method used to mark this task as complete */
  public void markAsDone() {
    this.isComplete = true;
    System.out.println("\nNice! I've marked this task as done:");
    System.out.println(this);
  }

  /** Method used to mark this task as incomplete */
  public void unmark() {
    this.isComplete = false;
    System.out.println("\nOK, I've marked this task as not done yet:");
    System.out.println(this);
  }

  /**
   * To String method that returns the task in string form to the user
   *
   * @return the task in string format
   */
  @Override
  public String toString() {
    String checkBox = this.isComplete ? "[X] " : "[ ] ";
    return checkBox + this.task;
  }

  /**
   * A method used to validate a userCommand to for a specific task type
   *
   * @param userCommand the command entered by the user to be parsed by the method
   * @param taskType the task type either Todo, Deadline or Event
   * @throws DukeException
   */
  private static void validateTaskCreation(
    String[] commandArray,
    TaskType taskType
  )
    throws DukeException {
    // TODO Move this logic to a factory method within the Tasks itself
    if (commandArray.length <= 1 || commandArray[1].length() == 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a " + taskType + " cannot be empty."
      );
    }

    if (taskType == TaskType.DEADLINE && commandArray[1].indexOf("/by") < 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a DEADLINE must contain a '/by'"
      );
    }

    if (taskType == TaskType.EVENT && commandArray[1].indexOf("/at") < 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a EVENT must contain a '/at'"
      );
    }
  }
}
