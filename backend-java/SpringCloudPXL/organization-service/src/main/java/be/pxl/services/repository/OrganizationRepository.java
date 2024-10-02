package be.pxl.services.repository;

import aj.org.objectweb.asm.commons.Remapper;
import be.pxl.services.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
