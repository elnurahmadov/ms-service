package az.ingress.service.mapper;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServicesEntity;
import az.ingress.service.dao.entity.ServicesGroupEntity;
import az.ingress.service.model.request.CreateServiceRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

public enum ServicesMapper {
    SERVICES_MAPPER;

    public ServicesEntity toEntity(CreateServiceRequest request,
                                   DisplayTextEntity displayText,
                                   ServicesGroupEntity servicesGroup) {

        return ServicesEntity.builder()
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
                .limit(4)
                .toList();

        if (words.isEmpty()) {
            return "";
        }

        return words.get(0) +
                words.stream()
                        .skip(1)
                        .map(word ->
                                Character.toUpperCase(word.charAt(0)) +
                                        word.substring(1)
                        )
                        .collect(Collectors.joining());
    }
}
