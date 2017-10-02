package graphs;

import java.util.*;

// There are multiple transactions from payee to payer.
// Consolidate all these transactions to minimum number of possible transactions.
// HINT: Consolidate transitive transactions along with similar transactions
public class TransactionConsolidator {
    private final Map<String, Entity> entities = new HashMap<>();
    private final Set<Entity> payers = new HashSet<>();

    TransactionConsolidator(List<Transaction> transactions) {
        for (Transaction tr : transactions) {
            Entity payer = getEntity(tr.payer);
            Entity payee = getEntity(tr.payee);
            addPayment(payer, payee, tr.amount);
            payers.add(payer);
        }
    }

    public Collection<Transaction> consolidate() {
        Queue<Entity> payers = new ArrayDeque<>(this.payers);
        while (!payers.isEmpty()) {
            Entity payer = payers.poll();

            Queue<Payment> payments = new ArrayDeque<>(payer.payments.values());
            while (!payments.isEmpty()) {
                Payment payment1 = payments.poll();
                Entity payee = payment1.payee;
                if (payment1.amount <= 0) continue;

                Queue<Payment> nextPayments = new ArrayDeque<>(payee.payments.values());
                while (!nextPayments.isEmpty()) {
                    Payment payment2 = nextPayments.poll();
                    Entity nextPayee = payment2.payee;
                    if (payment2.amount <= 0) continue;

                    int newPayment = Math.min(payment1.amount, payment2.amount);

                    if (payment1.amount > newPayment)
                        payment1.amount -= newPayment;
                    else
                        payer.payments.remove(payee);

                    if (payment2.amount > newPayment)
                        payment2.amount -= newPayment;
                    else
                        payee.payments.remove(nextPayee);

                    if (nextPayee != payer && addPayment(payer, nextPayee, newPayment))
                        payments.add(payer.payments.get(nextPayee));
                }
            }

            if (payer.payments.isEmpty())
                this.payers.remove(payer);
        }

        List<Transaction> transactions = new ArrayList<>();
        for (Entity payer : this.payers) {
            for (Payment payment : payer.payments.values()) {
                Transaction tr = new Transaction();
                tr.payer = payer.name;
                tr.payee = payment.payee.name;
                tr.amount = payment.amount;
                transactions.add(tr);
            }
        }
        return transactions;
    }

    private Entity getEntity(String name) {
        return entities.compute(name, (n, entity) -> entity != null ? entity : new Entity(name));
    }

    private boolean addPayment(Entity payer, Entity payee, Integer amount) {
        Payment payment = payer.payments.compute(payee, this::getPayment);
        payment.amount += amount;
        return payment.amount == amount;
    }

    private Payment getPayment(Entity payee, Payment payment) {
        return payment != null ? payment : new Payment(payee);
    }

    static class Entity {
        String name;
        Map<Entity, Payment> payments = new HashMap<>();
        Entity(String name) { this.name = name; }
    }

    static class Payment {
        Entity payee;
        int amount;
        Payment(Entity payee) { this.payee = payee; }
    }

    static class Transaction {
        String payee;
        String payer;
        int amount;

        @Override
        public boolean equals(Object other) { return other instanceof Transaction && equals((Transaction) other); }
        boolean equals(Transaction other) { return toString().equals(other.toString()); }

        @Override
        public String toString() { return payer + " -> " + payee + ": " + amount; }
    }
}
