package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.model.FinanceSystem;
import ml.greatmf.pay.pay.repo.FinanceSystemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SystemServiceImpl implements FinanceSystemService {

    private final FinanceSystemRepo financeSystemRepo;

    @Autowired
    public SystemServiceImpl(FinanceSystemRepo financeSystemRepo) {
        this.financeSystemRepo = financeSystemRepo;
    }

    @Override
    public Mono<FinanceSystem> save(FinanceSystem system) {
        return financeSystemRepo.save(system);
    }

    @Override
    public Mono<FinanceSystem> getByToken(String token) {
        return financeSystemRepo.findByToken(token);
    }

}
