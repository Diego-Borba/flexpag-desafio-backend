package com.flexpag.paymentscheduler;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@RestController
@AllArgsConstructor
public class PaymentController {

    PaymentRepository repository;

    // LISTA TODOS OS AGENDAMENTOS
    @GetMapping("/payment")
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    // LISTA O AGENDAMENTO POR ID
    @GetMapping("/payment/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return repository.findById(id).get();

    }

    // EFETUA O AGENDAMENTO
    @PostMapping("/payment")
    public Payment savePayment(@RequestBody Payment payment) {
        payment.status = "pending";
        return repository.save(payment);
    }

    // CONSULTA O STATUS DO PAGAMENTO
    @GetMapping("/payment/{id}/{status}")
    public String statusById(@PathVariable Long id, @PathVariable String status) {
        return repository.findById(id).get().getStatus();
    }

    // EFETUA O PAGAMENTO VERIFICANDO O SEU STATUS
    @PutMapping("/payment/{id}/{status}")
    public Payment makePayment(@PathVariable Long id, @RequestBody Payment payment) {
        payment = repository.findById(id).get();
        if (payment.getStatus().equals("pending")) {
            payment.setStatus("paid");
        }
        return repository.save(payment);
    }

    // FAZ O UPDATE PELO ID DO PAGAMENTO APENAS SE O STATUS DO PAGAMENTO FOR
    // "pending"
    @PutMapping("/payment/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        Payment payment = repository.findById(id).get();

        if (payment.getStatus().equals("pending")) {
            payment.setData(updatedPayment.getData());
            payment.setHora(updatedPayment.getHora());
            repository.save(payment);
        }
        return payment;

    }

    // DELETA O AGENDAMENTO PELO ID
    @DeleteMapping("/payment/{id}")
    public void deletePayment(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
