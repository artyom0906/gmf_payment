package ml.greatmf.pay.pay.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ml.greatmf.pay.pay.dto.ErrorDto;
import ml.greatmf.pay.pay.dto.PayCompleteDto;
import ml.greatmf.pay.pay.dto.PayDto;
import ml.greatmf.pay.pay.exceptions.TransactionCancelException;
import ml.greatmf.pay.pay.exceptions.UserNotFoundException;
import ml.greatmf.pay.pay.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Test get request")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "return new user money amount",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PayCompleteDto.class, multipleOf = 2))),
            @ApiResponse(responseCode = "400", description = "invalid token or money to transfer amount",
                    content = @Content(mediaType = "application/json",
                            examples = {
                                    @ExampleObject(value = """
                                            {
                                              "error" : "invalid token / invalid money to transfer amount",
                                            }""")
                            },
                            schema = @Schema(implementation = ErrorDto.class))),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content(mediaType = "application/json",
                            examples = {@ExampleObject(value = """
                                    {
                                      "error" : "user not found",
                                      "user" : {
                                        "id" : 0,
                                        "money" : -1
                                      }
                                    }""")},
                            schema = @Schema(implementation = ErrorDto.class))),
           @ApiResponse(responseCode = "409", description = "payment canceled",
                   content = @Content(mediaType = "application/json",
                           examples = {@ExampleObject(value = """
                                   {
                                     "error" : "not enough money to pay",
                                     "user" : {
                                       "id" : 0,
                                       "money" : 0
                                     }
                                   }""")},
                           schema = @Schema(implementation = ErrorDto.class)))})
    @PostMapping(value = "pay")
    public Mono<PayCompleteDto> postTestDto(@RequestBody final Mono<PayDto> payDto) {
        return transactionService.pay(payDto);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDto> handleConversion(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage(), ex.getUserDto()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionCancelException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorDto> handleTransactionCancel(TransactionCancelException ex) {
        return new ResponseEntity<>(new ErrorDto("not enough money to pay", ex.getUserDto()), HttpStatus.CONFLICT);
    }
}
