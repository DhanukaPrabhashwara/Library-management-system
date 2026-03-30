package com.library.memberservice.service;

import com.library.memberservice.exception.MemberNotFoundException;
import com.library.memberservice.model.Member;
import com.library.memberservice.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for MemberService.
 */
@SpringBootTest
@DisplayName("Member Service Integration Tests")
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    private Member testMember;

    @BeforeEach
    public void setUp() {
        memberRepository.deleteAll();
        
        testMember = new Member(null, "John Doe", "john@example.com", "password123");
    }

    @Test
    @DisplayName("Should create a member successfully")
    public void testCreateMember() {
        Member createdMember = memberService.createMember(testMember);
        
        assertNotNull(createdMember.getId());
        assertEquals("John Doe", createdMember.getName());
        assertEquals("john@example.com", createdMember.getEmail());
    }

    @Test
    @DisplayName("Should retrieve all members")
    public void testGetAllMembers() {
        memberService.createMember(testMember);
        
        var members = memberService.getAllMembers();
        
        assertFalse(members.isEmpty());
        assertEquals(1, members.size());
    }

    @Test
    @DisplayName("Should retrieve member by ID")
    public void testGetMemberById() {
        Member createdMember = memberService.createMember(testMember);
        
        Member retrieved = memberService.getMemberById(createdMember.getId());
        
        assertNotNull(retrieved);
        assertEquals(createdMember.getId(), retrieved.getId());
        assertEquals("John Doe", retrieved.getName());
    }

    @Test
    @DisplayName("Should throw MemberNotFoundException when ID not found")
    public void testGetMemberByIdNotFound() {
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberById(999L);
        });
    }

    @Test
    @DisplayName("Should update member successfully")
    public void testUpdateMember() {
        Member createdMember = memberService.createMember(testMember);
        
        Member updated = new Member(null, "Jane Doe", "jane@example.com", "newPassword");
        
        Member result = memberService.updateMember(createdMember.getId(), updated);
        
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane@example.com", result.getEmail());
    }

    @Test
    @DisplayName("Should delete member successfully")
    public void testDeleteMember() {
        Member createdMember = memberService.createMember(testMember);
        
        memberService.deleteMember(createdMember.getId());
        
        assertThrows(MemberNotFoundException.class, () -> {
            memberService.getMemberById(createdMember.getId());
        });
    }
}
