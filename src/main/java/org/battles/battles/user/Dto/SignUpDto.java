package org.battles.battles.user.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpDto {

    private String email;

    private String name;

    private String nickName;

    private String schoolName;

    private String phoneNumber;

}
