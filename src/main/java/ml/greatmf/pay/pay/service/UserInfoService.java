package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.model.UserInfo;
import reactor.core.publisher.Mono;

public interface UserInfoService {
    Mono<UserInfo> findByGisId(Long gisId);
    Mono<UserInfo> findByFinanceSystemIdAndGisId(Long financeSystemId, Long gisId);
    Mono<UserInfo> save(Mono<UserInfo> user);
}
