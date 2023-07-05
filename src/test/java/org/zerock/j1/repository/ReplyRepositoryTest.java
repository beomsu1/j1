package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {

    // 의존성 주입
    @Autowired
    private ReplyRepository replyRepository;

    
    // // board를 만들 때 board객체를 조회하는 게 아니고 id만 있으면 생성 가능하다!
    // @Test
    // public void insertOne(){
    //     Long bno = 100L;

    //     // reply의 board 객체 PK만 설정해주면 생성이 됨
    //     Board board = Board.builder().bno(bno).build();

    //     Reply reply = Reply.builder()
    //     .replyText("Reply...1")
    //     .replyer("replyer98")
    //     .board(board)
    //     .build();

    //     replyRepository.save(reply);
    // }
    

    // @Test
    // public void testInsertDumies(){

    //     Long[] bnoArr = {99L , 96L ,92L ,84L , 81L};

    //     for(Long bno : bnoArr){

    //         Board board = Board.builder().bno(bno).build();

    //         for(int i = 0; i< 5; i++){
                
    //             Reply reply = Reply.builder()
    //             .replyText("Reply"+bno+"--"+i)
    //             .replyer("replyer"+i)

    //             // bno값으로 만든 board를 넣어서 댓글 추가하기!
    //             .board(board)
    //             .build();

    //             replyRepository.save(reply);

    //         }


    //     } // end for
    // }

    //조회
    @Test
    public void testListBoard(){

        Long bno = 99L;

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(bno, pageable);

        // ToString 떄문에 board를 가져오기위해 쿼리를 한 번 더 날린것
        // ToSTring 할 떄 board를 안 가져오게 설정 하면 됨! -> domian reply에서 설정해주기
        result.get().forEach(r -> log.info(r));
    }
}
