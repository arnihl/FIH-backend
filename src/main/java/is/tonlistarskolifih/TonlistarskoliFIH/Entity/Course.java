package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String time;
    private String place;
    private String school;
    private String day;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "teacher_name"))
    })
    private Teacher teacher;
    private String preRequirements;

    public Course(){

    }

    public Course(String name, String time, String place, String school, String day, Teacher teacher, String preRequirements) {
        this.name = name;
        this.time = time;
        this.place = place;
        this.school = school;
        this.day = day;
        this.teacher = teacher;
        this.preRequirements = preRequirements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String preRequirements) {
        this.preRequirements = preRequirements;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
