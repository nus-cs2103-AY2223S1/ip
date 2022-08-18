public abstract class Task {
  protected String description;
  protected boolean isDone = false;

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

  public void markAsDone() {
    this.isDone = true;
    System.out.println("Paw-some! Another task done!");
    System.out.println("  " + this);
  }

  public void markAsNotDone() {
    this.isDone = false;
    System.out.println("Okay, I've marked this task as not done yet.");
    System.out.println("  " + this);
  }

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
          throw new CheeseException("A deadline requires a /by flag.");
        }
        String[] eventInputArray = textAfterCommand.split("/at", 2);
        if (eventInputArray[0].length() == 0 || eventInputArray[1].length() == 0) {
          throw new CheeseException("An event requires both a description and event time.");
        }
        break;
    }
  }

  @Override
  public String toString() {
    String checkbox = this.isDone ? "[X] " : "[ ] ";
    return checkbox + this.description;
  }
}
