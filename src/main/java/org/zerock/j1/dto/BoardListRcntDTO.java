package org.zerock.j1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//목록 데이터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardListRcntDTO {

    private Long bno;
    private String title;
    private String writer;
    private long replyCount;
    
    
}
