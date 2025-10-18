package com.example.youtubescheduler.repository;

import com.example.youtubescheduler.model.VideoSchedule;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        jdbcTemplate.execute("""
                CREATE TABLE IF NOT EXISTS video_schedule (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    youtube_url TEXT,
                    start_time TEXT,
                    duration_minutes INTEGER
                )
                """);
    }

    public List<VideoSchedule> findAll() {
        return jdbcTemplate.query("SELECT * FROM video_schedule",
                new BeanPropertyRowMapper<>(VideoSchedule.class));
    }

    public void save(VideoSchedule schedule) {
        if (schedule.getId() == null) {
            jdbcTemplate.update("INSERT INTO video_schedule(title, youtube_url, start_time, duration_minutes) VALUES (?, ?, ?, ?)",
                    schedule.getTitle(), schedule.getYoutubeUrl(), schedule.getStartTime(), schedule.getDurationMinutes());
        } else {
            jdbcTemplate.update("UPDATE video_schedule SET title=?, youtube_url=?, start_time=?, duration_minutes=? WHERE id=?",
                    schedule.getTitle(), schedule.getYoutubeUrl(), schedule.getStartTime(),
                    schedule.getDurationMinutes(), schedule.getId());
        }
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM video_schedule WHERE id=?", id);
    }
}
