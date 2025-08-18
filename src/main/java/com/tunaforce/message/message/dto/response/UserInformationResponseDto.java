package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationResponseDto {
    private UUID userId;
    private String name;
    private String slackId;
    private String role;
    private String deptId;
}
