package com.example.jpabook.service;


import com.example.jpabook.entity.member.Member;
import com.example.jpabook.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
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

    }

}