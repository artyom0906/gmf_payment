package ml.greatmf.pay.pay.service;

import ml.greatmf.pay.pay.dto.PayCompleteDto;
import ml.greatmf.pay.pay.dto.PayDto;
import ml.greatmf.pay.pay.dto.UserDto;
import ml.greatmf.pay.pay.exceptions.TransactionCancelException;
import ml.greatmf.pay.pay.model.FinanceSystem;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final FinanceSystemService financeSystemService;
    private final GISService gisService;

    public TransactionServiceImpl(FinanceSystemService financeSystemService, GISService gisService) {
        this.financeSystemService = financeSystemService;
        this.gisService = gisService;
    }

    @Override
    public Mono<PayCompleteDto> pay(Mono<PayDto> pay) {
        return pay.handle((p, sink) -> {
            gisService.user_exist(p.getFrom()).doOnError(sink::error);
            gisService.user_exist(p.getTo()).doOnError(sink::error);
            if (p.getAmount() <= 0)
                sink.error(new IllegalArgumentException("value to transfer must be positive and not equals to 0"));

            financeSystemService.getByToken(p.getToken())
                    .subscribe(s -> {
                        Long from_money_old = s.getUsers().get(p.getFrom());
                        Long to_money_old = s.getUsers().get(p.getTo());
                        if (from_money_old==null || from_money_old - p.getAmount() < 0L) {
                            throw new TransactionCancelException(new UserDto(p.getFrom(), from_money_old));
                        } else {
                            s.getUsers().put(p.getTo(), to_money_old==null ? 0 : to_money_old + p.getAmount());
                            s.getUsers().put(p.getFrom(), from_money_old - p.getAmount());
                            financeSystemService.save(s);
                            sink.next(
                                    new PayCompleteDto(
                                            new UserDto(p.getFrom(), s.getUsers().get(p.getFrom())),
                                            new UserDto(p.getTo(), s.getUsers().get(p.getTo()))
                                    )
                            );
                        }
                    });
        });
    }
}
