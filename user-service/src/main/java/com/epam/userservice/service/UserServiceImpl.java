package com.epam.userservice.service;

import com.epam.userservice.dto.UserDto;
import com.epam.userservice.entity.User;
import com.epam.userservice.exception.DuplicateUserException;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public List<UserDto> getAllUsers() {

        List<User> allUsers = userRepository.findAll();
        List<UserDto> allUserDto = new ArrayList<>();
        allUsers.forEach(user -> allUserDto.add(modelMapper.map(user , UserDto.class)));
        return allUserDto;
    }

    @Override
    public UserDto getUserByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userName);
        if(user.isEmpty()){
            throw new UserNotFoundException("user with this username does not exist");
        }
        return modelMapper.map(user.get() , UserDto.class);
    }

    @Override
    public void saveUser(UserDto userDto) throws DuplicateUserException {
        User user = modelMapper.map(userDto ,User.class );
        if(userRepository.existsById(user.getUserName())){
            throw new DuplicateUserException("User Already Exist");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(String userName, UserDto userDto) throws UserNotFoundException {
        UserDto user1 = getUserByUserName(userName);
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        User user = modelMapper.map(user1 , User.class);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) throws UserNotFoundException {
        UserDto userDto = getUserByUserName(userName);
        User user = modelMapper.map(userDto , User.class);
        userRepository.delete(user);
    }
}
