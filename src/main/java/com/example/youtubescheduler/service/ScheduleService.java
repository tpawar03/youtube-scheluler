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

    public List<VideoSchedule> getAllSchedules() {
        return repo.findAll();
    }

    public void save(VideoSchedule schedule) {
        repo.save(schedule);
    }

    public void delete(Long id) {
        repo.delete(id);
    }
}
