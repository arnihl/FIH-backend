package is.tonlistarskolifih.TonlistarskoliFIH.Controller;

import is.tonlistarskolifih.TonlistarskoliFIH.Entity.*;
import is.tonlistarskolifih.TonlistarskoliFIH.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AdminController {

    UserService userService;
    TeacherService teacherService;
    FileService fileService;
    NewsStoryService newsStoryService;
    CourseService courseService;
    @Autowired
    public AdminController(UserService userService, TeacherService teacherService, FileService fileService, NewsStoryService newsStoryService, CourseService courseService) {
        this.userService = userService;
        this.teacherService = teacherService;
        this.fileService = fileService;
        this.newsStoryService = newsStoryService;
        this.courseService = courseService;
    }

    //makes empty values in form as null
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(MultipartFile.class, new StringTrimmerEditor(true));
    }

    public boolean isLoggedIn(HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");
        if(user == null){
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/fih-admin", method = RequestMethod.GET)
    public String LoginGET(User user, BindingResult result, HttpSession session, Model model){
        if(isLoggedIn(session)){
            return "adminpage";
        }
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/fih-admin", method = RequestMethod.POST)
    public String LoginPOST(User user, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            return "redirect:/fih-admin";
        }
        User loggedInUser = userService.login(user);
        if( loggedInUser != null){
            session.setAttribute("loggedInUser", loggedInUser);
            return "adminpage";
        }
        return "redirect:/fih-admin";
    }

    @RequestMapping(value = "/fih-admin/kennarar", method = RequestMethod.GET)
    public String KennararAdminGet(Model model, HttpSession session){
        if(!isLoggedIn(session)){
            System.out.println("admin/kennarar" + session.getAttribute("loggedInUser"));
            return "redirect:/";
        }
        model.addAttribute("teachers", teacherService.findAll());
        return "admin-kennarar";
    }

    @RequestMapping(value = "/fih-admin/kennarar/add", method = RequestMethod.GET)
    public String KennararAddGet(TempTeacher teacher, HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        model.addAttribute("teacher", new TempTeacher());
        return "admin-kennarar-add";

    }

    @RequestMapping(value= "/fih-admin/kennarar/add", method = RequestMethod.POST)
    public String KennararAddPost(@Valid TempTeacher teacher, HttpSession session, BindingResult result){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            return "redirect:/fih-admin/kennarar/add";
        }
        // reyna að fá inn fæl sem byte
        fileService.uploadFile(teacher.getImg(), "k");
        teacherService.saveTempTeacher(teacher);
        return "redirect:/fih-admin/kennarar";

    }

    @RequestMapping(value="/fih-admin/kennarar/delete/{id}", method = RequestMethod.GET)
    public String KennararDeleteGet(@PathVariable("id") String id, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(teacherService.findById(Long.parseLong(id)) == null) return "redirect:/fih-admin/kennarar";
        teacherService.deleteById(Long.parseLong(id));
        return "redirect:/fih-admin/kennarar";
    }

    @RequestMapping(value="/fih-admin/kennarar/change/{id}", method = RequestMethod.GET)
    public String KennararChangeGet(@PathVariable("id") String id, HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(teacherService.findById(Long.parseLong(id)) == null) {
            return "redirect:/fih-admin/kennarar";
        }



        // finna concrete teacher
        // converta í tempteacher og senda hann sem modelið.
        // teacher2 er bara notaður til að birta myndina.
        ConcreteTeacher teacher2 = teacherService.findById(Long.parseLong(id));
        TempTeacher tempTeacher = teacherService.convertToTempTeacher(teacher2);
        model.addAttribute("teacher2", teacher2);
        model.addAttribute("teacher", tempTeacher);
        return "admin-kennarar-change";
    }

    @RequestMapping(value="/fih-admin/kennarar/change/{id}", method = RequestMethod.POST)
    public String KennararChangePost(@PathVariable("id") String id, @Valid TempTeacher teacher, BindingResult result, HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            // reyna að setja á model message. ekki voru allir reitir fylltir út.
            return "redirect:/fih-admin/kennarar/change/" + id;
        }

        teacherService.updateTeacher(teacher, Long.parseLong(id));
        return "redirect:/fih-admin/kennarar";
    }

    @RequestMapping(value = "/fih-admin/frettir", method = RequestMethod.GET)
    public String FrettirAdminGet(Model model, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        model.addAttribute("stories", newsStoryService.findAll());
        return "admin-frettir";
    }

    @RequestMapping(value = "/fih-admin/frettir/add", method = RequestMethod.GET)
    public String FrettirAddGet(Model model, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        model.addAttribute("newsStory", new TempNewsStory());
        return "admin-frettir-add";
    }

    @RequestMapping(value = "/fih-admin/frettir/add", method = RequestMethod.POST)
    public String FrettirAddPost(@Valid TempNewsStory newsStory, HttpSession session, BindingResult result){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            return "redirect:/fih-admin/frettir/add";
        }
        fileService.uploadFile(newsStory.getImg(), "f");
        newsStoryService.saveTempNewsStory(newsStory);
        return "redirect:/fih-admin/frettir";
    }

    @RequestMapping(value = "/fih-admin/frettir/change/{id}", method = RequestMethod.GET)
    public String FrettirChangeGet(@PathVariable("id") String id, HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(newsStoryService.findById(Long.parseLong(id)) == null) return "redirect:/fih-admin/frettir";

        // Birta mynd, birta story sem tempstory
        NewsStory realStory = newsStoryService.findById(Long.parseLong(id));
        TempNewsStory tempStory = newsStoryService.convertToTempNewsStory(realStory);
        model.addAttribute("tempStory", tempStory);
        model.addAttribute("realStory", realStory);
        return "admin-frettir-change";
    }

    @RequestMapping(value = "/fih-admin/frettir/change/{id}", method = RequestMethod.POST)
    public String FrettirChangePost(@PathVariable("id") String id, @Valid TempNewsStory tempStory, BindingResult result, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            return "redirect:/fih-admin/frettir/change/" + id;
        }

        newsStoryService.updateStory(tempStory, Long.parseLong(id));
        return "redirect:/fih-admin/frettir";
    }

    @RequestMapping(value="/fih-admin/frettir/delete/{id}", method = RequestMethod.GET)
    public String FrettirDeleteGet(@PathVariable("id") String id, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(newsStoryService.findById(Long.parseLong(id)) == null) return "redirect:/fih-admin/frettir";
        newsStoryService.deleteById(Long.parseLong(id));
        return "redirect:/fih-admin/frettir";
    }

    /*
     * ÁFANGAR
     */

    @RequestMapping(value = "/fih-admin/afangar")
    public String AfangarAdminGet(Model model, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        model.addAttribute("courses", courseService.findAll());
        return "admin-afangar";
    }

    @RequestMapping(value = "/fih-admin/afangar/add", method = RequestMethod.GET)
    public String AfangarAddGet(HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("course", new TempCourse());
        return "admin-afangar-add";
    }

    @RequestMapping(value = "/fih-admin/afangar/add", method = RequestMethod.POST)
    public String AfangarAddPost(@Valid TempCourse tempCourse, HttpSession session, BindingResult result){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            return "redirect:/fih-admin/afangar/add";
        }
        courseService.saveTempCourse(tempCourse);
        return "redirect:/fih-admin/afangar";
    }

    @RequestMapping(value="/fih-admin/afangar/change/{id}", method = RequestMethod.GET)
    public String AfangarChangeGet(@PathVariable("id") String id, HttpSession session, Model model){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        Course course = courseService.findById(Long.parseLong(id));
        if(course == null) return "redirect:/fih-admin/afangar";

        model.addAttribute("realCourse", courseService.findById(Long.parseLong(id)));
        model.addAttribute("course", courseService.convertToTempCourse(course));
        model.addAttribute("teachers", teacherService.findAll());
        return "admin-afangar-change";
    }

    @RequestMapping(value = "/fih-admin/afangar/change/{id}", method = RequestMethod.POST)
    public String AfangarChangePost(@PathVariable("id") String id, @Valid TempCourse tempCourse, HttpSession session, BindingResult result){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        if(result.hasErrors()){
            return "redirect:/fih-admin/afangar/change/" + id;
        }
        courseService.updateCourse(tempCourse, Long.parseLong(id));
        return "redirect:/fih-admin/afangar";
    }

    @RequestMapping(value = "/fih-admin/afangar/delete/{id}", method = RequestMethod.GET)
    public String AfangarDeleteGet(@PathVariable("id") String id, HttpSession session){
        if(!isLoggedIn(session)){
            return "redirect:/";
        }
        Course course = courseService.findById(Long.parseLong(id));
        if(course == null){
            return "redirect:/fih-admin/afangar";
        }
        courseService.deleteById(Long.parseLong(id));
        return "redirect:/fih-admin/afangar";
    }


}
