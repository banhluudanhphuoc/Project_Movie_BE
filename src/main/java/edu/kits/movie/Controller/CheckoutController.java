package edu.kits.movie.Controller;

import edu.kits.movie.Controller.Router.Api;
import edu.kits.movie.Dto.Response.OrderResponse;
import edu.kits.movie.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Api.UserApi.USER_BASE)
public class CheckoutController {
    private final PaymentService paymentService;
    @PostMapping("payment")
    public ResponseEntity<OrderResponse> checkout(@RequestParam Integer billingPlanId){
        return ResponseEntity.ok(paymentService.checkout(billingPlanId));
    }
}
