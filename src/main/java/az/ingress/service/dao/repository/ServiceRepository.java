package az.ingress.service.dao.repository;

import az.ingress.service.dao.entity.ServiceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

    @EntityGraph(attributePaths = "displayText")
    List<ServiceEntity> findAllWithDisplayText();

    @EntityGraph(attributePaths = {"displayText", "servicesGroup"})
    Optional<ServiceEntity> findDetailedById(Long id);

    @EntityGraph(attributePaths = "displayText")
    List<ServiceEntity> getServicesByServicesGroupId(Long id);
}