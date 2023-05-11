package com.tenco.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 유저이름 , 비밀번호 같이 받게 설계
		// !!시큐리티는 비밀번호 확인 하지 않음 -> DB 사용자 계정이 있는지
		// 먼저 검사한다.
		User principal = userRepository
				.findByUsername(username).orElseThrow(()->{
					return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
				});
		
		// 시큐리티 세션 영역에 유저 정보가 저장이 된다.
		return new PrincipalDetail(principal);
	}
	
	
}
