package be.pxl.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
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
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@Transactional
public class EmployeeTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Container
    private static MySQLContainer sqlContainer = new MySQLContainer(("mysql:5.7.37"));

    @DynamicPropertySource
    static void registerMySQLProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee employee = Employee.builder()
                .age(24)
                .name("John Doe")
                .position("Developer")
                .build();

        String employeeString = objectMapper.writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeString))
                .andExpect(status().isCreated());

        assertEquals(1, employeeRepository.findAll().size());
    }

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee = employeeRepository.save(Employee.builder()
                .age(24)
                .name("John Doe")
                .position("Developer")
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/" + employee.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeesByDepartmentId() throws Exception {
        Employee employee = employeeRepository.save(Employee.builder()
                .age(24)
                .name("John Doe")
                .position("Developer")
                .departmentId(1L)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/department/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetEmployeesByOrganizationId() throws Exception {
        Employee employee = employeeRepository.save(Employee.builder()
                .age(24)
                .name("John Doe")
                .position("Developer")
                .organizationId(1L)
                .build());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/organization/1"))
                .andExpect(status().isOk());
    }
}
