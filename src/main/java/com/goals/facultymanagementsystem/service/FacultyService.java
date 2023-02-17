package com.goals.facultymanagementsystem.service;

import com.goals.facultymanagementsystem.converter.faculty.FacultyDetailsToFacultyConverter;
import com.goals.facultymanagementsystem.converter.faculty.FacultyToFacultyDetailsConverter;
import com.goals.facultymanagementsystem.converter.leave.LeaveDetailsToLeaveConverter;
import com.goals.facultymanagementsystem.converter.leave.LeaveToLeaveDetailsConverter;
import com.goals.facultymanagementsystem.dto.ApplyLeaveDetails;
import com.goals.facultymanagementsystem.dto.FacultyDetails;
import com.goals.facultymanagementsystem.dto.LeaveDetails;
import com.goals.facultymanagementsystem.dto.QualificationDetails;
import com.goals.facultymanagementsystem.entity.Faculty;
import com.goals.facultymanagementsystem.exception.ResourceNotFoundException;
import com.goals.facultymanagementsystem.repository.FacultyRepository;
import com.goals.facultymanagementsystem.repository.LeaveRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyToFacultyDetailsConverter facultyToFacultyDetailsConverter;
    private final FacultyDetailsToFacultyConverter facultyDetailsToFacultyConverter;
    private final LeaveToLeaveDetailsConverter leaveToLeaveDetailsConverter;
    private final LeaveDetailsToLeaveConverter leaveDetailsToLeaveConverter;
    private final LeaveRepository leaveRepository;

    public FacultyService(FacultyRepository facultyRepository, FacultyToFacultyDetailsConverter facultyToFacultyDetailsConverter, FacultyDetailsToFacultyConverter facultyDetailsToFacultyConverter, LeaveToLeaveDetailsConverter leaveToLeaveDetailsConverter, LeaveDetailsToLeaveConverter leaveDetailsToLeaveConverter,
                          LeaveRepository leaveRepository) {
        this.facultyRepository = facultyRepository;
        this.facultyToFacultyDetailsConverter = facultyToFacultyDetailsConverter;
        this.facultyDetailsToFacultyConverter = facultyDetailsToFacultyConverter;
        this.leaveToLeaveDetailsConverter = leaveToLeaveDetailsConverter;
        this.leaveDetailsToLeaveConverter = leaveDetailsToLeaveConverter;
        this.leaveRepository = leaveRepository;
    }

    public List<FacultyDetails> findAllFaculties(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo != 0 ? pageNo - 1 : 0, pageSize != 0 ? pageSize : 4);
        return facultyRepository.findAll(pageable).getContent().stream().map(facultyToFacultyDetailsConverter::convert).toList();
    }

    public FacultyDetails findFacultyById(Long id) {
        Faculty newFaculty = facultyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
        return facultyToFacultyDetailsConverter.convert(newFaculty);
    }

    public QualificationDetails findQualificationByFacultyId(long facultyId){
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + facultyId + " not found"));
        return facultyRepository.getFacultyByQualification(faculty.getId());
    }

    public ApplyLeaveDetails findAllLeaves(long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + facultyId + " not found"));
        return new ApplyLeaveDetails(faculty.getName(), faculty.getLeaves().stream().map(leaveToLeaveDetailsConverter::convert).toList());
    }

    public FacultyDetails addFaculty(FacultyDetails facultyDetails) {
        Faculty faculty = facultyRepository.save(facultyDetailsToFacultyConverter.convert(facultyDetails));
        return facultyToFacultyDetailsConverter.convert(faculty);
    }

    public ApplyLeaveDetails addLeave(long facultyId, LeaveDetails leaveDetails) {
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new ResourceNotFoundException("User with id: " + facultyId + " not found"));
        faculty.getLeaves().add(leaveRepository.save(leaveDetailsToLeaveConverter.convert(leaveDetails)));
        facultyRepository.save(faculty);
        return new ApplyLeaveDetails(faculty.getName(), faculty.getLeaves().stream().map(leaveToLeaveDetailsConverter::convert).toList());
    }

    public FacultyDetails updateFacultyById( FacultyDetails facultyDetails) throws ResourceNotFoundException {
        Faculty updateFaculty = facultyRepository.findById(facultyDetails.getId()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + facultyDetails.getId() + " not found"));
        facultyDetails.setId(updateFaculty.getId());
        return facultyToFacultyDetailsConverter.convert(facultyRepository.save(facultyDetailsToFacultyConverter.convert(facultyDetails)));
    }

    public FacultyDetails deleteFacultyById(Long id) throws ResourceNotFoundException {
        Faculty currentFaculty = facultyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " not found"));
        facultyRepository.deleteById(id);
        return facultyToFacultyDetailsConverter.convert(currentFaculty);
    }
}
