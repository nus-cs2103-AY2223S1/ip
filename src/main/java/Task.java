public abstract class Task {
  /** Description of Task */
  private String description;

  /** Boolean representing whether Task is done or not */
  private boolean isDone = false;

  /**
   * Constructor to create a new Task
   * 
   * @param description description of Task
   */
  protected Task(String description) {
    this.description = description;
  }

  /**
   * Factory method to create a Task
   * 
   * @param inputArray array containing user input after splitting by space
   * @return created Task
   * @throws CheeseException if given command is invalid
   */
  public static Task createTask(String[] inputArray) throws CheeseException {
    String command = inputArray[0];

    switch (Command.valueOf(command)) {
      case todo:
        Task.validateCreateTask(inputArray);
        return new Todo(inputArray[1]);
      case deadline:
        Task.validateCreateTask(inputArray);
        String[] deadlineInputArray = inputArray[1].split("/by", 2);
        return new Deadline(deadlineInputArray[0].trim(), deadlineInputArray[1].trim());
      case event:
        Task.validateCreateTask(inputArray);
        String[] eventInputArray = inputArray[1].split("/at", 2);
        return new Event(eventInputArray[0].trim(), eventInputArray[1].trim());
      default:
        throw new CheeseException();
    }
  }

  /** Marks this task as done */
  public void markAsDone() {
    this.isDone = true;
    System.out.println("Paw-some! Another task done!");
    System.out.println("  " + this);
  }

  /** Marks this task as not done */
  public void markAsNotDone() {
    this.isDone = false;
    System.out.println("Okay, I've marked this task as not done yet.");
    System.out.println("  " + this);
  }

  /**
   * Checks if given command is valid
   * 
   * @param inputArray array containing user input after splitting with space
   * @throws CheeseException if given command has no description or deadline
   *                         command has not /by flag or deadline command has no
   *                         description/deadline or event command has no /at flag
   *                         or event command has no description/event time
   */
  private static void validateCreateTask(String[] inputArray) throws CheeseException {
    String command = inputArray[0];

    if (inputArray.length == 1 || inputArray[1].length() == 0) {
      throw new CheeseException("Sowwy, the description of a " + command + " cannot be empty.");
    }

    String textAfterCommand = inputArray[1];

    switch (Command.valueOf(command)) {
      case deadline:
        if (textAfterCommand.indexOf("/by") == -1) {
          throw new CheeseException("A deadline requires a /by flag.");
        }
        String[] deadlineInputArray = textAfterCommand.split("/by", 2);
        if (deadlineInputArray[0].length() == 0 || deadlineInputArray[1].length() == 0) {
          throw new CheeseException("A deadline requires both a description and deadline.");
        }
        break;
      case event:
        if (textAfterCommand.indexOf("/at") == -1) {
          throw new CheeseException("An event requires a /at flag.");
        }
        String[] eventInputArray = textAfterCommand.split("/at", 2);
        if (eventInputArray[0].length() == 0 || eventInputArray[1].length() == 0) {
          throw new CheeseException("An event requires both a description and event time.");
        }
        break;
    }
  }

  /**
   * Returns string representation of Task
   * 
   * @return string representation of Task
   */
  @Override
  public String toString() {
    String checkbox = this.isDone ? "[X] " : "[ ] ";
    return checkbox + this.description;
  }
}
