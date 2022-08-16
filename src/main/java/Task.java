public abstract class Task {
  private String title;
  private boolean done;

  public Task(String title, boolean done) {
    this.title = title;
    this.done = done;
  }

  public String getTitle() {
    return this.title;
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
