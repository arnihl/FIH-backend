package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

import javax.persistence.*;

@Entity
public class ConcreteTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Teacher teacher;

    public ConcreteTeacher(){

    }

    public ConcreteTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

