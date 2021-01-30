package ml.greatmf.pay.pay.service;

import reactor.core.publisher.Mono;

public interface GISService {
    Mono<Boolean> user_exist(Long id);
}
