package app.record.service;

import app.record.dto.RecordCreate;
import app.record.dto.RecordEdit;
import app.record.exception.RecordNotFoundException;
import app.record.model.Record;

import java.util.List;
import java.util.UUID;

public interface RecordService {
    List<Record> getAll();
    Record getById(UUID recordId) throws RecordNotFoundException;
    void deleteById(UUID recordId) throws RecordNotFoundException;
    Record edit(RecordEdit recordEdit) throws RecordNotFoundException;
    Record create(RecordCreate recordCreate);
}
