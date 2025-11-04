package com.example.youtubescheduler.controller;

import com.example.youtubescheduler.model.VideoSchedule;
import com.example.youtubescheduler.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    // View all schedules + add new
    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("schedules", service.getAllSchedules());
        model.addAttribute("newSchedule", new VideoSchedule());
        model.addAttribute("isEdit", false); // for conditional form display
        return "schedule";
    }

    // Save new or updated schedule
    @PostMapping("/save")
    public String save(@ModelAttribute VideoSchedule schedule,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        try {
            service.save(schedule);
            redirectAttributes.addFlashAttribute("successMessage", "✅ Schedule saved successfully!");
            return "redirect:/schedule";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("schedules", service.getAllSchedules());
            model.addAttribute("newSchedule", schedule);
            model.addAttribute("isEdit", schedule.getId() != null);
            return "schedule"; // redisplay same page with error
        }
    }

    // Edit existing schedule (load it into the form)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        VideoSchedule existing = service.getById(id);
        model.addAttribute("newSchedule", existing);
        model.addAttribute("schedules", service.getAllSchedules());
        model.addAttribute("isEdit", true); // flag to show update button
        return "schedule";
    }

    // Delete a schedule
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("successMessage", "🗑️ Schedule deleted successfully!");
        return "redirect:/schedule";
    }
}
