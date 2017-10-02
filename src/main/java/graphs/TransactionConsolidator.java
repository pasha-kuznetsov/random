package graphs;

import java.util.*;

// There are multiple transactions from payee to payer.
// Consolidate all these transactions to minimum number of possible transactions.
// HINT: Consolidate transitive transactions along with similar transactions
public class TransactionConsolidator {
    private final Map<String, Payer> payers = new HashMap<>();

    TransactionConsolidator(List<Transaction> transactions) {
        for (Transaction tr : transactions)
            getPayer(tr.payer).addPayment(tr.payee, tr.amount);
    }

    public Collection<Transaction> consolidate() {
        Queue<Payer> payersQueue = new ArrayDeque<>(payers.values());
        while (!payersQueue.isEmpty()) {
            Payer payer1 = payersQueue.poll();

            Queue<Transaction> payments1 = new ArrayDeque<>(payer1.payments.values());
            while (!payments1.isEmpty()) {
                Transaction tr1 = payments1.poll();
                Payer payer2 = payers.get(tr1.payee);
                if (payer2 == null) continue;

                Queue<Transaction> payments2 = new ArrayDeque<>(payer2.payments.values());
                while (!payments2.isEmpty() && tr1.amount > 0) {
                    Transaction tr2 = payments2.poll();

                    int newPayment = Math.min(tr1.amount, tr2.amount);
                    payer1.subtractPayment(tr1, newPayment);
                    payer2.subtractPayment(tr2, newPayment);
                    if (!payer1.name.equals(tr2.payee) && payer1.addPayment(tr2.payee, newPayment))
                        payments1.add(payer1.payments.get(tr2.payee));
                }
            }
        }

        List<Transaction> transactions = new ArrayList<>();
        for (Payer payer : payers.values())
            transactions.addAll(payer.payments.values());
        return transactions;
    }

    private Payer getPayer(String name) {
        return payers.compute(name, (n, entity) -> entity != null ? entity : new Payer(name));
    }

    private static class Payer {
        String name;
        Map<String, Transaction> payments = new HashMap<>();

        Payer(String name) { this.name = name; }

        boolean addPayment(String payee, Integer amount) {
            Transaction payment = payments.compute(payee,
                    (p, tr) -> tr != null ? tr : new Transaction(name, payee, 0));
            payment.amount += amount;
            return payment.amount == amount;
        }

        void subtractPayment(Transaction tr, int amount) {
            tr.amount = tr.amount > amount ? tr.amount - amount : 0;
            if (tr.amount <= 0) payments.remove(tr.payee);
        }
    }

    static class Transaction {
        String payer;
        String payee;
        int amount;

        Transaction(String payer, String payee, int amount) {
            this.payer = payer;
            this.payee = payee;
            this.amount = amount;
        }

        @Override
        public boolean equals(Object other) { return other instanceof Transaction && equals((Transaction) other); }
        boolean equals(Transaction other) { return toString().equals(other.toString()); }

        @Override
        public String toString() { return payer + " -> " + payee + ": " + amount; }
    }
}
