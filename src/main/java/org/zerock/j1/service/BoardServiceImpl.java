package org.zerock.j1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.j1.domain.Board;
import org.zerock.j1.dto.BoardDTO;
import org.zerock.j1.dto.BoardListRcntDTO;
import org.zerock.j1.dto.PageRequestDTO;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    
    private final BoardRepository boardRepository;

    @Override
    public PageResponseDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {

        log.info("----------------------");
        log.info(pageRequestDTO);

        return boardRepository.searchDTORcnt(pageRequestDTO);
    }

    // 조회
    @Override
    public BoardDTO getOne(Long bno) {

        Optional<Board> result = boardRepository.findById(bno);

        // 문제있으면 던져
        // 던지고 나서 JSON 데이터를 고민해야함!
        Board board = result.orElseThrow();
        
        // board -> BoardDTO로 변환
        BoardDTO dto = modelMapper.map(board, BoardDTO.class);
        
        return dto;

    }
}
