package com.Sample.SampleWebbApp.services;

import com.Sample.SampleWebbApp.entities.Course;
import com.Sample.SampleWebbApp.repos.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
    }

    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }
}
