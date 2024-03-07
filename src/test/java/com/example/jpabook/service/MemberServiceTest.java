package com.example.jpabook.service;


import com.example.jpabook.entity.member.Member;
import com.example.jpabook.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.aspectj.bridge.MessageUtil.fail;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = Member.builder()
                .userName("kim")
                .build();

        // when
        Long join = memberService.join(member);

        // then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(join));
    }

    @Test
    public void 중복회원예외() throws Exception {
        // given
        Member member = Member.builder()
                .userName("kim")
                .build();

        Member member2 = Member.builder()
                .userName("kim")
                .build();

        // when
        memberService.join(member);

        // 예외 검사를 할 때 이 문법으로 처리해야 한다.
       Assertions.assertThatThrownBy(() -> memberService.join(member2))
                       .isInstanceOf(IllegalStateException.class);

        // then
        fail("예외가 발생해야 한다.");
    }
}