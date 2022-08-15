public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public void mark(){
    this.isDone = true;
    System.out.println("Nice! I've marked this task as done:\n  " + this);
  }

  public void unmark(){
    this.isDone = false;
    System.out.println("OK, I've marked this task as not done yet:\n  " + this);
  }
  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

}
