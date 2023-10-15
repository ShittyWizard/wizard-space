package app.record.service;

import app.record.dto.RecordCreate;
import app.record.dto.RecordEdit;
import app.record.exception.RecordNotFoundException;
import app.record.model.Record;
import app.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultRecordService implements RecordService {
    @Autowired
    private RecordRepository repository;

    @Override
    public List<Record> getAll() {
        List<Record> recordList = new ArrayList<>();
        Iterable<Record> all = repository.findAll();
        all.forEach(recordList::add);
        return recordList;
    }

    @Override
    public Record getById(UUID recordId) throws RecordNotFoundException {
        Optional<Record> optionalRecord = repository.findById(recordId);
        if (optionalRecord.isPresent()) {
            return optionalRecord.get();
        } else {
            throw new RecordNotFoundException(recordId);
        }
    }

    @Override
    public Record create(RecordCreate recordCreate) {
        Record newRecord = new Record(recordCreate.getId(), recordCreate.getName());
        return repository.save(newRecord);
    }

    @Override
    public Record edit(RecordEdit recordEdit) throws RecordNotFoundException {
        UUID recordId = recordEdit.getRecordId();
        Record record = getById(recordId);
        record.setName(recordEdit.getName());
        return repository.save(record);
    }

    @Override
    public void deleteById(UUID recordId) throws RecordNotFoundException {
        Record record = getById(recordId);
        repository.delete(record);
    }
}
