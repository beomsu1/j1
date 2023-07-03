package org.zerock.j1.service;

import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
    
    PageResponseDTO<TodoDTO> getList();

    // dto는 title만 가지고 있는 애 TodoDTO는 번호까지 가지고 있는 애
    // 나중에 모달창 같은거 등록을 했어 그러면 번호도 알려줘야하는 상황이 생길수도 있어서 TodoDTO을 반환타입으로
    TodoDTO register(TodoDTO dto);
}
