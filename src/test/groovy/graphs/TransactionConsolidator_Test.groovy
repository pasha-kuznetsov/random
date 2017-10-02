package graphs

import spock.lang.Specification
import spock.lang.Unroll

class TransactionConsolidator_Test extends Specification {

    @Unroll
    def "test #expectedConsolidated"() {
        when:
        def consolidator = new TransactionConsolidator(transactions);

        then:
        consolidator.consolidate() == expectedConsolidated

        where:
        transactions | expectedConsolidated

        [
                tran("Chase", "BoA", 132),
                tran("BoA", "Chase", 100)
        ]            | [
                tran("Chase", "BoA", 32)
        ]

        [
                tran("Chase", "BoA", 100),
                tran("BoA", "Chase", 132)
        ]            | [
                tran("BoA", "Chase", 32)
        ]

        [
                tran("Chase", "BoA", 132),
                tran("BoA", "Chase", 132)
        ]            | [
        ]

        [
                tran("Chase", "BoA", 100),
                tran("BoA", "Wells", 132)
        ]            | [
                tran("BoA", "Wells", 32),
                tran("Chase", "Wells", 100)
        ]

        [
                tran("Chase", "BoA", 132),
                tran("BoA", "Wells", 100)
        ]            | [
                tran("Chase", "Wells", 100),
                tran("Chase", "BoA", 32)
        ]

        [
                tran("Chase", "Wells", 100),
                tran("Chase", "BoA", 32),
                tran("BoA", "Wells", 32)
        ]            | [
                tran("Chase", "Wells", 132)
        ]

        [
                tran("Chase", "BoA", 132),
                tran("Chase", "BoA", 827),
                tran("BoA", "Wells", 751),
                tran("Chase", "BoA", 585),
                tran("Wells", "Chase", 877),
                tran("Chase", "Wells", 157),
                tran("Chase", "Wells", 904),
                tran("BoA", "Chase", 976),
                tran("Wells", "Chase", 548),
                tran("Wells", "BoA", 872)
        ]            | [
                tran("Chase", "BoA", 204),
                tran("Wells", "BoA", 485)
        ]
    }

    private static TransactionConsolidator.Transaction tran(String payer, String payee, Integer amount) {
        ["payee": payee, "amount": amount, "payer": payer] as TransactionConsolidator.Transaction
    }
}
