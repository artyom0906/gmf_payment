package ml.greatmf.pay.pay.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PayCompleteDto {
    private final UserDto from;
    private final UserDto to;
}
