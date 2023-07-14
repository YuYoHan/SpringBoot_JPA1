package com.example.jpabook.repository;

import com.example.jpabook.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
// 테스트 메서드에 @Transactional을 사용하면 트랜잭션으로 감싸지며, 메서드가 종료될 때 자동으로 롤백된다.
// 이 어노테이션이 테스트에 있으면 수행하고 db를 롤백을 한다.
// 테스트 말고 다른 곳에 있으면 정상적으로 동작을 한다.
@Transactional
@Rollback(value = false)
@Slf4j
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("jpa 등록 테스트")
    void testMember() throws Exception {
        // given
        Member member = new Member();
        member = Member.builder()
                .userName("memberA")
                .build();

        // when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);

        // 같은 트랜잭션에서 돌아가기 때문에 영속성 컨텍스트가 같다.
        // 같은 영속성 컨텍스트 안에서는 아이디 값이 같으면 같은 엔티티로 식별한다.
        // 1차 캐쉬에서 기존에 관리하던거에서 꺼내온것이다.
        log.info("findMember == member : " + (findMember == member));
    }
}