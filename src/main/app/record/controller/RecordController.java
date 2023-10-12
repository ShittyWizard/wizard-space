package app.record.controller;

import app.record.dto.RecordCreate;
import app.record.dto.RecordEdit;
import app.record.exception.RecordNotFoundException;
import app.record.model.Record;
import app.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService service;

    @GetMapping("/getAll")
    public List<Record> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/${id}")
    public Record getById(@PathVariable UUID recordId) throws RecordNotFoundException {
        return service.getById(recordId);
    }

    @PostMapping("/create")
    public Record create(@RequestBody RecordCreate recordCreate) {
        return service.create(recordCreate);
    }

    @PostMapping("/edit")
    public Record edit(@RequestBody RecordEdit recordEdit) throws RecordNotFoundException {
        return service.edit(recordEdit);
    }

    @DeleteMapping("/delete/${id}")
    public void delete(@PathVariable UUID id) throws RecordNotFoundException {
        service.deleteById(id);
    }

}
