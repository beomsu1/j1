package org.zerock.j1.service;

import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.ReplyDTO;
import org.zerock.j1.dto.ReplyPageRequestDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface ReplyService {

    // 항상 리턴 타입은 PageResponseDTO
    PageResponseDTO<ReplyDTO> list (ReplyPageRequestDTO requestDTO);

    // 등록
    Long register(ReplyDTO replyDTO);
    
    // 댓글 조회
    ReplyDTO read(Long rno);

    // 삭제
    void remove(Long rno);

    // 수정
    // 여러개의 값들이 있기에 ReplyDTO로 처리
    void modify(ReplyDTO replyDTO);
}
