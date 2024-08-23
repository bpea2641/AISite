package com.ai.AISite.service;

import com.ai.AISite.dto.UserDto;
import com.ai.AISite.entity.UserEntity;
import com.ai.AISite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 기능
    public UserEntity registerUser(UserDto userDto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userDto.getUserPassword());
        userDto.setUserPassword(encodedPassword);

        // DTO를 Entity로 변환하여 저장
        UserEntity userEntity = UserEntity.toUserEntity(userDto);
        return userRepository.save(userEntity);
    }

    // 로그인 기능
    public Optional<UserEntity> loginUser(String userEmail, String userPassword) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUserEmail(userEmail);

        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            // 비밀번호 확인
            if (passwordEncoder.matches(userPassword, userEntity.getUserPassword())) {
                return Optional.of(userEntity);
            }
        }
        return Optional.empty();
    }
}
