package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(Long id);
    DepartmentResponse getDepartmentByOrganizationId(Long organizationId);
    Department addDepartment(DepartmentRequest request);
    DepartmentResponse getDepartmentByOrganizationIdWithEmployees(Long organizationId);
}
