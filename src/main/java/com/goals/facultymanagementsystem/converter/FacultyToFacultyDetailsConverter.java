package com.goals.facultymanagementsystem.converter;

import com.goals.facultymanagementsystem.dto.FacultyDetails;
import com.goals.facultymanagementsystem.entity.Faculty;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyToFacultyDetailsConverter implements Converter<Faculty, FacultyDetails> {

    private final QualificationToQualificationDetailsConverter qualificationToQualificationDetailsConverter;


    public FacultyToFacultyDetailsConverter(QualificationToQualificationDetailsConverter qualificationToQualificationDetailsConverter) {
        this.qualificationToQualificationDetailsConverter = qualificationToQualificationDetailsConverter;
    }

    @Override
    public @NonNull FacultyDetails convert(Faculty source) {
        return new FacultyDetails(
                source.getId(),
                source.getName(),
                source.getEmail(),
                source.getSubject(),
                qualificationToQualificationDetailsConverter.convert(source.getQualification())
        );
    }
}
