package com.goals.facultymanagementsystem.converter.leave;

import com.goals.facultymanagementsystem.dto.LeaveDetails;
import com.goals.facultymanagementsystem.entity.Leave;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LeaveDetailsToLeaveConverter implements Converter<LeaveDetails, Leave> {
    @Override
    public @NonNull Leave convert(LeaveDetails source) {
        Leave leave = new Leave();
        leave.setDate(source.getDate());
        leave.setReason(source.getReason());
        return leave;
    }
}
