package ru.mail.zelenskaya.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.mail.zelenskaya.app.service.CreateService;
import ru.mail.zelenskaya.app.service.DeleteService;
import ru.mail.zelenskaya.app.service.SelectStudentsService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;

import java.util.List;

@Controller
@RequestMapping(value = "/students")
@AllArgsConstructor
public class StudentController {
    private final CreateService createService;
    private final SelectStudentsService selectService;
    private final DeleteService deleteService;

    @GetMapping()
    public String getAll(Model model) {
        List<StudentDTO> students = selectService.getAll();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping(value = "/add")
    public String showAddUserForm(@ModelAttribute("student") StudentDTO studentDTO) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") @Validated StudentDTO studentDTO,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        createService.addStudent(studentDTO);
        return "redirect:/students";
    }

    @GetMapping(value = "/{id}")
    public String showDeleteStudent(@PathVariable Long id) {
        deleteService.deleteStudentById(id);
        return "redirect:/students";
    }
}
