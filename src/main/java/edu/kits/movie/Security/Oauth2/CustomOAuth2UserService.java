package edu.kits.movie.Security.Oauth2;

import edu.kits.movie.Entity.*;
import edu.kits.movie.Exception.OAuth2AuthenticationProcessingException;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Security.User.OAuth2UserInfo;
import edu.kits.movie.Security.User.OAuth2UserInfoFactory;
import edu.kits.movie.Security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final AccountRepository accountRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (oAuth2UserInfo.getEmail().isEmpty()) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<Account> userOptional = accountRepository.findByEmail(oAuth2UserInfo.getEmail());
        Account user;
        if (userOptional.isPresent()) {
            if (userOptional.get().getSocialLogin().getProvider().equalsIgnoreCase("local")) {
                String rawString = "Email này đã được sử dụng bởi một tài khoản khác";
                String error_message = URLEncoder.encode(rawString, StandardCharsets.UTF_8);
                throw new OAuth2AuthenticationProcessingException(error_message);
            }
            user = userOptional.get();
//            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
//                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
//                        user.getProvider() + " account. Please use your " + user.getProvider() +
//                        " account to login.");
//            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        Account user = new Account();

//        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//        user.setProviderId(oAuth2UserInfo.getId());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setFullName(oAuth2UserInfo.getName());
        user.setUsername(oAuth2UserInfo.getEmail());
        BillingPlan billingPlan = new BillingPlan();
        billingPlan.setId(1);
        SocialLogin socialLogin = new SocialLogin();
        socialLogin.setId(2);
        user.setBillingPlan(billingPlan);
        user.setSocialLogin(socialLogin);
//        user.setImgUrl(oAuth2UserInfo.getImageUrl());
        return accountRepository.save(user);
    }

    private Account updateExistingUser(Account existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setFullName(oAuth2UserInfo.getName());
//        existingUser.setImgUrl(oAuth2UserInfo.getImageUrl());
        return accountRepository.save(existingUser);
    }
}
