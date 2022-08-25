public class ToDo extends Task {
        protected ToDo(String name, String date) {
            super(name, "");
            type = "T";
        }

    @Override
    public String stringType() {
        return "todo";
    }
}
