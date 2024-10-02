package be.pxl.services.controller;

import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.services.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity getDepartments() {
        return new ResponseEntity(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDepartmentById(@PathVariable Long id) {
        return new ResponseEntity(departmentService.getDepartmentById(id), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity getDepartmentByOrganizationId(@PathVariable Long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentByOrganizationId(organizationId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public ResponseEntity getDepartmentByOrganizationIdWithEmployees(@PathVariable Long organizationId) {
        return new ResponseEntity(departmentService.getDepartmentByOrganizationIdWithEmployees(organizationId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody DepartmentRequest request){
        return new ResponseEntity(departmentService.addDepartment(request), HttpStatus.CREATED);
    }
}
