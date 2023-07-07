package org.zerock.j1.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface BoardReadDTO {
    
    // 인터페이스라 public 생략
    // getter , setter 규칙에 맞춰서 생성
    Long getBno();
    String getTitle();
    String getContent();
    LocalDateTime getRegDate();
    LocalDateTime getModDate();


}
