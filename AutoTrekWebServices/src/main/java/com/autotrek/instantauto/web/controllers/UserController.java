package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.services.UserService;
import com.autotrek.instantauto.web.dto.converters.UserToUserDtoConverter;
import com.autotrek.instantauto.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Joe C. McPherson
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserToUserDtoConverter userToUserDtoConverter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        // This is not the best implementation. We should be paginating
        // these because this will probably get larger. When we get to
        // that functionliaty this will be refactored a bit.
        List<User> users = userService.getAllUsers();
        ArrayList<UserDto> ret = new ArrayList<>();

        users.stream().map((user) -> {
            UserDto dto = userToUserDtoConverter.convert(user);
            return dto;
        }).forEachOrdered((dto) -> {
            ret.add(dto);
        });

        return ret;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserDto getUserInfo(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            UserDto dto = userToUserDtoConverter.convert(user);
            return dto;
        } else {
            throw ApiExceptionBuilder.NOT_FOUND.build();
        }
    }
}
