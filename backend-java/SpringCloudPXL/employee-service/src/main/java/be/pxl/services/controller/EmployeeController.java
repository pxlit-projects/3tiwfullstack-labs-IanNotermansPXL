package be.pxl.services.controller;

import be.pxl.services.controller.input.CreateEmployeeCommand;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.services.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity getEmployees() {
        return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity getEmployeesByDepartmentId(@PathVariable Long departmentId) {
        return new ResponseEntity(employeeService.getEmployeesByDepartmentId(departmentId), HttpStatus.OK);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity getEmployeesByOrganizationId(@PathVariable Long organizationId) {
        return new ResponseEntity(employeeService.getEmployeesByOrganizationId(organizationId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody EmployeeRequest request){
        return new ResponseEntity(employeeService.addEmployee(request), HttpStatus.CREATED);
    }
}
