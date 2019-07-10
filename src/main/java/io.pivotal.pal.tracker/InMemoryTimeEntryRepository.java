package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long,TimeEntry> myTimeEntryMap = new HashMap<Long, TimeEntry>();
    private long id = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry myTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        this.myTimeEntryMap.put(myTimeEntry.getId(), myTimeEntry);
        id++;
        return myTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return myTimeEntryMap.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (myTimeEntryMap.containsKey(id)) {
            TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
            this.myTimeEntryMap.put(id, updatedTimeEntry);
            return updatedTimeEntry;
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        myTimeEntryMap.remove(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(myTimeEntryMap.values());
    }
}
