package com.jpabook.jpashop.service;


import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class) // junit 실행시 스프링이랑 같이 실행
@SpringBootTest // 스프링 부트를 띄운 상태에서
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    // @Rollback(value = false) // 기본적으로 트렌ㄴ젝션은 롤백을 하지만 롤백하지 않음
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("kim");

        // when

        Long savedId = memberService.join(member);

        em.flush();
        // then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        memberService.join(member1);
        memberService.join(member2); //  이미 이쪽에서 예외 발생


        fail("중복_회원_예외발생");
    }


}
