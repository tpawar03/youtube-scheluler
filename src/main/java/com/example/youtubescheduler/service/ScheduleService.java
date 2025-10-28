package com.example.youtubescheduler.service;

import com.example.youtubescheduler.model.VideoSchedule;
import com.example.youtubescheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repo;

    public ScheduleService(ScheduleRepository repo) {
        this.repo = repo;
    }

    // Get all schedules
    public List<VideoSchedule> getAllSchedules() {
        return repo.findAll();
    }

    // Get a schedule by ID (for editing)
    public VideoSchedule getById(Long id) {
        return repo.findById(id);
    }

    // Save new or updated schedule
    public void save(VideoSchedule schedule) {
        repo.save(schedule);
    }

    // Delete a schedule by ID
    public void delete(Long id) {
        repo.delete(id);
    }
}
