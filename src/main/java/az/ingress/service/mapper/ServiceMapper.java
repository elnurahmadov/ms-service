package az.ingress.service.mapper;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServiceEntity;
import az.ingress.service.dao.entity.ServicesGroupEntity;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServiceDetailedResponse;
import az.ingress.service.model.response.ServiceResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

public enum ServiceMapper {
    SERVICE_MAPPER;

    private static final int SERVICE_CODE_MAX_LENGTH = 64;

    public ServiceEntity toEntity(CreateServiceRequest request,
                                  DisplayTextEntity displayText,
                                  ServicesGroupEntity servicesGroup) {

        return ServiceEntity.builder()
                .displayText(displayText)
                .servicesGroup(servicesGroup)
                .serviceCode(toCamelCase(request.getLanguage().getEn()))
                .descriptionApplicable(TRUE.equals(request.getDescriptionApplicable()))
                .actionApplicable(TRUE.equals(request.getActionApplicable()))
                .rateApplicable(TRUE.equals(request.getRateApplicable()))
                .tooltipsApplicable(TRUE.equals(request.getTooltipsApplicable()))
                .reportApplicable(TRUE.equals(request.getReportApplicable()))
                .restrictionApplicable(TRUE.equals(request.getRestrictionApplicable()))
                .serviceType(request.getServiceType())
                .build();
    }

    private String toCamelCase(String text) {

        Set<String> ignoredWords = Set.of(
                "for", "and", "the", "of", "to",
                "a", "an", "in", "on", "with"
        );

        List<String> words = Arrays.stream(
                        text.replaceAll("[^a-zA-Z0-9 ]", "")
                                .trim()
                                .split("\\s+")
                )
                .map(String::toLowerCase)
                .filter(word -> !ignoredWords.contains(word))
                .toList();

        if (words.isEmpty()) {
            return "";
        }

        String camelCase = words.get(0) +
                words.stream()
                        .skip(1)
                        .map(word ->
                                Character.toUpperCase(word.charAt(0)) +
                                        word.substring(1)
                        )
                        .collect(Collectors.joining());

        return camelCase.length() > SERVICE_CODE_MAX_LENGTH
                ? camelCase.substring(0, SERVICE_CODE_MAX_LENGTH)
                : camelCase;
    }

    public List<ServiceResponse> toResponseList(List<ServiceEntity> servicesEntities) {

        return servicesEntities.stream().map(this::toResponse).toList();
    }

    public ServiceResponse toResponse(ServiceEntity entity) {

        DisplayTextEntity displayTextEntity = entity.getDisplayText();

        return ServiceResponse.builder()
                .id(entity.getId())
                .language(Language.builder()
                        .az(displayTextEntity.getAz())
                        .en(displayTextEntity.getEn())
                        .ru(displayTextEntity.getRu())
                        .build())
                .build();
    }

    public ServiceDetailedResponse toDetailedResponse(ServiceEntity entity) {

        DisplayTextEntity displayTextEntity = entity.getDisplayText();

        return ServiceDetailedResponse.builder()
                .id(entity.getId())
                .language(Language.builder()
                        .az(displayTextEntity.getAz())
                        .en(displayTextEntity.getEn())
                        .ru(displayTextEntity.getRu())
                        .build())
                .servicesGroupId(entity.getServicesGroup().getId())
                .serviceCode(entity.getServiceCode())
                .descriptionApplicable(entity.isDescriptionApplicable())
                .actionApplicable(entity.isActionApplicable())
                .rateApplicable(entity.isRateApplicable())
                .tooltipsApplicable(entity.isTooltipsApplicable())
                .reportApplicable(entity.isReportApplicable())
                .restrictionApplicable(entity.isRestrictionApplicable())
                .serviceType(entity.getServiceType())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
