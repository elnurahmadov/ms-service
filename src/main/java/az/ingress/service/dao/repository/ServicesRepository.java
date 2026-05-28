package az.ingress.service.dao.repository;

import az.ingress.service.dao.entity.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<ServicesEntity, Long> {
}