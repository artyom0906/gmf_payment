package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.dto.PayCompleteDto;
import ml.greatmf.pay.pay.dto.PayDto;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Mono<PayCompleteDto> pay(Mono<PayDto> pay);
}
