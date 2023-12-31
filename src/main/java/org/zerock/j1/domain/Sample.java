package org.zerock.j1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // entity 클래스로 만들어줌
@Table(name = "tbl_sample")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class Sample {

    @Id
    private String keyCol;
    
    private String first;
    
    private String last;

    private String zipcode;

    private String city;

}
