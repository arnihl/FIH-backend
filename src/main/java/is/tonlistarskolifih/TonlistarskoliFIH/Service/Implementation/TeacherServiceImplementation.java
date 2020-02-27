package is.tonlistarskolifih.TonlistarskoliFIH.Service.Implementation;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.ConcreteTeacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.Teacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Entity.TempTeacher;
import is.tonlistarskolifih.TonlistarskoliFIH.Repository.ConcreteTeacherRepository;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.FileService;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    ConcreteTeacherRepository repository;
    FileService fileService;

    @Autowired
    public TeacherServiceImplementation(ConcreteTeacherRepository repository, FileService fileService){
        this.repository = repository;
        this.fileService = fileService;
    }

    // Temp teacher is made when making a new teacher with a photo.
    // However we don't want to store the photo in the database but only as a string
    // referance like in Teacher class.
    @Override
    public ConcreteTeacher saveTempTeacher(TempTeacher teacher) {

        //find teacher with id                                                                                                  // getImg().getOriginialFilename().
        Teacher newTeacher = new Teacher(teacher.getName(), teacher.getAbbreviation(), teacher.getEmail(), teacher.getPhoneNumber(), teacher.getDescription(), teacher.getProfession(), teacher.getImg().getOriginalFilename());
        return save(newTeacher, null);
    }

    public ConcreteTeacher changeTeacher(TempTeacher teacher, long id){
        // find teacher with id. g.r.f að id fylgi með post requesti.

        // if image, change all variables.
        // else change all except image

        // save teacher
        return new ConcreteTeacher();
    }

    // Teacher Class has to be embeddable and therefore cannot be accessible via JPA
    // This method takes class teacher and turns it to ConcreteTeacher
    // which can be fetched.
    @Override
    public ConcreteTeacher save(Teacher teacher, Long id) {
        ConcreteTeacher newTeacher = null;
        if(id==null) {
            newTeacher = new ConcreteTeacher(teacher);
        } else {
            newTeacher = findById(id);
            newTeacher.setTeacher(teacher);
        }
        return repository.save(newTeacher);
    }


    @Override
    public void deleteById(long id) {
        ConcreteTeacher exists = repository.findById(id);
        if(exists != null){
            repository.deleteById(id);
        }
    }

    @Override
    public List<ConcreteTeacher> findAll() {
        return repository.findAll();
    }

    @Override
    public ConcreteTeacher findById(long id) {
        return repository.findById(id);
    }

    @Override
    public TempTeacher convertToTempTeacher(ConcreteTeacher cTeacher) {
        String name = cTeacher.getTeacher().getName();
        String abbreviation = cTeacher.getTeacher().getAbbreviation();
        String email = cTeacher.getTeacher().getEmail();
        String phoneNumber = cTeacher.getTeacher().getPhoneNumber();
        String description = cTeacher.getTeacher().getDescription();
        String profession = cTeacher.getTeacher().getProfession();

        TempTeacher tempTeacher = new TempTeacher(name, abbreviation, email, phoneNumber, description, profession, null);
        return tempTeacher;
    }



    @Override
    public void updateTeacher(TempTeacher teacher, long id) {
        // Ef tempteacher er með mynd
        //      upload-a mynd, Imgref og allt annað í Concreteteacher
        // Ef ekki
        //      Update-a allt í concreteTeacher.
        ConcreteTeacher cTeacher = findById(id);
        if(!teacher.getImg().isEmpty()){
            fileService.uploadFile(teacher.getImg(),"k");
            cTeacher.getTeacher().setImgRef(teacher.getImg().getOriginalFilename());
        }

        cTeacher.getTeacher().setName(teacher.getName());
        cTeacher.getTeacher().setAbbreviation(teacher.getAbbreviation());
        cTeacher.getTeacher().setEmail(teacher.getEmail());
        cTeacher.getTeacher().setPhoneNumber(teacher.getPhoneNumber());
        cTeacher.getTeacher().setDescription(teacher.getDescription());
        cTeacher.getTeacher().setProfession(teacher.getProfession());
        save(cTeacher.getTeacher(), cTeacher.getId());
    }
}
