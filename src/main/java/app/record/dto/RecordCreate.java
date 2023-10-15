package app.record.dto;

public class RecordCreate {
    private String name;

    public RecordCreate() {
    }

    public RecordCreate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
