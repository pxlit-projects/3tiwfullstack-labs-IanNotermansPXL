package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {
    //private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().map(this::mapDepartmentToResponse).toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(this::mapDepartmentToResponse).orElse(null);
    }

    @Override
    public DepartmentResponse getDepartmentByOrganizationId(Long organizationId) {
        Optional<Department> department = departmentRepository.findByOrganizationId(organizationId);
        return department.map(this::mapDepartmentToResponse).orElse(null);
    }

    @Override
    public Department addDepartment(DepartmentRequest request) {
        Department department = new Department();
        department.setOrganizationId(request.getOrganizationId());
        department.setName(request.getName());
        department.setPosition(request.getPosition());
        department.setEmployees(request.getEmployees());
        return departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse getDepartmentByOrganizationIdWithEmployees(Long organizationId) {
        Optional<Department> department = departmentRepository.findByOrganizationId(organizationId);
        List<Employee> employees = Collections.emptyList();//employeeRepository.findByDepartmentId(department.getId());
        department.get().setEmployees(employees);
        return department.map(this::mapDepartmentToResponse).orElse(null);
    }

    private DepartmentResponse mapDepartmentToResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .position(department.getPosition())
                .employees(department.getEmployees())
                .build();
    }
}
