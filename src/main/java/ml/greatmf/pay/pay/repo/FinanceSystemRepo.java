package ml.greatmf.pay.pay.repo;

import ml.greatmf.pay.pay.model.FinanceSystem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface FinanceSystemRepo extends ReactiveCrudRepository<FinanceSystem, Long> {
    Mono<FinanceSystem> findByToken(String token);
}
