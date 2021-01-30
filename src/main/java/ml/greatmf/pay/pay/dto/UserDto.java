package ml.greatmf.pay.pay.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private final Long id;
    private final Long money;

    public UserDto(Long id) {
        this(id, -1L);
    }
}
