package com.example.E_Commerce_MicroServices.services.converters;

import com.example.E_Commerce_MicroServices.models.User;
import com.example.E_Commerce_MicroServices.services.dtos.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User user) {
        if (user == null) {
            return null;
        }

        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCountry(),
                user.getCity(),
                user.getStreet(),
                user.getCreditLimit(),
                user.getBirthdate(),
                user.getPhone(),
                user.getDateCreated(),
                user.getLastUpdated()
        );
    }
}
