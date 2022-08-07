package org.battles.battles.user;

import lombok.RequiredArgsConstructor;
import org.battles.battles.response.ResponseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final ResponseService responseService;

}
