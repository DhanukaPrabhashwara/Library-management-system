package com.library.memberservice.repository;

import com.library.memberservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Member entity.
 * Provides database operations for the Member table.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Find a member by email address.
     *
     * @param email the email address
     * @return Optional containing Member if found
     */
    Optional<Member> findByEmail(String email);
}
