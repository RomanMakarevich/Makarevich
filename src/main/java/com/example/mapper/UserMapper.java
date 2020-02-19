package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity sourceToDestination(UserDTO source);

    UserDTO destinationToSource(UserEntity destination);
}
