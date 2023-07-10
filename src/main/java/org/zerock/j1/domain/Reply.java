package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="t_reply")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "board") // board는 뺴고 ToString
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoIncrement
    private Long rno;

    private String replyText;

    private String replyFile;

    private String replyer;

    // FK가 관계의 주인이라 ManyToOne
    // 연관 관계는 fetch LAZY를 걸고 시작하자
    @ManyToOne(fetch = FetchType.LAZY)
    // 하나의 댓글은 하나의 게시글의 포함된다 라는 논리에 대한 코드
    private Board board;

    public void changeText(String text){
        this.replyText = text;
    }

    public void changeFile(String fileName){
        this.replyFile = fileName;
    }
}
