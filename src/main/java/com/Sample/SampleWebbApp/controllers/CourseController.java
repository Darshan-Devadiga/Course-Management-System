package com.Sample.SampleWebbApp.controllers;

import com.Sample.SampleWebbApp.entities.Course;
import com.Sample.SampleWebbApp.services.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // List all courses
    @GetMapping({"/", "/courses"})
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses";
    }

    // Show form to add a new course
    @GetMapping("/courses/new")
    public String createCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "create_course";
    }

    // Save the new course
    @PostMapping("/courses")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.saveCourse(course);
        return "redirect:/courses";
    }

    // Show form to edit an existing course
    @GetMapping("/courses/edit/{id}")
    public String editCourseForm(@PathVariable int id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "edit_course";
    }

    // Update the course
    @PostMapping("/courses/{id}")
    public String updateCourse(@PathVariable int id, @ModelAttribute("course") Course course) {
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setName(course.getName());
        existingCourse.setInstructor(course.getInstructor());
        existingCourse.setSubject(course.getSubject());
        existingCourse.setFee(course.getFee());

        courseService.saveCourse(existingCourse);
        return "redirect:/courses";
    }

    // Delete a course
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}