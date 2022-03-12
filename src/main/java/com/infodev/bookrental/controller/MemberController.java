package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.MemberDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.serviceImpl.MemberServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public String postAddMember(@Valid @ModelAttribute("members") MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/member/membercreate";

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
        ResponseDto responseDto = memberService.findById(id);
        if (responseDto.isResponseStatus()) {
            model.addAttribute("members", responseDto.getMemberDto());
            return "member/membercreate";
        }
        model.addAttribute("errormessage",responseDto.getResponse());
        return "category/categorysetup";
    }

}
