package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {

	@Autowired
    BoardService service;
	
	// 파일 스트림은 json 데이터로 전송할 수 없음
	// 파일 스트림을 보내기 위해 게시물 데이터를 폼데이터로 전송해야함
	
	// @RequestBody
	// JSON데이터를 받아서 BoardDTO로 변환

	// 처음에는 @RequestBody로 했다가 나중에 지우기
	// 게시물 데이터를 폼데이터로 받기 위해 @RequestBody 삭제
	@PostMapping("/register")
	public ResponseEntity<Integer> register(BoardDTO dto, Principal principal) {
		dto.setWriter(principal.getName());
		int no = service.register(dto);
		return new ResponseEntity<>(no, HttpStatus.CREATED); //201성공코드와 새로운 글번호를 반환한다
	}

	//localhost:8080/board/
	@GetMapping("/list")
	public ResponseEntity<List<BoardDTO>> getList() {
		List<BoardDTO> list = service.getList();
		return new ResponseEntity<>(list, HttpStatus.OK); //200성공코드와 게시물목록을 반환한다
	}

	//localhost:8080/board?no=1
	@GetMapping("/read")
	public ResponseEntity<BoardDTO> read(@RequestParam(name = "no") int no) {
		BoardDTO dto = service.read(no);
		return new ResponseEntity<>(dto, HttpStatus.OK); //200성공코드와 게시물정보를 반환한다
	}

	@PutMapping("/modify")
	public ResponseEntity modify(@RequestBody BoardDTO dto) {
		 service.modify(dto);
		 return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

	@DeleteMapping("/remove")
	public ResponseEntity remove(@RequestParam(name = "no") int no) {
		service.remove(no);
		return new ResponseEntity(HttpStatus.NO_CONTENT); //204성공코드를 반환한다
	}

}
