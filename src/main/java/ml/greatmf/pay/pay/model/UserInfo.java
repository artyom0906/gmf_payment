package ml.greatmf.pay.pay.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Table("user_info")
public class UserInfo {
    @Id
    private Long id;
    private Long financeSystemId;

    private Long gisId;

    private Long coins;
}
