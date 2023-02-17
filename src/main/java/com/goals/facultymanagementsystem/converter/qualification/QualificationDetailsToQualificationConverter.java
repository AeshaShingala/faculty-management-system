package com.goals.facultymanagementsystem.converter.qualification;

import com.goals.facultymanagementsystem.dto.QualificationDetails;
import com.goals.facultymanagementsystem.entity.Qualification;
import com.goals.facultymanagementsystem.repository.QualificationRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QualificationDetailsToQualificationConverter implements Converter<QualificationDetails, Qualification> {
    private final QualificationRepository qualificationRepository;

    public QualificationDetailsToQualificationConverter(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public Qualification convert(QualificationDetails source) {
        Qualification qualification = qualificationRepository.findById(source.getId()).orElseGet(Qualification::new);
        qualification.setDegree(source.getDegree());
        qualification.setExperience(source.getExperience());
        return qualification;
    }
}
