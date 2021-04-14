package il.co.orgo.orgo.controllers;

import il.co.orgo.orgo.dto.UserDto;
import il.co.orgo.orgo.model.User;
import il.co.orgo.orgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminControllerV1(UserService userService) {
        this.userService = userService;
    }

    //TODO probably here need to add something related to token. @PreAuthorize or something....
    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
