public abstract class Task {

  protected String task;
  protected boolean isComplete = false;

  /**
   * A method used to validate a userCommand to for a specific task type
   *
   * @param userCommand the command entered by the user to be parsed by the method
   * @param taskType the task type either Todo, Deadline or Event
   * @throws DukeException
   */
  private static void validateTaskCreation(
    String userCommand,
    TaskType taskType
  )
    throws DukeException {
    String[] cmdArray = userCommand.split(" ", 2);
    if (cmdArray.length <= 1 || cmdArray[1].length() == 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a " + taskType + " cannot be empty."
      );
    }

    if (taskType == TaskType.DEADLINE && cmdArray[1].indexOf("/by") < 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a DEADLINE must contain a '/by'"
      );
    }

    if (taskType == TaskType.EVENT && cmdArray[1].indexOf("/at") < 0) {
      throw new DukeException(
        "☹ OOPS!!! The description of a EVENT must contain a '/at'"
      );
    }
  }

  /**
   * Factory method used to create a new Task
   *
   * @param userCommand the command entered by the user to be parsed by the method
   * @return a Task obj, either a Todo, Deadline or Event
   * @throws DukeException
   */
  public static Task createTask(String userCommand) throws DukeException {
    String task;
    String date;
    String[] cmdArray = userCommand.split(" ", 2);
    String cmd = cmdArray[0];
    Task newTask;

    switch (Commands.valueOf(cmd)) {
      case todo:
        Task.validateTaskCreation(userCommand, TaskType.TODO);
        task = cmdArray[1].trim();
        newTask = new Todo(task);
        break;
      case deadline:
        Task.validateTaskCreation(userCommand, TaskType.DEADLINE);
        task = cmdArray[1].split("/by", 2)[0].trim();
        date = cmdArray[1].split("/by", 2)[1].trim();
        newTask = new Deadline(task, date);
        break;
      case event:
        Task.validateTaskCreation(userCommand, TaskType.EVENT);
        task = cmdArray[1].split("/at", 2)[0].trim();
        date = cmdArray[1].split("/at", 2)[1].trim();
        newTask = new Event(task, date);
        break;
      default:
        throw new DukeException();
    }
    System.out.println("\nGot it. I've added this task:");
    System.out.println(newTask);
    return newTask;
  }

  /** Method used to mark this task as complete */
  public void markAsDone() {
    this.isComplete = true;
    System.out.println("Nice! I've marked this task as done:");
    System.out.println(this);
  }

  /** Method used to mark this task as incomplete */
  public void unmark() {
    this.isComplete = false;
    System.out.println("OK, I've marked this task as not done yet:");
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
}
