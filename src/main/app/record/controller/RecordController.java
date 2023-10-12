package app.record.controller;

import app.record.model.Record;
import app.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService service;

    @GetMapping("/getAll")
    public List<Record> getAll() {
        return service.getAll();
    }

    @PostMapping("/addNew")
    public Record addNew() {
        return service.addNew();
    }
}
