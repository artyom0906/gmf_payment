package ml.greatmf.pay.pay.service;

import lombok.SneakyThrows;
import ml.greatmf.pay.pay.dto.UserDto;
import ml.greatmf.pay.pay.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class GISServiceImpl implements GISService {
    private final WebClient client;
    public GISServiceImpl(@Value("${gis.url}") String url){
        client = WebClient.create(url);
    }
    @SneakyThrows
    @Override
    public Mono<Boolean> user_exist(Long id) {
        return client.get().uri("/users/{id}", id).retrieve().toBodilessEntity().handle((r, sink)->{
            if(r.getStatusCodeValue() != HttpStatus.OK.value()){
                sink.error(new UserNotFoundException(new UserDto(id)));
                sink.next(false);
            }else {
                sink.next(true);
            }
        });
    }
}
