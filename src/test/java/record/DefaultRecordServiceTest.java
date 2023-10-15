package record;

import app.record.dto.RecordCreate;
import app.record.dto.RecordEdit;
import app.record.exception.RecordNotFoundException;
import app.record.model.Record;
import app.record.repository.RecordRepository;
import app.record.service.DefaultRecordService;
import app.record.service.RecordService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DefaultRecordServiceTest {
    @MockBean
    private RecordRepository repository;
    @Autowired
    private RecordService recordService;

    @Configuration
    @Import(DefaultRecordService.class)
    static class DefaultRecordServiceTestContextConfiguration {
    }

    @Test
    public void testGetAll() {
        Record record1 = new Record(UUID.randomUUID(), "Record1");
        Record record2 = new Record(UUID.randomUUID(), "Record2");
        List<Record> expectedResult = List.of(record1, record2);
        when(repository.findAll()).thenReturn(expectedResult);

        List<Record> getAllResult = recordService.getAll();

        Assert.assertEquals(expectedResult.size(), getAllResult.size());
        expectedResult.forEach(expectedRec -> {
            assert getAllResult.stream()
                    .anyMatch(rec ->
                            rec.getId().equals(expectedRec.getId()) &&
                                    rec.getName().equals(expectedRec.getName())
                    );
        });
    }

    @Test
    public void testGetByIdPositive() throws RecordNotFoundException {
        Record expectedResult = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(expectedResult.getId())).thenReturn(Optional.of(expectedResult));

        Record getByIdResult = recordService.getById(expectedResult.getId());
        Assert.assertEquals(expectedResult, getByIdResult);
    }

    @Test(expected = RecordNotFoundException.class)
    public void testGetByIdNegative() throws RecordNotFoundException {
        Record expectedResult = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(expectedResult.getId())).thenReturn(Optional.of(expectedResult));

        Record getByIdResult = recordService.getById(UUID.randomUUID());
    }

    @Test
    public void create() {
        Record expectedResult = new Record(UUID.randomUUID(), "Record1");
        when(repository.save(Mockito.any(Record.class))).thenReturn(expectedResult);

        Record createResult = recordService.create(new RecordCreate(expectedResult.getName()));

        Assert.assertEquals(expectedResult, createResult);
    }

    @Test
    public void editPositive() throws RecordNotFoundException {
        Record oldRecord = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(oldRecord.getId())).thenReturn(Optional.of(oldRecord));
        Record editedRecord = new Record(oldRecord.getId(), "Record2");
        when(repository.save(Mockito.any(Record.class))).thenReturn(editedRecord);

        Record editResult = recordService.edit(new RecordEdit(oldRecord.getId(), editedRecord.getName()));

        Assert.assertEquals(editedRecord, editResult);
    }

    @Test(expected = RecordNotFoundException.class)
    public void editNegative() throws RecordNotFoundException {
        Record oldRecord = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(oldRecord.getId())).thenReturn(Optional.of(oldRecord));
        Record editedRecord = new Record(oldRecord.getId(), "Record2");
        when(repository.save(Mockito.any(Record.class))).thenReturn(editedRecord);

        Record editResult = recordService.edit(new RecordEdit(UUID.randomUUID(), editedRecord.getName()));
    }

    @Test
    public void deletePositive() throws RecordNotFoundException {
        Record record1 = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(record1.getId())).thenReturn(Optional.of(record1));

        recordService.deleteById(record1.getId());
    }

    @Test(expected = RecordNotFoundException.class)
    public void deleteNegative() throws RecordNotFoundException {
        Record record1 = new Record(UUID.randomUUID(), "Record1");
        when(repository.findById(record1.getId())).thenReturn(Optional.of(record1));

        recordService.deleteById(UUID.randomUUID());
    }
}
