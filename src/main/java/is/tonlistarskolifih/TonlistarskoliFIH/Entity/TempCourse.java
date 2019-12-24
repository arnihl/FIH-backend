package is.tonlistarskolifih.TonlistarskoliFIH.Entity;

public class TempCourse {
    private String name;
    private String time;
    private String place;
    private String school;
    private String day;
    private String teacherId;
    private Teacher teacher;
    private String preRequirements;

    public TempCourse(){

    }

    public TempCourse(String name, String time, String place, String school, String day, String teacherId, Teacher teacher, String preRequirements) {
        this.name = name;
        this.time = time;
        this.place = place;
        this.school = school;
        this.day = day;
        this.teacherId = teacherId;
        this.teacher = teacher;
        this.preRequirements = preRequirements;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getPreRequirements() {
        return preRequirements;
    }

    public void setPreRequirements(String preRequirements) {
        this.preRequirements = preRequirements;
    }
}
