package bam.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {
    public String getNowDateStr() {
 
        // 현재 날짜/시간
        LocalDateTime now = LocalDateTime.now();
        // 포맷팅
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // 포맷팅 현재 날짜/시간 출력
        return formatedNow; // 2021년 12월 02일 18시 19분 36초
    }
}