package com.goals.facultymanagementsystem.controller;

import com.goals.facultymanagementsystem.dto.FacultyDetails;
import com.goals.facultymanagementsystem.dto.GenericResponse;
import com.goals.facultymanagementsystem.dto.LeaveDetails;
import com.goals.facultymanagementsystem.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse> getAllFaculties(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculties found successfully", facultyService.findAllFaculties(pageNo, pageSize), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getFacultyById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculty found successfully", (facultyService.findFacultyById(id)), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/qualification/{id}")
    public ResponseEntity<GenericResponse> getQualificationByFacultyId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculty found successfully", (facultyService.findQualificationByFacultyId(id)), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @GetMapping("/leaves/{id}")
    public ResponseEntity<GenericResponse> getLeavesByFacultyId(@PathVariable("id") Long facultyId) {
        return new ResponseEntity<>(new GenericResponse(true, "Leaves found successfully", facultyService.findAllLeaves(facultyId), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GenericResponse> addFaculty(@RequestBody FacultyDetails facultyDetails) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculty added successfully", facultyService.addFaculty(facultyDetails), HttpStatus.CREATED.value(), LocalDateTime.now()), HttpStatus.CREATED);
    }

    @PostMapping("/leaves/{facultyId}")
    public ResponseEntity<GenericResponse> applyLeave(@PathVariable long facultyId, @RequestBody LeaveDetails leaveDetails) {
        return new ResponseEntity<>(new GenericResponse(true, "Leave added successfully", facultyService.addLeave(facultyId, leaveDetails), HttpStatus.CREATED.value(), LocalDateTime.now()), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<GenericResponse> updateFaculty(@RequestBody FacultyDetails facultyDetails) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculty updated successfully", facultyService.updateFacultyById(facultyDetails), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse> deleteFaculty(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new GenericResponse(true, "Faculty deleted successfully", facultyService.deleteFacultyById(id), HttpStatus.OK.value(), LocalDateTime.now()), HttpStatus.OK);
    }
}
