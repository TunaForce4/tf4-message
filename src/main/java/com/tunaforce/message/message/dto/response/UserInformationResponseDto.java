package com.tunaforce.message.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationResponseDto {
    private Long userId;
    private String name;
    private String slackId;
    private String role;
    private String deptId;
}
