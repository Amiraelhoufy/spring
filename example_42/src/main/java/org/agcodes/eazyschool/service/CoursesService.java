package org.agcodes.eazyschool.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.exception.EntityNotFoundException;
import org.agcodes.eazyschool.model.Courses;
import org.agcodes.eazyschool.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CoursesService {

  @Autowired
  private final CoursesRepository coursesRepository;

  public CoursesService(CoursesRepository coursesRepository) {
    this.coursesRepository = coursesRepository;
  }

  public List<Courses> findAllCourses() {
    return coursesRepository.findAll();
  }

  public boolean saveNewCourse(Courses newCourse) {
    Courses savedCourse = coursesRepository.save(newCourse);
    return savedCourse != null && savedCourse.getCourseId() > 0;
  }

  public Courses findCourseById(int courseId) {
    return coursesRepository.findById(courseId)
        .orElseThrow(
            () -> new EntityNotFoundException("Course with ID " + courseId + " not found"));
  }

}
