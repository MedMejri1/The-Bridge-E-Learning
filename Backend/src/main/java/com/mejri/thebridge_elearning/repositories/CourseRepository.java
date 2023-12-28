package com.mejri.thebridge_elearning.repositories;

import com.mejri.thebridge_elearning.models.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Courses,Integer> {
}
