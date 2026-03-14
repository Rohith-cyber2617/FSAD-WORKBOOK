package com.klu.controller;

import com.klu.model.Course;
import com.klu.services.CourseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseServices courseService;

    // CREATE
    @PostMapping
    public ResponseEntity<Object> addCourse(@RequestBody Course course) {

        if (course.getTitle() == null || course.getTitle().isEmpty()) {
            return new ResponseEntity<>("Title cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Course saved = courseService.addCourse(course);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable int id) {

        Optional<Course> course = courseService.getCourseById(id);

        if (course.isPresent()) {
            return new ResponseEntity<>(course.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable int id,
                                               @RequestBody Course course) {

        Optional<Course> updated = courseService.updateCourse(id, course);

        if (updated.isPresent()) {
            return new ResponseEntity<>(updated.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable int id) {

        boolean deleted = courseService.deleteCourse(id);

        if (deleted) {
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
    }

    // SEARCH
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Course>> searchByTitle(@PathVariable String title) {
        return new ResponseEntity<>(courseService.searchByTitle(title), HttpStatus.OK);
    }
}