package org.battles.battles.user;

import lombok.RequiredArgsConstructor;
import org.battles.battles.common.Status;
import org.battles.battles.exception.exception.CTokenUserNotFoundException;
import org.battles.battles.exception.exception.CUserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(String userEmail, Long userId) {
        if(userRepository.findByEmail(userEmail).isEmpty()){
            throw new CUserNotFoundException();
        }
        User user = userRepository.findByEmail(userEmail).get();
        if (!user.getUserId().equals(userId)) {
            throw new CTokenUserNotFoundException();
        }
        user.setStatus(Status.DELETED);
        userRepository.save(user);
    }

}
