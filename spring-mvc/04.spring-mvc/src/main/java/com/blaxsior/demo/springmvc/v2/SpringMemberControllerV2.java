package com.blaxsior.demo.springmvc.v2;

import com.blaxsior.demo.domain.member.Member;
import com.blaxsior.demo.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {
    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping
    public ModelAndView index() {
        List<Member> members = memberRepository.findAll();

        var modelView = new ModelAndView("members");
        modelView.addObject("members", members);

        return modelView;
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        var view = new ModelAndView("save"); // 논리 이름 반환
        view.addObject("member", member); // attribute 설정

        return view;
    }
}
