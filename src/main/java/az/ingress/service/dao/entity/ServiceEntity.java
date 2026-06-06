package az.ingress.service.dao.entity;

import az.ingress.service.model.enums.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "display_text_id")
    @ToString.Exclude
    private DisplayTextEntity displayText;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "services_group_id")
    @ToString.Exclude
    private ServicesGroupEntity servicesGroup;

    private String serviceCode;

    private boolean descriptionApplicable;

    private boolean actionApplicable;

    private boolean rateApplicable;

    private boolean tooltipsApplicable;

    private boolean reportApplicable;

    private boolean restrictionApplicable;

    @Enumerated(STRING)
    private ServiceType serviceType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}