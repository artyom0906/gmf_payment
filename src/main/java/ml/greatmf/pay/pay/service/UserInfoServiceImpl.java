package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.model.UserInfo;
import ml.greatmf.pay.pay.repo.UserInfoRepo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepo userInfoRepo;

    public UserInfoServiceImpl(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    @Override
    public Mono<UserInfo> findByGisId(Long gisId) {
        return userInfoRepo.findByGisId(gisId);
    }

    @Override
    public Mono<UserInfo> findByFinanceSystemIdAndGisId(Long financeSystemId, Long gisId) {
        return userInfoRepo.findByFinanceSystemIdAndGisId(financeSystemId, gisId).doON;
    }

    @Override
    public Mono<UserInfo> save(Mono<UserInfo> user) {
        return null;
    }
}
