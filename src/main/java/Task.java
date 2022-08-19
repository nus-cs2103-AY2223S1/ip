public abstract class Task {

  private final String title;
  private boolean status;

  Task(String title, boolean status) {
    this.title = title;
    this.status = status;
  }

  Task setStatus(boolean status) {
    this.status = status;
    return this;
  }

  @Override
  public String toString() {
    return "[" + (this.status ? "X" : " ") + "] " + this.title;
  }
}
