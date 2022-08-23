public class ToDo extends Task{

  public ToDo(String description) {
    super(description);
  }

  public ToDo(String description, boolean isDone) {
    super(description,isDone);
  }
  public String toString() {
    return "[T][" + this.getStatusIcon() + "] " + this.description;
  }

}
