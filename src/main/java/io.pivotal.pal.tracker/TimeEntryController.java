package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class TimeEntryController{

    private TimeEntryRepository repository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;


    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        System.out.println(timeEntryToCreate.toString());
        TimeEntry val = repository.create(timeEntryToCreate);
        System.out.println(val.toString());


        return new ResponseEntity<TimeEntry>(new TimeEntry(val.getId(),timeEntryToCreate.getProjectId(),
                timeEntryToCreate.getUserId(),timeEntryToCreate.getDate(),timeEntryToCreate.getHours()), CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry val = repository.find(timeEntryId);

        if(val != null) {
            return new ResponseEntity<>(val, OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(repository.list(),OK);

    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable long timeEntryId, @RequestBody TimeEntry newValues) {
        TimeEntry val = repository.update(timeEntryId, newValues);

        if (val != null) {
            return new ResponseEntity<TimeEntry>(val, OK);
        } else
            return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
