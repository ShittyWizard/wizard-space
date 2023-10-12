package app.record.service;

import app.record.model.Record;
import app.record.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public Record addNew() {
        return repository.save(new Record(UUID.randomUUID(), "Vlad" + System.currentTimeMillis()));
    }
}
