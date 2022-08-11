public enum TaskType {
  TODO, DEADLINE, EVENT;

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
