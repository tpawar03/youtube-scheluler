package com.example.youtubescheduler.controller;

import com.example.youtubescheduler.model.VideoSchedule;
import com.example.youtubescheduler.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("schedules", service.getAllSchedules());
        model.addAttribute("newSchedule", new VideoSchedule());
        return "schedule";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute VideoSchedule schedule) {
        service.save(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/schedule";
    }
}
