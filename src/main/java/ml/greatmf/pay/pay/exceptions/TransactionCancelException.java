package ml.greatmf.pay.pay.exceptions;

import lombok.Data;
import lombok.Getter;
import ml.greatmf.pay.pay.dto.UserDto;

@Getter
public class TransactionCancelException extends RuntimeException{
    private UserDto userDto;
    public TransactionCancelException(UserDto userDto){
        super("not enough money to pay");
    }
}
