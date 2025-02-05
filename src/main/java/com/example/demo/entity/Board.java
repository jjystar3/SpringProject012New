package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int no;

    @Column(length = 100, nullable = false)
    String title;

    @Column(length = 1500, nullable = false)
    String content;

//    @Column(length = 50, nullable = false)
//    String writer;
    
	// 작성자 필드를 외래키로 설정하여 Member 테이블의 PK를 참조
	@ManyToOne // 관계차수는 1:N
	Member writer; // 자료형 변경 String->Member
    
	/* 필드 추가 */
    @Column(length = 200, nullable = true)
	String imgPath; //첨부파일 이름

}
