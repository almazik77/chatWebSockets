package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;


    public static UserDto from(User appUser) {
        return UserDto.builder()
                .id(appUser.getId())
                .email(appUser.getEmail())
                .build();
    }
}
