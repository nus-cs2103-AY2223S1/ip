class Todo extends Task{

    public Todo(String itself) {
        super(itself);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
