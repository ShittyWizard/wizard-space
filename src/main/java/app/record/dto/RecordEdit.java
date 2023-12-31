package app.record.dto;

import java.util.UUID;

public class RecordEdit {
    private UUID id;
    private String name;

    public RecordEdit() {
    }

    public RecordEdit(UUID recordId, String name) {
        this.id = recordId;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
