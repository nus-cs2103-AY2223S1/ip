public class CheckBox {
    private boolean isChecked;

    public CheckBox(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setCheckBox(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String toString() {
        String content = this.isChecked ? "X" : " ";
        return "[" + content +  "]";
    }
}
