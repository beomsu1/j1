package org.zerock.j1.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){

        for (int i = 0; i < 100; i++) {
            Board board = Board.builder()
            .title("Sample Title"+i)
            .content("Sample Content"+i)
            .writer("Sample user"+(i%10))
            .build();

            boardRepository.save(board);
            
        }
        
    }

    @Test
    public void testRead(){

        Long bno = 1L;

        // NUll 값이 나올수 있기에 Optional을 사용
        Optional<Board> result = boardRepository.findById(bno);

        // 최대한 자기 할 일을 미루다가 할 일이 생기면 그때가서 일을 하는 친구
        // 지연로딩( lazy loading ) DB 연결이 필요할 떄 까지 일을 안함.
        // 트랜잭션을 필히 걸어줘야 오류가 안남 ( 사용 X )
        // Board board = boardRepository.getReferenceById(bno);

        log.info("--------------");

        // 예외나면 던져버려라 
        Board board = result.orElseThrow();

        log.info(result.get());

    }

    @Test
    public void testUpdate(){

        // 일단 조회하기!!

        Long bno = 1L;

        // NUll 값이 나올수 있기에 Optional을 사용
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        // 조회한 친구를 이용해 수정하기!!

        board.changeTitle("Update Title");

        boardRepository.save(board);

    }

    @Test
    public void testQuery1(){
        List<Board> list = boardRepository.findByTitleContaining("1");

        log.info("-----------------");
        log.info(list.size());
        log.info(list);

    }

    @Test
    public void testQuery2(){
        Pageable pageable = PageRequest.of(0, 10, 
        Sort.by("bno").descending());

        // 내용에 1이 들어간 값을 찾고 pageable 처리를 한다.
        // 페이징 처리를 하면서 검색 가능!
        Page<Board> result = boardRepository.findByContentContaining("1", pageable);

        log.info("-------------");
        log.info(result);
    }

    @Test
    public void testQuery1_1(){

        List<Board> list = boardRepository.listTitle("1");

        log.info("-----------------");
        log.info(list.size());
        log.info(list);

    }

    @Test
    public void testQuery1_2(){
        
        List<Object[]> list = boardRepository.listTitle2("1");

        log.info("-----------------");
        log.info(list.size());
        
        // 배열들의 값을 보는법 ( forEach )
        list.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testQuery1_3(){
        
        Pageable pageable = PageRequest.of(0, 10,  Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.listTitle2("1", pageable);

        log.info(result);

    }
    @Transactional
    @Commit
    @Test
    public void testModify(){

        Long bno = 100L;
        String title = "Modified Title 100";
        
        // 반환 타입이 int니까 count에 담고 확인해보자
        int count = boardRepository.modifyTitle(title, bno);

        log.info(count);
    }


    @Test
    public void listNative(){

        List<Object[]> result = boardRepository.listNative();

        result.forEach(arr -> log.info(Arrays.toString(arr)));
        
    }

    // 검색
    @Test
    public void testSearch1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.search1("tcw" , "1" , pageable);
   
        log.info(result.getTotalElements());

        result.get().forEach(b -> log.info(b));

    }

    // JPQL
    // 리스트 와 댓글갯수 뽑기
    @Test
    public void testListWithRcnt(){

        List<Object[]> result = boardRepository.getListWithRcnt();

        for (Object[] result2 : result) {
            log.info(Arrays.toString(result2));
        }
    }
    

    // Querydsl 조인코드
    @Test
    public void testLishWithRcntSearch(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        boardRepository.searchWithRcnt("tcw", "1", pageable);
    }

    @Test
    public void test0706_1(){

        PageRequestDTO pageRequest = new PageRequestDTO();
        
        PageResponseDTO<BoardListRcntDTO> responseDTO = 
        boardRepository.searchDTORcnt(pageRequest);

        log.info(responseDTO);
    }
}