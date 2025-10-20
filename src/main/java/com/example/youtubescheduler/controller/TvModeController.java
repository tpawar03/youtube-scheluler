package com.example.youtubescheduler.controller;

import com.example.youtubescheduler.model.VideoSchedule;
import com.example.youtubescheduler.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;

@Controller
public class TvModeController {

    private final ScheduleService service;

    public TvModeController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping("/tvmode")
    public String tvMode(Model model) {
        List<VideoSchedule> all = service.getAllSchedules()
                .stream()
                .sorted(Comparator.comparing(VideoSchedule::getStartTime))
                .toList();

        model.addAttribute("schedules", all);
        return "tvmode";
    }
}
