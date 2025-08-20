package com.tunaforce.message;

import com.tunaforce.message.token.entity.MasterToken;
import com.tunaforce.message.message.dto.request.MapKeyRequestDto;
import com.tunaforce.message.token.repository.TokenKeyJpaRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Transactional
public class TimstampedTest {

    @Autowired
    private TokenKeyJpaRepository tokenKeyJpaRepository;

    @Test
    @DisplayName("DB insert 추가 테스트")
    public void insertToken() {

        MapKeyRequestDto mapKeyRequestDto = new MapKeyRequestDto(
                UUID.randomUUID(),
                "osh066qnrv",
                "uvvXHOeBUf3sEaIBaCX5jqBbJHBZqYNrem0s07lj",
                "xoxb-8953330136789-9365014243201-rkNUnlaP1FndcylHCJB3OleP"
        );

        MasterToken masterToken = new MasterToken(mapKeyRequestDto);

        tokenKeyJpaRepository.save(masterToken);

        Assertions.assertThat(masterToken.getCreatedAt()).isNotNull();

    }
}
