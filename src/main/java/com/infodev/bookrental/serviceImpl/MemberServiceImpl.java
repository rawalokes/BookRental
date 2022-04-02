package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.components.SendEmailComponents;
import com.infodev.bookrental.dto.MemberDto;
import com.infodev.bookrental.dto.ResponseDto;
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
    private final SendEmailComponents sendEmailComponents;

    public MemberServiceImpl(MemberRepo memberRepo, SendEmailComponents sendEmailComponents) {
        this.memberRepo = memberRepo;
        this.sendEmailComponents = sendEmailComponents;
    }

    @Override
    public ResponseDto create(MemberDto memberDto) {
        try {
            //convert memberdto into member
            Member member = dtoToMember(memberDto);
            //if member is newly created
            if (memberDto.getId() != null) {

                String currentMail = memberDto.getEmail();
                String databaseMail = memberRepo.findById(memberDto.getId()).get().getEmail();
                //check if mail is changed
                if (!currentMail.equalsIgnoreCase(databaseMail)) {
                    //send email saying your email is changed
                    sendEmailComponents.sendEmail(memberDto.getEmail(), "Member", memberDto.getName(), true);
                }

            } else {

                sendEmailComponents.sendEmail(memberDto.getEmail(), "Member", memberDto.getName(), false);
            }
            //save member
            memberRepo.save(member);

            return ResponseDto.builder()
                    .responseStatus(true)
                    .build();


        } catch (Exception e) {
            if (e.getMessage().contains("mobileNumber")) {
                return errorResponse("mobile number already exists");
            } else {
                return errorResponse("email already exists");
            }
        }

    }

    @Override
    public List<MemberDto> showAll() {
        List<Member> memberList = memberRepo.findAll();
        return memberList.stream().map(member -> memberToDTO(member)).collect(Collectors.toList());
    }

    @Override
    public ResponseDto findById(Integer integer) {
        try {
            Optional<Member> member = memberRepo.findById(integer);
            if (member.isPresent()) {
                Member reterivedMember = member.get();
                return ResponseDto.builder()
                        .responseStatus(true)
                        .memberDto(memberToDTO(reterivedMember))
                        .build();
            }

        } catch (Exception e) {
            return errorResponse("Member Not Found");
        }

        return null;
    }

    @Override
    public ResponseDto deleteById(Integer integer) {
        try {
            memberRepo.deleteById(integer);
        } catch (Exception e) {
            return errorResponse("Member Not Found");
        }
        return null;
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

    private ResponseDto errorResponse(String message) {
        return ResponseDto.builder()
                .responseStatus(false)
                .response(message)
                .build();
    }


}
