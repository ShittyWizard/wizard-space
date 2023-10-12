package app.record.service;

import app.record.model.Record;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecordService {
    List<Record> getAll();
    Record addNew();
}
