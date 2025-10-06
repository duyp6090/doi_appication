package com.duydev.backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "otp", timeToLive = 300) // TTL in seconds (1 minute)
public class OtpEntityRedis {
    @Id
    private String id;
    @Indexed
    private String email;
    @Indexed
    private String code;
}
