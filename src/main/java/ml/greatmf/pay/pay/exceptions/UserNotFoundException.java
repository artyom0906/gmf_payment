package ml.greatmf.pay.pay.exceptions;

import lombok.Data;
import lombok.Getter;
import ml.greatmf.pay.pay.dto.UserDto;

@Getter
public class UserNotFoundException extends RuntimeException{
    private UserDto userDto;
    public UserNotFoundException(UserDto userDto){
        super("user not found");
        this.userDto = userDto;
    }
}
