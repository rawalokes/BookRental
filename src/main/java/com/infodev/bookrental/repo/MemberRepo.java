package com.infodev.bookrental.repo;

import com.infodev.bookrental.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member,Integer> {
}
