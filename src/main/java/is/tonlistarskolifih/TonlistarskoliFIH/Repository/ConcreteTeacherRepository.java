package is.tonlistarskolifih.TonlistarskoliFIH.Repository;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.ConcreteTeacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcreteTeacherRepository extends JpaRepository<ConcreteTeacher, Long> {
    ConcreteTeacher save(ConcreteTeacher teacher);
    void deleteById(long id);
    List<ConcreteTeacher> findAll();
    ConcreteTeacher findById(long id);
}
