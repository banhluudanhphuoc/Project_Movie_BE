package edu.kits.movie.Service;

import edu.kits.movie.Entity.Account;
import edu.kits.movie.Repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = {Exception.class})
public class EmailVerificationService {

    @Autowired
    private AccountRepository accountRepository;

    public void sendVerificationEmail(Account account) {
        // Tạo mã xác thực email
        String verificationCode = generateVerificationCode();

        // Lưu mã xác thực vào cơ sở dữ liệu
        account.setVerificationCode(verificationCode);
        accountRepository.save(account);

        // Gửi email chứa mã xác thực đến địa chỉ email người dùng
        sendEmail(account.getEmail(), "Email Verification", "Your Verification Code: " + verificationCode);
    }

    public void verifyEmail(String username, String verificationCode) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new RuntimeException("Account does not exist");
        }

        // Kiểm tra xem mã xác thực có khớp không
        if (verificationCode.equals(account.getVerificationCode())) {
            // Đánh dấu email đã được xác thực
            account.setEmailVerified(true);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Invalid authentication code");
        }
    }

    private String generateVerificationCode() {
        //Tạo mã xác thực

            String characters = "0123456789";
            int length = 4;
            return RandomStringUtils.random(length, characters);
        }

    @Autowired
    private JavaMailSender javaMailSender;

    private void sendEmail(String to, String subject, String content) {
        // Gửi email đến địa chỉ to với tiêu đề subject và nội dung content
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

    }




