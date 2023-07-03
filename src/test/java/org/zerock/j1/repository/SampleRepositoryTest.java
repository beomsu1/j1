package org.zerock.j1.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Sample;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SampleRepositoryTest {

    // 의존성 주입
    @Autowired
    private SampleRepository sampleRepository;
    
    @Test
    public void test1(){
        log.info(sampleRepository.getClass().getName());
    }

    @Test
    public void testInsert(){

        // 100개의 데이터를 생성하는 코드
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Sample obj = Sample.builder()
            .keyCol("u"+i)
            .first("first")
            .last("last" + i)
            .build();

            // 만든 obj를 save 하는 코드
            sampleRepository.save(obj);
        });

    }

    @Test
    public void testRead(){

        String keyCol = "u10";

        Optional<Sample> result = sampleRepository.findById(keyCol);

        // 만약에 얘가 없으면 예외를 만들어서 던져버리겠다.
        Sample obj = result.orElseThrow();

        log.info(obj);
    }

    @Test
    public void testDelete(){
        String keyCol = "u1";

        sampleRepository.deleteById(keyCol);

    }


    @Test
    public void TestPaging(){

        // pageable api가 있음 domain쪽을 임포트 해야함
        // 주의사항 : 페이지의 번호는 0부터 시작!
        Pageable pageable = PageRequest.of(0,10,

        // ketCol을 역순으로 정렬 하겠다!
        Sort.by("keyCol").descending());

        // 내가 pageable 타입을 사용하면 타입을 맞춰줘야함
       Page<Sample> result = sampleRepository.findAll(pageable);

       // DB는 데이터가 많을 수 있기에 long타입을 사용해줘야함. 엘리먼트가 몇개가 인지 확인!
       log.info(result.getTotalElements());

       // 총 몇페이지 인지 확인
       log.info(result.getTotalPages());    

       // result.hasNext() // 다음 페이지가 있는지
       // result.hasPrevious() // 이전 페이지가 있는지

       // 한 페이지의 데이터 출력
       result.getContent().forEach(obj -> 
       log.info(obj));
    }

}
