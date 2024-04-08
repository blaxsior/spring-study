package com.blaxsior.demo;

import com.blaxsior.demo.core.AppConfig;
import com.blaxsior.demo.member.Grade;
import com.blaxsior.demo.member.Member;
import com.blaxsior.demo.member.service.MemberService;
import com.blaxsior.demo.member.service.MemberServiceImpl;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		AppConfig config = new AppConfig();

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		var memberService = context.getBean("memberService", MemberService.class);

		var memberId = 1L;
		var member = new Member(memberId, "test-member", Grade.VIP);

		memberService.join(member);
		var optMember = memberService.findMember(memberId);

		optMember.ifPresent(
				it -> System.out.println(it.getName())
		);
	}

}
