package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.MemberDto;
import com.infodev.bookrental.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author rawalokes
 * Date:2/26/22
 * Time:11:49 AM
 */
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberServiceImpl memberService;

    public MemberController(MemberServiceImpl memberService) {
        this.memberService = memberService;
    }

    //return all member
    @GetMapping("/setup")
    public String getAllMember(Model model) {
        model.addAttribute("memberlist", memberService.showAll());
        return "member/membersetup";
    }


    @GetMapping("/create")
    public String getAddMember(Model model) {
        model.addAttribute("members", new MemberDto());
        return "member/membercreate";
    }

    @PostMapping("/create")
    public String postAddMember(@ModelAttribute("members") MemberDto memberDto) {
        memberService.create(memberDto);
        return "redirect:/member/setup";
    }

    @GetMapping("/delete/{id}")
    public String removeMemberByID(@PathVariable("id") Integer id) {
        memberService.deleteById(id);
        return "redirect:/member/setup";
    }

    @GetMapping("/update/{id}")
    public String updateMemberByID(@PathVariable Integer id, Model model) {
        MemberDto memberDto = memberService.findById(id);
        model.addAttribute("members", memberDto);
        return "member/membercreate";
    }

}
