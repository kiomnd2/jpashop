package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {


    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); // 영속성 컨텍스트에 객체를 올림 이 때 키는 member의 아이디
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); // jpql 문법 from의 대상이 테이블이 아닌 Member Entity
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
