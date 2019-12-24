package is.tonlistarskolifih.TonlistarskoliFIH.Service.Implementation;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Course;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Teacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempCourse;
import is.tonlistarskolifih.TonlistarskoliFIH.Repository.CourseRepository;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.CourseService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImplementation implements CourseService {
    CourseRepository repository;
    TeacherService teacherService;

    @Autowired
    public CourseServiceImplementation(CourseRepository repository, TeacherService teacherService) {
        this.repository = repository;
        this.teacherService = teacherService;
    }

    @Override
    public Course save(Course course) {
        return repository.save(course);
    }

    @Override
    public void deleteById(long id) {
        if(repository.findById(id) != null){
            repository.deleteById(id);
        }
    }

    @Override
    public Course findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return repository.findAll();
    }

    @Override
    public Course saveTempCourse(TempCourse tempCourse) {
        String name = tempCourse.getName();
        String time = tempCourse.getTime();
        String place = tempCourse.getPlace();
        String school = tempCourse.getSchool();
        String day = tempCourse.getDay();
        Teacher teacher = teacherService.findById(Long.parseLong(tempCourse.getTeacherId())).getTeacher();
        String preRequirements = tempCourse.getPreRequirements();
        Course newCourse = new Course(name, time, place, school, day, teacher, preRequirements);
        return save(newCourse);

    }

    @Override
    public TempCourse convertToTempCourse(Course course) {
        String name = course.getName();
        String time = course.getTime();
        String day = course.getDay();
        String place = course.getPlace();
        String school = course.getSchool();
        Teacher teacher = course.getTeacher();
        String preRequirements = course.getPreRequirements();
        return new TempCourse(name, time, place, school, day, null, teacher, preRequirements);
    }

    @Override
    public void updateCourse(TempCourse tempCourse, long id) {
        Course course = findById(id);
        course.setName(tempCourse.getName());
        course.setTime(tempCourse.getTime());
        course.setDay(tempCourse.getDay());
        course.setPlace(tempCourse.getPlace());
        course.setSchool(tempCourse.getSchool());
        course.setPreRequirements(tempCourse.getPreRequirements());
        course.setTeacher(teacherService.findById(Long.parseLong(tempCourse.getTeacherId())).getTeacher());
        save(course);
    }

    @Override
    public List<Course> findAllFIHCourses() {
        List<Course> allCourses = findAll();
        List<Course> FIHCourses = new ArrayList<Course>();
        for(Course course : allCourses){
            if(course.getSchool().equals("FÍH")){
                FIHCourses.add(course);
            }
        }
        return FIHCourses;
    }

    @Override
    public List<Course> findAllMITCourses() {
        List<Course> allCourses = findAll();
        List<Course> MITCourses = new ArrayList<>();
        for(Course course : allCourses){
            if(course.getSchool().equals("MÍT")){
                MITCourses.add(course);
            }
        }
        return MITCourses;
    }
}
