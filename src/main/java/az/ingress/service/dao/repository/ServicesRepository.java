package az.ingress.service.dao.repository;

import az.ingress.service.dao.entity.ServicesEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {

    @Override
    @EntityGraph(attributePaths = "displayText")
    List<ServicesEntity> findAll();
}