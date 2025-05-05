package org.agcodes.eazyschool.service;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.exception.EntityNotFoundException;
import org.agcodes.eazyschool.model.Courses;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.repository.CoursesRepository;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class CoursesService {

  @Autowired
  private final CoursesRepository coursesRepository;
  @Autowired
  private final PersonRepository personRepository;

  public CoursesService(CoursesRepository coursesRepository,PersonRepository personRepository) {
    this.coursesRepository = coursesRepository;
    this.personRepository = personRepository;
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

  public boolean addStudent(Person person, Courses course) {
    Person personEntity = personRepository.findByEmail(person.getEmail());
    if (personEntity == null || !(personEntity.getPersonId() > 0)) {
      return false;
    }
    personEntity.getCourses().add(course);
    // keeps both sides in sync manually (recommended for bidirectional)
    course.getPersons().add(personEntity);
    // This saves both the person and course
    Person savedPerson = personRepository.save(personEntity);
   return true;
  }

  public Person deleteStudent(int personId, Courses course) {
    Optional<Person> personOptional = personRepository.findById(personId);
    if(personOptional.isEmpty()){
      return null;
    }
    Person personEntity = personOptional.get();
    // remove course from person
    personEntity.getCourses().remove(course);
    // keeps both sides in sync manually (recommended for bidirectional)
    course.getPersons().remove(personId);
    return personRepository.save(personEntity);
  }
}
