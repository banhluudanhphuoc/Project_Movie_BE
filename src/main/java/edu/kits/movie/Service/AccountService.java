package edu.kits.movie.Service;

import edu.kits.movie.Domain.Account;
import edu.kits.movie.Domain.BillingPlan;
import edu.kits.movie.Model.Request.SignUpRequest;
import edu.kits.movie.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailVerificationService emailVerificationService;

    public void registerAccount(SignUpRequest signUpRequest) {
        // Kiểm tra xem tên người dùng đã tồn tại chưa
        if (accountRepository.findByUsername(signUpRequest.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        BillingPlan bl = new BillingPlan();
        bl.setId(1);
        Account account = new Account();
        account.setUsername(signUpRequest.getUsername());
        account.setPasswords(encodedPassword);
        account.setFullName(signUpRequest.getFull_name());
        account.setAddress(signUpRequest.getAddress());
        account.setPhoneNumber(signUpRequest.getPhone_number());
        account.setAvatar(signUpRequest.getAvatar());
        account.setDateOfBirth(signUpRequest.getDateOfBirth());
        account.setEmail(signUpRequest.getEmail());
        account.setBillingPlan(bl);
        accountRepository.save(account);

        //Gửi email xác thực
        emailVerificationService.sendVerificationEmail(account);
        
    }
}
