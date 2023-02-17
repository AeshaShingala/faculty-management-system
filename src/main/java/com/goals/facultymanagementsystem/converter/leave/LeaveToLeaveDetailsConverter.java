package com.goals.facultymanagementsystem.converter.leave;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import com.goals.facultymanagementsystem.dto.LeaveDetails;
import com.goals.facultymanagementsystem.entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveToLeaveDetailsConverter implements Converter<Leave, LeaveDetails> {
    @Override
    public @NonNull LeaveDetails convert(Leave source) {
        return new LeaveDetails(
                source.getReason(),
                source.getDate()
        );
    }
}
