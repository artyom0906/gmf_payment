package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.model.FinanceSystem;
import reactor.core.publisher.Mono;

public interface FinanceSystemService {
    Mono<FinanceSystem> save(FinanceSystem system);
    Mono<FinanceSystem> getByToken(String token);
}
