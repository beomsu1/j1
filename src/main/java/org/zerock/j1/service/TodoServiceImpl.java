package org.zerock.j1.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;
import org.zerock.j1.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final ModelMapper modelMapper;

    private final TodoRepository todoRepository;
    
    @Override
    public PageResponseDTO<TodoDTO> getList() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        // 수집된 리스트를 TodoDTO 객체로 매핑이 되고 dtoList에 저장이 된다.
        List<TodoDTO> dtoList = 
        result.getContent().stream()
        // todo를 TodoDTO로 변환한다음에 리스트로 모아라
        .map(todo -> modelMapper.map(todo, TodoDTO.class)).collect(Collectors.toList());

        // PageResponseDTO 객체를 생성하여 rseponse 변수에 할당
        // PageResponseDTO<TodoDTO> response = new PageResponseDTO<>();

        // response.setDtoList(dtoList);
        // return response;
        return null;

    }

    @Override
    public TodoDTO register(TodoDTO dto) {

        // dto 를 Todo로 변환해서 그 값을 entity에 저장
        // domain Todo -> entity
        Todo entity = modelMapper.map(dto, Todo.class);
        
        // 번호가 들어있는 Todo
        // entity를 db에 저장을하고 그 값을 result에 저장
        Todo result = todoRepository.save(entity);
        
        // result 값을 TodoDTO로 변환을 해서 화면에 출력 대기
        return modelMapper.map(result,TodoDTO.class);

    }


    // 조회
    @Override
    public TodoDTO getOne(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);
        
        // 예외를 던져주는 방식
        Todo todo = result.orElseThrow();

        // todo -> TodoDTO로 변환 후 TodoDTO 타입의 dto에 저장한다!
        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);

        return dto;
    }

    // 삭제
    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);
    }

    // 수정이
    @Override
    public void update(TodoDTO dto) {

        // findById를 해서 조회를 하고 수정을 하고 저장하자!

        Optional<Todo> result = todoRepository.findById(dto.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(dto.getTitle());

        todoRepository.save(todo);

    }
    
}
