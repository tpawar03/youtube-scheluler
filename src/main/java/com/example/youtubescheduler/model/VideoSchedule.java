package com.example.youtubescheduler.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoSchedule {
    private Long id;
    private String title;
    private String youtubeUrl;
    private String startTime;
    private int durationMinutes;
}