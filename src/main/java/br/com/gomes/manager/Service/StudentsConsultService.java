package br.com.gomes.manager.Service;

import br.com.gomes.manager.Model.Students;
import br.com.gomes.manager.Repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class StudentsConsultService {

    @Autowired
    private StudentsRepository studentsRepository;

    public ModelAndView searchFilter(String searchBar, String format, String courses, String status, String shift, Model model) {
            ModelAndView returnURL = null;
        if(!searchBar.isEmpty() && format.equals("name") && courses.equals("NONE") && status.equals("none") && shift.equals("none")) {
            returnURL = consultNameSearch(searchBar, model);
        } else if(!searchBar.isEmpty() && format.equals("register") && courses.equals("NONE") && status.equals("none") && shift.equals("none")) {
            returnURL = consultRegisterSearch(searchBar, model);
        } else if(searchBar.isEmpty() && format.equals("none") && courses.equals("NONE") && status.equals("none") && shift.equals("none")) {
            returnURL = consultAllStudents(model);
        } else if (searchBar.isEmpty() && format.equals("none") && !courses.equals("NONE") && status.equals("none") && shift.equals("none")) {
            returnURL = consultCourseSearch(courses, model);
        } else if(searchBar.isEmpty() && format.equals("none") && courses.equals("NONE") && !status.isEmpty() && shift.equals("none"))  {
            returnURL = consultStatusSearch(status, model);
        } else if(searchBar.isEmpty() && format.equals("none") && courses.equals("NONE") && status.equals("none") && !shift.isEmpty()){
            returnURL = consultShiftSearch(shift, model);
        } else {
            returnURL = consultAllStudents(model);
        }
        return returnURL;
    }

    public ModelAndView consultNameSearch(String name, Model model) {
        List<Students> resultConsult = studentsRepository.findByNameContainingIgnoreCase(name);
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }

    public ModelAndView consultRegisterSearch(String register, Model model) {
        List<Students> resultConsult = studentsRepository.findAllByRegistration(register);
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }

    public ModelAndView consultAllStudents(Model model) {
        List<Students> resultConsult = studentsRepository.findAll();
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }

    public ModelAndView consultCourseSearch(String course, Model model) {
        List<Students> resultConsult = studentsRepository.findByCourse(course);
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }
    public ModelAndView consultStatusSearch(String status, Model model) {
        List<Students> resultConsult = studentsRepository.findByStatus(status);
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }

    public ModelAndView consultShiftSearch(String shift, Model model) {
        List<Students> resultConsult = studentsRepository.findByShift(shift);
        ModelAndView modelAndView = new ModelAndView();
        if(resultConsult.isEmpty()) {
            errorConsultEmpty(model);
        }
        modelAndView.addObject("ListStudents", resultConsult);
        modelAndView.setViewName("consult-pages/consult-students");
        return modelAndView;
    }


    public String errorConsultEmpty(Model model) {
        model.addAttribute("errorMessageConsult", true);
        return "consult-pages/consult-students";
    }
    public String saveUpdate(Model model) {
        model.addAttribute("saveStudent", true);
        return "consult-pages/consult-students";
    }


}
