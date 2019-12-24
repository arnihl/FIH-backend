package is.tonlistarskolifih.TonlistarskoliFIH.Service;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Course;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempCourse;

import java.util.List;

public interface CourseService {
    Course save(Course course);
    void deleteById(long id);
    Course findById(long id);
    List<Course> findAll();
    Course saveTempCourse(TempCourse tempCourse);
    TempCourse convertToTempCourse(Course course);
    void updateCourse(TempCourse tempCourse, long id);
    List<Course> findAllFIHCourses();
    List<Course> findAllMITCourses();
}
