package be.pxl.services.services;

import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.domain.Organization;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .map(this::mapToOrganizationResponse)
                .orElse(null);
    }



    @Override
    public OrganizationResponse getOrganizationByIdWithDepartments(Long id) {
//        return organizationRepository.findOrganizationByIdWithDepartments(id)
//                .map(this::mapToOrganizationResponse)
//                .orElse(null);
        return null;
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithDepartmentsAndEmployees(Long id) {
//        return organizationRepository.getOrganizationByIdWithDepartmentsAndEmployees(id)
//                .map(this::mapToOrganizationResponse)
//                .orElse(null);
        return null;
    }

    @Override
    public OrganizationResponse getOrganizationByIdWithEmployees(Long id) {
//        return organizationRepository.findOrganizationByIdWithEmployees(id)
//                .map(this::mapToOrganizationResponse)
//                .orElse(null);
        return null;
    }

    private OrganizationResponse mapToOrganizationResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .departments(organization.getDepartments())
                .build();
    }
}
