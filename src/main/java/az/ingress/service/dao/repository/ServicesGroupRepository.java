package az.ingress.service.dao.repository;

import az.ingress.service.dao.entity.ServicesGroupEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ServicesGroupRepository extends CrudRepository<ServicesGroupEntity, Long> {

    @Override
    @EntityGraph(attributePaths = "displayText")
    List<ServicesGroupEntity> findAll();

    @Override
    @EntityGraph(attributePaths = "displayText")
    Optional<ServicesGroupEntity> findById(Long id);
}