package is.tonlistarskolifih.TonlistarskoliFIH.Service;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.ConcreteTeacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Teacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempTeacher;

import java.util.List;

public interface TeacherService {
    ConcreteTeacher saveTempTeacher(TempTeacher teacher);
    ConcreteTeacher save(Teacher teacher, Long id);
    void deleteById(long id);
    List<ConcreteTeacher> findAll();
    ConcreteTeacher findById(long id);
    TempTeacher convertToTempTeacher(ConcreteTeacher cTeacher);
    void updateTeacher(TempTeacher teacher, long id);
}
