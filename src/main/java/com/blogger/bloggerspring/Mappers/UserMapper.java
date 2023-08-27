package com.blogger.bloggerspring.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.blogger.bloggerspring.Dtos.AuthRegisterDto;
import com.blogger.bloggerspring.Dtos.UserDto;
import com.blogger.bloggerspring.Entities.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    User mapToUser(AuthRegisterDto registerDto);
}
