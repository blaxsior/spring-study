package com.blaxsior.demo.frontcontroller.v3.controller;

import com.blaxsior.demo.domain.member.Member;
import com.blaxsior.demo.domain.member.MemberRepository;
import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.MyView;
import com.blaxsior.demo.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        var modelView = new ModelView("members");
        modelView.getModel().put("members", members);

        return modelView;
    }
}
