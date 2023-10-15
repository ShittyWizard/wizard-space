package app.record.dto;

import java.util.UUID;

public class RecordEdit {
    private UUID recordId;
    private String name;

    public RecordEdit(UUID recordId, String name) {
        this.recordId = recordId;
        this.name = name;
    }

    public UUID getRecordId() {
        return recordId;
    }

    public void setRecordId(UUID recordId) {
        this.recordId = recordId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
