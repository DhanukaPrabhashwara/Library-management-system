package com.library.memberservice.service;

import com.library.memberservice.exception.MemberNotFoundException;
import com.library.memberservice.model.Member;
import com.library.memberservice.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for Member business logic.
 * Contains all business operations related to members.
 */
@Service
@Transactional
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Create a new member.
     *
     * @param member the member to create
     * @return the created member
     */
    public Member createMember(Member member) {
        log.info("Creating new member with email: {}", member.getEmail());
        Member savedMember = memberRepository.save(member);
        log.info("Member created successfully with id: {}", savedMember.getId());
        return savedMember;
    }

    /**
     * Get all members.
     *
     * @return list of all members
     */
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        log.info("Fetching all members");
        List<Member> members = memberRepository.findAll();
        log.info("Found {} members", members.size());
        return members;
    }

    /**
     * Get a member by ID.
     *
     * @param id the member ID
     * @return the member
     * @throws MemberNotFoundException if member not found
     */
    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        log.info("Fetching member with id: {}", id);
        return memberRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Member not found with id: {}", id);
                    return new MemberNotFoundException("Member not found with id: " + id);
                });
    }

    /**
     * Update an existing member.
     *
     * @param id            the member ID
     * @param updatedMember the updated member data
     * @return the updated member
     * @throws MemberNotFoundException if member not found
     */
    public Member updateMember(Long id, Member updatedMember) {
        log.info("Updating member with id: {}", id);
        Member existingMember = getMemberById(id);

        if (updatedMember.getName() != null) {
            existingMember.setName(updatedMember.getName());
        }
        if (updatedMember.getEmail() != null) {
            existingMember.setEmail(updatedMember.getEmail());
        }
        if (updatedMember.getPassword() != null) {
            existingMember.setPassword(updatedMember.getPassword());
        }

        Member savedMember = memberRepository.save(existingMember);
        log.info("Member updated successfully with id: {}", id);
        return savedMember;
    }

    /**
     * Delete a member by ID.
     *
     * @param id the member ID
     * @throws MemberNotFoundException if member not found
     */
    public void deleteMember(Long id) {
        log.info("Deleting member with id: {}", id);
        Member member = getMemberById(id);
        memberRepository.delete(member);
        log.info("Member deleted successfully with id: {}", id);
    }
}
