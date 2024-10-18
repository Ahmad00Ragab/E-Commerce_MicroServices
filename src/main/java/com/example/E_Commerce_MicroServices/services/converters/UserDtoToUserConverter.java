package com.example.E_Commerce_MicroServices.services.converters;

import com.example.E_Commerce_MicroServices.models.User;
import com.example.E_Commerce_MicroServices.services.dtos.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.id());
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setEmail(userDto.email());
        user.setCountry(userDto.country());
        user.setCity(userDto.city());
        user.setStreet(userDto.street());
        user.setCreditLimit(userDto.creditLimit());
        user.setBirthdate(userDto.birthdate());
        user.setPhone(userDto.phone());
        user.setDateCreated(userDto.dateCreated());
        user.setLastUpdated(userDto.lastUpdated());

        return user;
    }
}
