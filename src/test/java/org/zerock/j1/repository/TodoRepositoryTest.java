package org.zerock.j1.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTest {
    
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testInsert(){

        // 반복 돌려서 100개 값 만들기
        IntStream.rangeClosed(1, 100).forEach(i ->{
            Todo todo = Todo.builder()
            // 번호값은 autoIncrement라 값을 줄 수가 없음
            .title("Title"+ i)
            .build();

            Todo result = todoRepository.save(todo);

            log.info(result);
        });
    }

    @Test
    public void testPaging(){

        // 항상 pageable 타입!
        Pageable pageable = PageRequest.of(0, 10, 
        // tno를 역순으로
        Sort.by("tno").descending());

        // pageable 을 쓰면 리턴 타입은 항상 page!!!!!!!!
        Page<Todo> result = todoRepository.findAll(pageable);

        log.info(result);
    }

    @Test
    public void testRead(){

        Long tno = 100L;

        Optional<Todo> result = todoRepository.findById(tno);
        
        // 예외는 던지도록 설계
        Todo entity = result.orElseThrow();

        log.info("entity----------------");
        log.info(entity);

        // entity 를 dto로 변환

        // entity 를 어떤 타입으로 만들것이냐 -> TodoDTO
        // entity를 TodoDTO로 매핑하고 TodoDTO로 반환!
        TodoDTO dto = modelMapper.map(entity, TodoDTO.class);

        log.info(dto);

    }


}
