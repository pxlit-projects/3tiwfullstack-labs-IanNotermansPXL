package be.pxl.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
public class DepartmentTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer(("mysql:5.7.37"));

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testCreateDepartment() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("John Doe")
                .position("Developer")
                .departmentId(1L)
                .organizationId(1L)
                .build();

        Employee employee2 = Employee.builder()
                .age(21)
                .name("Jane Doe")
                .position("Manager")
                .departmentId(1L)
                .organizationId(1L)
                .build();

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee2);

        Department department = Department.builder()
                .name("IT")
                .employees(employees)
                .organizationId(1L)
                . position("stuffs")
                .build();

        String departmentString = objectMapper.writeValueAsString(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/department")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(departmentString))
                .andExpect(status().isCreated());

        assertEquals(1, departmentRepository.findAll().size());
    }

    @Test
    public void testGetDepartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/department"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Department department = departmentRepository.save(Department.builder()
                .name("IT")
                .organizationId(1L)
                .position("stuffs")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/" + department.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentByOrganizationId() throws Exception {
        Department department = departmentRepository.save(Department.builder()
                .name("IT")
                .organizationId(1L)
                .position("stuffs")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDepartmentByOrganizationIdWithEmployees() throws Exception {
        Department department = departmentRepository.save(Department.builder()
                .name("IT")
                .organizationId(1L)
                .position("stuffs")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/department/organization/1/with-employees"))
                .andExpect(status().isOk());
    }

}
