package edu.kits.movie.Service;

import edu.kits.movie.Dto.Response.OrderResponse;

public interface PaymentService {
    OrderResponse checkout(Integer id);
}
