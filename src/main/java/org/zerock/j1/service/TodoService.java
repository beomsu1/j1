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

    // 조회
    TodoDTO getOne (Long tno);

    // 삭제
    void remove(Long tno);

    // 수정
    // 일반적으로 조회하는 페이지로 이동을 시켰는데 이제는 그 화면에서 처리를 하고있다!
    // 첫번 째 방식 업데이트 된 데이터를 state를 해서 보여주는 방식
    // 두번 쨰 강제로 read Component를 강제로 리렌더링 해서 보여주는 방식
    void update(TodoDTO dto);

}
