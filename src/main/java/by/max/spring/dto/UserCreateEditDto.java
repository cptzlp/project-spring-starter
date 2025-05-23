package by.max.spring.dto;


import by.max.spring.database.entity.Role;
import by.max.spring.validator.UserInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateEditDto {

    @Email
    String username;

    LocalDate birthDate;


    @Size(min = 2, max = 30)
    String firstname;
    String lastname;
    Role role;
    Integer companyId;
    MultipartFile image;
}
