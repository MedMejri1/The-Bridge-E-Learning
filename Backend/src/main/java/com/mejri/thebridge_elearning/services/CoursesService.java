package com.mejri.thebridge_elearning.services;

import com.mejri.thebridge_elearning.models.Courses;
import com.mejri.thebridge_elearning.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursesService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Courses> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    public Courses createCourse(Courses course) {
        return courseRepository.save(course);
    }

    public Courses updateCourse(Integer id, Courses newCourseData) {
        Optional<Courses> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            Courses existingCourse = optionalCourse.get();
            existingCourse.setImage(newCourseData.getImage());
            existingCourse.setTitle(newCourseData.getTitle());
            existingCourse.setPrice(newCourseData.getPrice());

            return courseRepository.save(existingCourse);
        }
        return null;
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }

    public long countCourses() {
        return courseRepository.count();
    }

}
