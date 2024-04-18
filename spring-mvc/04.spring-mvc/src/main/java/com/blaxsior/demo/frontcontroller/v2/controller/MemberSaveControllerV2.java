package com.blaxsior.demo.frontcontroller.v2.controller;

import com.blaxsior.demo.domain.member.Member;
import com.blaxsior.demo.domain.member.MemberRepository;
import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v2.ControllerV2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        req.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save.jsp";

        var view = new MyView(viewPath);
        return view;
    }
}
