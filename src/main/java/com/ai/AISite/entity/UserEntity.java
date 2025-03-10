package com.ai.AISite.entity;


import com.ai.AISite.dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String userEmail;

    @Column
    private String userNickname;

    @Column
    private String userBirth;

    @Column
    private String userPassword;

    @Column
    private String userPhone;

    @Column
    private char userGender;

    private Integer userAble;

    private UUID userUuid;

    private Timestamp createdTime;

    public static UserEntity toUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNickname(userDto.getUserNickname());
        userEntity.setUserEmail(userDto.getUserEmail());
        userEntity.setUserPassword(userDto.getUserPassword());
        userEntity.setUserBirth(userDto.getUserBirth());

        // 전화번호에서 숫자만 추출
        String cleanedPhoneNumber = userDto.getUserPhone().replaceAll("[^0-9]", "");
        userEntity.setUserPhone(cleanedPhoneNumber);

        userEntity.setUserGender(userDto.getUserGender());
        userEntity.setUserAble(1);
        userEntity.setUserUuid(UUID.fromString(UUID.randomUUID().toString()));
        return userEntity;
    }
}
