public class DeleteException extends RabbitException {
    public enum Type {
        FORMAT, INDEX;
    }
    private Type type;

    public DeleteException(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        switch(this.type) {
            case FORMAT:
                return "Type 'delete index' to delete a task if that's what you want.";
            case INDEX:
                return "You can't delete a task that's not in the list.";
        }
        return "";
    }

}
