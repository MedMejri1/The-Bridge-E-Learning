package com.mejri.thebridge_elearning.controllers;

import com.mejri.thebridge_elearning.models.Courses;
import com.mejri.thebridge_elearning.services.CoursesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping // Ajout d'un préfixe pour les endpoints
public class CoursesController {

    private final CoursesService coursesService;

    @Autowired
    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @Operation(summary = "Get all courses")
    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> allCourses = coursesService.getAllCourses();
        return new ResponseEntity<>(allCourses, HttpStatus.OK);
    }

    @Operation(summary = "Get course by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourseById(
            @Parameter(description = "ID of the course", example = "1") @PathVariable Integer id) {
        Optional<Courses> course = coursesService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new course")
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Courses> createCourse(
            @RequestBody Courses course) {
        Courses createdCourse = coursesService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a course by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Courses> updateCourse(
            @Parameter(description = "ID of the course", example = "1") @PathVariable Integer id,
            @RequestBody Courses course) {
        Courses updatedCourse = coursesService.updateCourse(id, course);
        if (updatedCourse != null) {
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a course by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @Parameter(description = "ID of the course", example = "1") @PathVariable Integer id) {
        coursesService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get total count of courses")
    @GetMapping("/count")
    public ResponseEntity<Long> countCourses() {
        long totalCourses = coursesService.countCourses();
        return new ResponseEntity<>(totalCourses, HttpStatus.OK);
    }
}
