package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    TimeEntryRepository inMemoryTimeEntryRepository ;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.inMemoryTimeEntryRepository = timeEntriesRepo;
    }

    @PostMapping
    public ResponseEntity create (@RequestBody TimeEntry timeEntry){
        TimeEntry createdTimeEntry = inMemoryTimeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED) ;
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry>  read(@PathVariable long id){
        TimeEntry foundTimeEntry = inMemoryTimeEntryRepository.find(id);
        if(foundTimeEntry!=null){
            return new ResponseEntity<>(foundTimeEntry, HttpStatus.OK) ;
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND) ;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<>(inMemoryTimeEntryRepository.list(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = inMemoryTimeEntryRepository.update(id, timeEntry);
        if(updatedTimeEntry!= null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete (@PathVariable long id) {
        inMemoryTimeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
