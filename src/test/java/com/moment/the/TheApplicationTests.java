package com.moment.the;

import com.moment.the.exception.ErrorClassification;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class TheApplicationTests {
    @Test
    @DisplayName("번외 - Enum과 String 합치기 테스트")
    void str_compare_test(){
        String str = ErrorClassification.COMMON + "-ERR-400";
        assertEquals(str, "COMMON-ERR-400");
    }
}