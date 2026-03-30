package com.library.memberservice.controller;

import com.library.memberservice.dto.MemberDTO;
import com.library.memberservice.model.Member;
import com.library.memberservice.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit tests for MemberController.
 */
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    private Member testMember;
    private MemberDTO testMemberDTO;

    @BeforeEach
    public void setUp() {
        testMember = new Member(1L, "John Doe", "john@example.com", "password123");
        testMemberDTO = new MemberDTO(1L, "John Doe", "john@example.com", "password123");
    }

    @Test
    public void testCreateMember() throws Exception {
        when(memberService.createMember(any(Member.class))).thenReturn(testMember);

        mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMemberDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    public void testGetAllMembers() throws Exception {
        List<Member> members = Arrays.asList(testMember);
        when(memberService.getAllMembers()).thenReturn(members);

        mockMvc.perform(get("/members")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetMemberById() throws Exception {
        when(memberService.getMemberById(1L)).thenReturn(testMember);

        mockMvc.perform(get("/members/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateMember() throws Exception {
        when(memberService.updateMember(eq(1L), any(Member.class))).thenReturn(testMember);

        mockMvc.perform(put("/members/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testMemberDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDeleteMember() throws Exception {
        mockMvc.perform(delete("/members/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
