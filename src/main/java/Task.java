public abstract class Task {

  protected final String title;
  protected boolean status;

  Task(String title, boolean status) {
    this.title = title;
    this.status = status;
  }

  Task setStatus(boolean status) {
    this.status = status;
    return this;
  }

  abstract String saveString();

  @Override
  public String toString() {
    return "[" + (this.status ? "X" : " ") + "] " + this.title;
  }
}
