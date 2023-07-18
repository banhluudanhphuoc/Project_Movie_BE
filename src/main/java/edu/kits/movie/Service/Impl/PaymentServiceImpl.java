package edu.kits.movie.Service.Impl;

import edu.kits.movie.Common.Mapper.ModelConverter;
import edu.kits.movie.Dto.Response.OrderResponse;
import edu.kits.movie.Entity.*;
import edu.kits.movie.Repository.AccountRepository;
import edu.kits.movie.Repository.BillingPlanRepository;
import edu.kits.movie.Repository.OrderRepository;
import edu.kits.movie.Service.PaymentService;
import edu.kits.movie.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class PaymentServiceImpl implements PaymentService {
    private final ModelConverter converter;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final UserService userService;
    private final BillingPlanRepository billingPlanRepository;
    @Override
    public OrderResponse checkout(Integer billingPlanId) {
        //id 1 = free, id 2 = premium
        BillingPlan billingPlan = billingPlanRepository.findById(billingPlanId).orElse(null);
        if (billingPlan == null){
            return null;
        }
        String username = userService.getCurrentUser();

        Account account = accountRepository.findByUsername(username);
        //id 1 = visa_card
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(1);

        //id 1 = pending 2 = success
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(1);

        Order order = new Order();
        order.setAccount(account);
        order.setPaymentMethod(paymentMethod);
        order.setPaymentStatus(paymentStatus);
        order.setBillingPlan(billingPlan);
        order.setAmount(billingPlan.getPrice());
        Order savedOrder = orderRepository.save(order);


        Order orderResponse = orderRepository.findById(savedOrder.getId()).orElse(null);
        // if current plan diff from new plan change to new plan
        if(!Objects.equals(account.getBillingPlan().getId(), billingPlanId)){
            LocalDateTime newBillingPlan = LocalDateTime.now().plusMonths(billingPlan.getDurationInMonth());
            account.setBillingPlan(billingPlan);
            account.setBillingPlanExpiredAt(newBillingPlan);
            accountRepository.save(account);
        }else{
            LocalDateTime currentBillingPlanDate = account.getBillingPlanExpiredAt();
            LocalDateTime newBillingPlanDate = currentBillingPlanDate.plusMonths(billingPlan.getDurationInMonth());
            account.setBillingPlanExpiredAt(newBillingPlanDate);
            accountRepository.save(account);
        }
        return converter.map(orderResponse, OrderResponse.class);
    }
}
