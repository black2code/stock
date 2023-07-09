package com.example.stock.user.service;

import com.example.stock.user.domain.User;
import com.example.stock.user.dto.AddUserRequest;
import com.example.stock.user.dto.SinupUserResponse;
import com.example.stock.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SinupUserResponse createUser(AddUserRequest dto) {
        User user = userRepository.save(User.builder()
                .name(dto.getName())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .birthday(dto.getBirthday())
                .build());

        SinupUserResponse sinupUserResponse = new SinupUserResponse();
        sinupUserResponse.setId(user.getId());
        sinupUserResponse.setName(user.getName());
        return sinupUserResponse;
    }

//    public void deleteUser()
}
