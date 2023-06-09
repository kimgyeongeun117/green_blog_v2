package com.tenco.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenco.blog.model.User;

// <테이블 명, pk의 타입>
@Repository	// 여기서는 생략 가능 JpaRepository가 생성하기 때문 
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// select, selectAll, insert, update, delete 등
	
	// Spring JPA 네이밍 전략
	// 메서드 이름으로 JPA가 쿼리를 만들어 준다. (규칙을 지킨다면)
	// SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);
	
	// 두번째 방법
	@Query(value = "SELECT * " 
			+ " FROM user WHERE username = ?1 "
			+ " AND password = ?2 ", nativeQuery = true)
	User login(String username,String password);
	
	// SELECT * FROM user WHERE username = ?;
	Optional<User> findByUsername(String username);
	// jpa get set을 하기 위해 optinal로 보냄
}
