package thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thymeleaf.model.Course;
import thymeleaf.model.CourseRequest;
import thymeleaf.service.CompanyService;
import thymeleaf.service.CourseService;

@Controller
@RequestMapping("/courses/{id}")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService) {
        this.courseService = courseService;
        this.companyService = companyService;
    }

    @GetMapping()
    public String coursesOfCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("courseList", courseService.findALLByCompanyId(id));
        model.addAttribute("companyId", id);
        return "courses/getCourses";
    }

//    @GetMapping("/save/{companyId}")
//    public String showCourseSavePage(@PathVariable long companyId, Model model) {
//        model.addAttribute("companyId",companyId);
//        model.addAttribute("emptyCourse", new Course());
//        return "courses/newCourses";
//    }
//
//    @PostMapping("/save/{companyId}")
//    public String saveCourse(Course course, @PathVariable long companyId) {
//        courseService.saveCourse(course);
//        return "redirect:/courses//find/by/{id}";
//    }


//
//    @GetMapping("/course-delete/{id}")
//    public String deleteCourse(@PathVariable("id") long id){
//        courseService.deleteById(id);
//        return "redirect:/api/companies";
//    }


//

    @GetMapping("/addCourse")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        return "courses/addCourses";
    }


    @PostMapping("/saveCourse")
    public String saveCourses(CourseRequest requestCourse,
                              @PathVariable("id") Long companyId) {
        courseService.saveCourse(companyId, requestCourse);
        return "redirect:/courses/" + companyId;
    }

}
