package io.angularpay.ttl.adapter.inbound;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

@RequiredArgsConstructor
public class TTLMessageSubscriber implements MessageListener {

    private final RedisMessageAdapter redisMessageAdapter;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        redisMessageAdapter.onMessage(String.valueOf(message));
    }
}
