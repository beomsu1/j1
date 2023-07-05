package org.zerock.j1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.j1.domain.Board;
import org.zerock.j1.repository.search.BoardSearch;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch{
    
    List<Board> findByTitleContaining(String title);

    // JPQL - 어노테이션을 사용해서 title을 찾는 코드
    // 파라미터 값을 사용할 때는 = ? 으로 사용
    @Query("select b from Board b where b.title like %:title% ")
    List<Board> listTitle(@Param("title") String title);

    @Query("select b.bno,b.title from Board b where b.title like %:title% ")
    List<Object[]> listTitle2(@Param("title") String title);

    @Query("select b.bno,b.title from Board b where b.title like %:title% ")
    Page<Object[]> listTitle2(@Param("title") String title , Pageable pageable);

    // update
    @Modifying
    @Query("update Board b set b.title = :title where b.bno =:bno")
    int modifyTitle(@Param("title")String title, @Param("bno")Long bno);


    // 내용으로 검색하는데 페이징 처리까지 해줌 Pageable을 사용했기에
    Page<Board> findByContentContaining(String content, Pageable pageable);


    //Native Query

    @Query(value = "select * from t_board", nativeQuery = true)
    List<Object[]> listNative();

    // 이 코드는 정적이라 내용을 바꿀 수 없음 (그래서 사용 X ) -> Querydsl을 사용해야함
    // 댓글 갯수 리스트?
    //b.bno, b.title, b.writer 3개가 Object의 배열로
    @Query("select b.bno, b.title, b.writer , count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
    List<Object[]> getListWithRcnt();



}
