package org.battles.battles.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResult {

    //    @ApiModelProperty(value = "응답 성공 여부: true/false")
    private boolean success;

    //    @ApiModelProperty(value = "응답 코드 번호: >=0 정상, < 0 비정상")
    private int code;

    //    @ApiModelProperty(value = "응답 메세지")
    private String msg;
}
