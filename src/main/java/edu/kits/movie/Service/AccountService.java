package edu.kits.movie.Service;

import edu.kits.movie.Domain.*;
import edu.kits.movie.Model.Request.SignUpRequest;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    private final PasswordEncoder passwordEncoder;


    private final EmailVerificationService emailVerificationService;
    private final AuthorityRepository authorityRepository;
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


        AuthorityId authorityId = new AuthorityId();
        Role role = new Role();
        role.setId(2);
        authorityId.setUsername(account.getUsername());
        authorityId.setRoleId(2);
        Authority authority = new Authority();
        authority.setId(authorityId);
        authority.setUsername(account);
        authority.setRole(role);

        //Gửi email xác thực
        emailVerificationService.sendVerificationEmail(account);
        accountRepository.save(account);
        authorityRepository.save(authority);
        
    }
}
