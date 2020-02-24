package com.maryanto.dimas.example.fallback;

import com.maryanto.dimas.example.entity.ErrorMessages;
import com.maryanto.dimas.example.impl.GatewayClientResponse;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;

@Slf4j
@Component
public class GeneralServiceFallback implements FallbackProvider {

    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException || cause instanceof SocketTimeoutException || cause instanceof com.netflix.client.ClientException) {
            GatewayClientResponse badGateway = new GatewayClientResponse(
                    new ErrorMessages(
                            String.format("Service ('%s') not available.", route),
                            HttpStatus.BAD_GATEWAY)
            );
            return badGateway;
        } else {
            log.debug("error route -> {}", route, cause);
            return new GatewayClientResponse(
                    new ErrorMessages(
                            String.format("Service ('%s') not available.", route),
                            HttpStatus.INTERNAL_SERVER_ERROR)
            );
        }
    }
}
