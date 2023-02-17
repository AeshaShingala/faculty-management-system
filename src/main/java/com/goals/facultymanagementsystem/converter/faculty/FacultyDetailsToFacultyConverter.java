package com.goals.facultymanagementsystem.converter.faculty;

import com.goals.facultymanagementsystem.converter.qualification.QualificationDetailsToQualificationConverter;
import com.goals.facultymanagementsystem.dto.FacultyDetails;
import com.goals.facultymanagementsystem.entity.Faculty;
import com.goals.facultymanagementsystem.repository.FacultyRepository;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyDetailsToFacultyConverter implements Converter<FacultyDetails, Faculty> {
    private final FacultyRepository facultyRepository;
    private final QualificationDetailsToQualificationConverter qualificationDetailsToQualificationConverter;

    public FacultyDetailsToFacultyConverter(FacultyRepository facultyRepository, QualificationDetailsToQualificationConverter qualificationDetailsToQualificationConverter) {
        this.facultyRepository = facultyRepository;
        this.qualificationDetailsToQualificationConverter = qualificationDetailsToQualificationConverter;
    }

    @Override
    public @NonNull Faculty convert(FacultyDetails source) {
        Faculty faculty = facultyRepository.findById(source.getId()).orElseGet(Faculty::new);
        faculty.setName(source.getName());
        faculty.setEmail(source.getEmail());
        faculty.setSubject(source.getSubject());
        faculty.setQualification(qualificationDetailsToQualificationConverter.convert(source.getQualificationDetails()));
        return facultyRepository.save(faculty);
    }
}
