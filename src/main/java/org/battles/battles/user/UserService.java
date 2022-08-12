package org.battles.battles.user;

import static java.util.Objects.nonNull;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.battles.battles.common.ValidStringUtils;
import org.battles.battles.exception.exception.CEmailExistedException;
import org.battles.battles.exception.exception.CNicknameExistedException;
import org.battles.battles.exception.exception.CNotSchoolEmailException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.battles.battles.user.Dto.SignUpDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.battles.battles.common.ValidStringUtils.*;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User signin(String email) {
        return userRepository.findByEmail(email).orElseThrow(CUserNotFoundException::new);
    }

    @Transactional
    public User signUp(SignUpDto signUpDto) {
        if (userRepository.findByEmail(signUpDto.getEmail()).isPresent()) {
            throw new CEmailExistedException();
        }
        if (!checkSchoolEmail(signUpDto.getEmail()) && nonNull(
            ValidStringUtils.getValidEmail(signUpDto.getEmail()))) {
            throw new CNotSchoolEmailException();
        }
        if (userRepository.findUserByNickName(signUpDto.getNickName()).isPresent()) {
            throw new CNicknameExistedException();
        }
        // 학교 추가 로직
        User newUser = User.builder()
            .email(ValidStringUtils.getValidEmail(signUpDto.getEmail()))
            .name(signUpDto.getName())
            .nickName(signUpDto.getNickName())
//            .schoolId()
            .phoneNumber(signUpDto.getPhoneNumber())
            .role(Role.USER)
            .status(UserStatus.ALIVE)
            .build();
        return userRepository.save(newUser);
    }

    @Transactional
    public boolean isExistedNickName(String nickName) {
        return userRepository.findUserByNickName(nickName).isEmpty();
    }

    private boolean checkSchoolEmail(String email) {
        return email.contains("ac.kr") || email.contains("edu");
    }

}
