package org.spring.dev.company.service.member;

import lombok.RequiredArgsConstructor;
import org.spring.dev.company.config.EmailMessage;
import org.spring.dev.company.dto.member.MailHandler;
import org.spring.dev.company.entity.member.MemberEntity;
import org.spring.dev.company.repository.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final MemberRepository memberRepository;


    private final MemberService memberService;
    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final JavaMailSenderImpl javaMailSenderImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void mailSend(HttpSession session, String email) {
        try{
            MailHandler mailHandler = new MailHandler(javaMailSenderImpl);
            Random random = new Random(System.currentTimeMillis());
            Long start = System.currentTimeMillis();

            int result = 100000 + random.nextInt(900000);

            // 받는 사람
            mailHandler.setTo(email);
            // 보내는 사람
            mailHandler.setFrom("yyy042352@gmail.com");
            // 제목
            mailHandler.setSubject("인증번호입니다.");
            // HTML Layout
            String htmlContent = "<p>인증번호 : + " + result + "</p>";
            mailHandler.setText(htmlContent, true);

            mailHandler.send();

            Long end = System.currentTimeMillis();

            session.setAttribute(""+email,result);
            System.out.println("실행시간: " + ( end - start)/ 1000.0);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }







    public String sendMail(EmailMessage emailMessage, String type) {
        String authNum = createCode();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        if (type.equals("password")){
            Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(emailMessage.getTo());

            memberService.SetTempPassword(emailMessage.getTo(), authNum);
        }

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo()); // 메일 수신자
            mimeMessageHelper.setSubject(emailMessage.getSubject()); // 메일 제목
            mimeMessageHelper.setText(setContext(authNum, type), true); // 메일 본문 내용, HTML 여부
            javaMailSender.send(mimeMessage);


            return authNum;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    // 인증번호 및 임시 비밀번호 생성 메서드
    public String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

    // thymeleaf를 통한 html 적용
    public String setContext(String code, String type) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(type, context);
    }


    public boolean emailCertification(HttpSession session, String email, int inputCode) {
        try{
            int generationCode = (int) session.getAttribute((email));

            if(generationCode == inputCode){
                return true;
            } else{
                return false;
            }
        } catch (Exception e){
            throw e;
        }
    }

    public String getPasswordByEmail(String email) {
        return memberRepository.findByPassword(email);
    }

    @Transactional
    public void updatePassword(String email , String password) {

        String encodedPassword = passwordEncoder.encode(password);

        memberRepository.updateUserPassword1(email, encodedPassword);
    }
}
