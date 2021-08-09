package com.sinisiro.jpashop.repository;

import com.sinisiro.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositoryJpaData extends JpaRepository<Member,Long> {
    List<Member> findByName(String name);
}
