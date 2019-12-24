package is.tonlistarskolifih.TonlistarskoliFIH.Repository;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course save(Course course);
    void deleteById(long id);
    Course findById(long id);
    List<Course> findAll();

}
