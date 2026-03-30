package com.library.memberservice.controller;

import com.library.memberservice.dto.MemberDTO;
import com.library.memberservice.model.Member;
import com.library.memberservice.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for Member management endpoints.
 * Base URL: /members
 */
@RestController
@RequestMapping("/members")
@Tag(name = "Member Management", description = "APIs for managing library members")
public class MemberController {

        private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    /**
     * Create a new member.
     *
     * @param memberDTO the member data
     * @return ResponseEntity with created member and 201 status
     */
    @PostMapping
    @Operation(summary = "Create a new member", description = "Creates a new member in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Member created successfully",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        log.info("POST /members - Creating new member with email: {}", memberDTO.getEmail());

        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setEmail(memberDTO.getEmail());
        member.setPassword(memberDTO.getPassword());

        Member createdMember = memberService.createMember(member);
        MemberDTO responseDTO = convertToDTO(createdMember);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Get all members.
     *
     * @return ResponseEntity with list of members and 200 status
     */
    @GetMapping
    @Operation(summary = "Get all members", description = "Retrieves all members from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Members retrieved successfully",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        log.info("GET /members - Fetching all members");

        List<Member> members = memberService.getAllMembers();
        List<MemberDTO> memberDTOs = members.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new ResponseEntity<>(memberDTOs, HttpStatus.OK);
    }

    /**
     * Get a member by ID.
     *
     * @param id the member ID
     * @return ResponseEntity with member and 200 status
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get member by ID", description = "Retrieves a specific member by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member found",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "404", description = "Member not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        log.info("GET /members/{} - Fetching member by ID", id);

        Member member = memberService.getMemberById(id);
        MemberDTO memberDTO = convertToDTO(member);

        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    /**
     * Update an existing member.
     *
     * @param id             the member ID
     * @param updatedMemberDTO the updated member data
     * @return ResponseEntity with updated member and 200 status
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update member", description = "Updates an existing member's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member updated successfully",
                    content = @Content(schema = @Schema(implementation = MemberDTO.class))),
            @ApiResponse(responseCode = "404", description = "Member not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<MemberDTO> updateMember(
            @PathVariable Long id,
            @RequestBody MemberDTO updatedMemberDTO) {
        log.info("PUT /members/{} - Updating member", id);

        Member updatedMember = new Member();
        updatedMember.setName(updatedMemberDTO.getName());
        updatedMember.setEmail(updatedMemberDTO.getEmail());
        updatedMember.setPassword(updatedMemberDTO.getPassword());

        Member member = memberService.updateMember(id, updatedMember);
        MemberDTO responseDTO = convertToDTO(member);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Delete a member by ID.
     *
     * @param id the member ID
     * @return ResponseEntity with 204 (No Content) status
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete member", description = "Deletes a member from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Member deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Member not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        log.info("DELETE /members/{} - Deleting member", id);

        memberService.deleteMember(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Convert Member entity to MemberDTO.
     *
     * @param member the member entity
     * @return the member DTO
     */
    private MemberDTO convertToDTO(Member member) {
        return new MemberDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword()
        );
    }
}
