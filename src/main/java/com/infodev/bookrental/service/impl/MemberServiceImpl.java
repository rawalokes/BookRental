package com.infodev.bookrental.service.impl;

import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.MemberDto;
import com.infodev.bookrental.model.Member;
import com.infodev.bookrental.repo.MemberRepo;
import com.infodev.bookrental.service.MemberService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:2/27/22
 * Time:9:51 PM
 */
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;

    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public MemberDto create(MemberDto memberDto) {
        Member reMember = dtoToMember(memberDto);
        reMember = memberRepo.save(reMember);
        return memberToDTO(reMember);
    }

    @Override
    public List<MemberDto> showAll() {
        List<Member> memberList = memberRepo.findAll();
        return memberList.stream().map(member -> MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .address(member.getAddress())
                .mobileNumber(member.getMobileNumber())
                .email(member.getAddress()).
                build()).collect(Collectors.toList());
    }

    @Override
    public MemberDto findById(Integer integer) {
        Optional<Member> member = memberRepo.findById(integer);
        if (member.isPresent()) {
            Member reterivedMember = member.get();
            return memberToDTO(reterivedMember);
        }
        return null;

    }

    @Override
    public void deleteById(Integer integer) {
        memberRepo.deleteById(integer);
    }

    private MemberDto memberToDTO(Member member) {
        return MemberDto.builder().id(member.getId()).name(member.getName())

                .email(member.getEmail()).mobileNumber(member.getMobileNumber()).address(member.getAddress()).build();

    }

    private Member dtoToMember(MemberDto memberDto) {
        return Member.builder().id(memberDto.getId()).name(memberDto.getName()).email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .mobileNumber(memberDto.getMobileNumber())
                .build();
    }

}
