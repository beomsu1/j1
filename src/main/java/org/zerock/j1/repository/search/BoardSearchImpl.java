package org.zerock.j1.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.QBoard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{
    
    // 생성자를 만들어줘서 오류를 해결하자 - 컴파일 에러 해결
    public BoardSearchImpl(){
        super(Board.class);
    }

    @Override
     public Page<Board> search1( String searchType , String keyword , Pageable pageable) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);
        // sql문을 객체화 시켜놓은 애라고 생각하면 됨

        // 키워드이 not null , searchType이 not null 일 때
        if(keyword !=  null && searchType != null){

            //문자열을 배열로
            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            // () 만들어주는게 BooleanBuiler다!
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String type : searchArr) {
                switch(type){
                    // 검색 조건
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }
            } // end for

            // 검색조건을 where절에 추가하자
            query.where(searchBuilder);

        }

 
        this.getQuerydsl().applyPagination(pageable, query);

        // 목록 데이터 뽑기
        List<Board> list = query.fetch();

        // 데이터가 많을 수도 있으니 int 대신 Long 을 많이 사용
        // 데이터 카운트 뽑기
        Long count = query.fetchCount();

        log.info(list);
        log.info("count" + count);

        return new PageImpl<>(list, pageable, count);
    }
}
