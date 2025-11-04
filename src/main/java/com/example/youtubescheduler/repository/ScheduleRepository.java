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

    // Get all schedules (sorted by start time)
    public List<VideoSchedule> findAll() {
        return jdbcTemplate.query("""
            SELECT * FROM video_schedule ORDER BY start_time
        """, new BeanPropertyRowMapper<>(VideoSchedule.class));
    }

    // Get a single schedule by ID (for editing)
    public VideoSchedule findById(Long id) {
        return jdbcTemplate.queryForObject("""
            SELECT * FROM video_schedule WHERE id=?
        """, new BeanPropertyRowMapper<>(VideoSchedule.class), id);
    }

    // Save or update a schedule
    public void save(VideoSchedule schedule) {
        if (schedule.getId() == null) {
            jdbcTemplate.update("""
                INSERT INTO video_schedule(title, youtube_url, start_time, duration_minutes)
                VALUES (?, ?, ?, ?)
            """, schedule.getTitle(), schedule.getYoutubeUrl(),
                    schedule.getStartTime(), schedule.getDurationMinutes());
        } else {
            jdbcTemplate.update("""
                UPDATE video_schedule
                SET title=?, youtube_url=?, start_time=?, duration_minutes=?
                WHERE id=?
            """, schedule.getTitle(), schedule.getYoutubeUrl(),
                    schedule.getStartTime(), schedule.getDurationMinutes(), schedule.getId());
        }
    }

    // Delete schedule by ID
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM video_schedule WHERE id=?", id);
    }

    // Check for scheduling conflicts
    public boolean hasConflict(VideoSchedule newSchedule) {
        String sql = """
            SELECT COUNT(*) FROM video_schedule
            WHERE id != COALESCE(?, -1)
              AND (
                datetime(start_time) < datetime(?, '+' || ? || ' minutes')
                AND datetime(start_time, '+' || duration_minutes || ' minutes') > datetime(?)
              )
        """;

        Long count = jdbcTemplate.queryForObject(sql, Long.class,
                newSchedule.getId(),
                newSchedule.getStartTime(), newSchedule.getDurationMinutes(),
                newSchedule.getStartTime());

        return count != null && count > 0;
    }
}
