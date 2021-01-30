package ml.greatmf.pay.pay.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PayDto {
    @NotEmpty
    private String token;

    @NotEmpty
    private Long from;

    @NotNull
    private Long to;

    @NotNull
    private Long amount;
}
