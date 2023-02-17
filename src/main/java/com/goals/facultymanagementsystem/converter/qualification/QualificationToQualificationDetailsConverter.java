package com.goals.facultymanagementsystem.converter.qualification;

import com.goals.facultymanagementsystem.dto.QualificationDetails;
import com.goals.facultymanagementsystem.entity.Qualification;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class QualificationToQualificationDetailsConverter implements Converter<Qualification, QualificationDetails> {
    @Override
    public QualificationDetails convert(Qualification source) {
        return new QualificationDetails(
                source.getId(),
                source.getDegree(),
                source.getExperience()
        );
    }
}
