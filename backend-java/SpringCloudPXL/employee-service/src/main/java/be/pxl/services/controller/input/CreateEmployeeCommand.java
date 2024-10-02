package be.pxl.services.controller.input;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public class CreateEmployeeCommand {
    private final Long organizationId;
    private final Long departmentId;
    private final String name;
    private final int age;
    private final String position;

    public CreateEmployeeCommand(Long organizationId, Long departmentId, String name, int age, String position) {
        this.organizationId = organizationId;
        this.departmentId = departmentId;
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }
}
