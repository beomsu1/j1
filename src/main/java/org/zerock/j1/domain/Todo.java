package org.zerock.j1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 모든 Entity는 id를 가진다
@Table(name="tbl_todo2")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter // entity는 가능하면 getter를 사용 DB에서 데이터가 나오기 때문에
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // autoIncrement로 사용할 거라는 코드
    private Long tno;

    @Column(length = 300, nullable = false) // 300자 제한 , not null
    private String title;
    
}
