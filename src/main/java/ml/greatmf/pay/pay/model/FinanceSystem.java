package ml.greatmf.pay.pay.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

@Data
@NoArgsConstructor
@Table("systems")
public class FinanceSystem {
    @Id
    private Long id;

    private String coinName;

    private String token;
}
