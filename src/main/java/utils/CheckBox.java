package utils;

public class CheckBox {
    private boolean isChecked;

    public CheckBox(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setCheckBox(boolean isChecked) {
        this.isChecked = isChecked;
    }

    private String getStatusIcon() {
        return this.isChecked ? "X" : " ";
    }

    public String toString() {
        return "[" + getStatusIcon() +  "]";
    }
}
