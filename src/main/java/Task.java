public abstract class Task {
  private String description;
  private boolean done;

  public Task(String description, boolean done) {
    this.description = description;
    this.done = done;
  }

  public String getDescription() {
    return this.description;
  }

  public boolean getDone() {
    return this.done;
  }

  public void setDone() {
    this.done = true;
  }

  public void setNotDone() {
    this.done = false;
  }
}
