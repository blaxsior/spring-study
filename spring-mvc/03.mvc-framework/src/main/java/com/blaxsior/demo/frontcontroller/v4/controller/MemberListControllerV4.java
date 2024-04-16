package com.blaxsior.demo.frontcontroller.v4.controller;

import com.blaxsior.demo.domain.member.Member;
import com.blaxsior.demo.domain.member.MemberRepository;
import com.blaxsior.demo.frontcontroller.ModelView;
import com.blaxsior.demo.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) throws ServletException, IOException{
        List<Member> members = memberRepository.findAll();

        model.put("members", members);

        return "members";
    }
}
