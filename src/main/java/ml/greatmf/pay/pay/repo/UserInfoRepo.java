package ml.greatmf.pay.pay.repo;

import ml.greatmf.pay.pay.model.UserInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserInfoRepo  extends ReactiveCrudRepository<UserInfo, Long> {

    Mono<UserInfo> findByGisId(Long gisId);
    Mono<UserInfo> findByFinanceSystemIdAndGisId(Long financeSystemId, Long gisId);
}
