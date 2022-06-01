package ru.mail.zelenskaya.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.mail.zelenskaya.app.repository.GroupRepository;
import ru.mail.zelenskaya.app.service.CreateService;
import ru.mail.zelenskaya.app.service.DeleteService;
import ru.mail.zelenskaya.app.service.SelectStudentsService;
import ru.mail.zelenskaya.app.service.model.StudentDTO;
import ru.mail.zelenskaya.app.service.validator.GroupNumberValidator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static ru.mail.zelenskaya.app.controller.ControllerConstantTest.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateService createService;
    @MockBean
    private SelectStudentsService selectService;
    @MockBean
    private DeleteService deleteService;
    @MockBean
    private GroupNumberValidator groupNumberValidator;
    @MockBean
    private GroupRepository groupRepository;

    @Test
    void shouldReturn200WhenWeGetAllStudents() throws Exception {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(TEST_ID)
                .surname(TEST_SURNAME)
                .name(TEST_NAME)
                .patronymic(TEST_PATRONYMIC)
                .birth(TEST_DATE_OF_BIRTH)
                .groupNumber(TEST_GROUP_NUMBER)
                .build();
        List<StudentDTO> students = new ArrayList<>();
        students.add(studentDTO);
        when(selectService.getAll()).thenReturn(students);
        mockMvc.perform(get(URL_TEMPLATE))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404WhenWeBadUrlForGetMethod() throws Exception {
        mockMvc.perform(get(BAD_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn405WhenWeDeleteForGetMethod() throws Exception {
        mockMvc.perform(delete(URL_TEMPLATE))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldReturnMapsToBusinessModelWhenWeGetAllStudents() throws Exception {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(TEST_ID)
                .surname(TEST_SURNAME)
                .name(TEST_NAME)
                .patronymic(TEST_PATRONYMIC)
                .birth(TEST_DATE_OF_BIRTH)
                .groupNumber(TEST_GROUP_NUMBER)
                .build();
        List<StudentDTO> students = new ArrayList<>();
        students.add(studentDTO);
        when(selectService.getAll()).thenReturn(students);
        MvcResult mvcResult = mockMvc.perform(get(URL_TEMPLATE)
                        .content(objectMapper.writeValueAsString(students)))
                .andExpect(view().name(VIEW_NAME_INDEX))
                .andReturn();
        verify(selectService, times(1)).getAll();
        List<StudentDTO> studentsResult = (List<StudentDTO>) mvcResult.getModelAndView().getModel().get(ATTRIBUTE_NAME);
        Assertions.assertFalse(studentsResult.isEmpty());
    }

    @Test
    void shouldStudentWhenWeGetAllStudents() throws Exception {
        StudentDTO studentDTO = StudentDTO.builder()
                .id(TEST_ID)
                .surname(TEST_SURNAME)
                .name(TEST_NAME)
                .patronymic(TEST_PATRONYMIC)
                .birth(TEST_DATE_OF_BIRTH)
                .groupNumber(TEST_GROUP_NUMBER)
                .build();
        List<StudentDTO> students = new ArrayList<>();
        students.add(studentDTO);
        when(selectService.getAll()).thenReturn(students);
        MvcResult mvcResult = mockMvc.perform(get(URL_TEMPLATE)
                        .content(objectMapper.writeValueAsString(students)))
                .andExpect(view().name(VIEW_NAME_INDEX))
                .andReturn();
        List<StudentDTO> studentsResult = (List<StudentDTO>) mvcResult.getModelAndView().getModel().get(ATTRIBUTE_NAME);
        assertThat(studentsResult.get(0).getName()).isEqualToIgnoringWhitespace(TEST_NAME);
    }

    @Test
    void shouldReturn200WhenWeGetShowFormForPost() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE_SHOW_FORM))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_NEW));
    }

    @Test
    void shouldReturn404WhenWeBadUrlGetShowFormForPost() throws Exception {
        mockMvc.perform(get(BAD_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn405WhenWeDeleteForGetShowFormForPost() throws Exception {
        mockMvc.perform(delete(URL_TEMPLATE_SHOW_FORM))
                .andExpect(status().isMethodNotAllowed());
    }


    @Test
    void shouldReturn200WhenWePostNewStudent() throws Exception {
        mockMvc.perform(post(URL_TEMPLATE))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEW_NAME_NEW));
    }

    @Test
    void shouldReturn404WhenWeBadUrlForPostNewStudent() throws Exception {
        mockMvc.perform(post(BAD_URL))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn405WhenWeDeleteFoPostNewStudent() throws Exception {
        mockMvc.perform(delete(URL_TEMPLATE))
                .andExpect(status().isMethodNotAllowed());
    }
}