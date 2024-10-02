package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService implements  IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapEmployeeToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setOrganizationId(request.getOrganizationId());
        employee.setDepartmentId(request.getDepartmentId());
        employee.setName(request.getName());
        employee.setAge(request.getAge());
        employee.setPosition(request.getPosition());
        Employee savedEmployee = employeeRepository.save(employee);
        return mapEmployeeToResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::mapEmployeeToResponse)
                .orElse(null);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(this::mapEmployeeToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeResponse> getEmployeesByOrganizationId(Long organizationId) {
        return employeeRepository.findByOrganizationId(organizationId).stream()
                .map(this::mapEmployeeToResponse)
                .collect(Collectors.toList());
    }

    private EmployeeResponse mapEmployeeToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .organizationId(employee.getOrganizationId())
                .departmentId(employee.getDepartmentId())
                .name(employee.getName())
                .age(employee.getAge())
                .position(employee.getPosition())
                .build();
    }
}
