package strings

import spock.lang.Specification
import spock.lang.Unroll

class MostFrequentChar_Test extends Specification {
    @Unroll
    def "#str"() {
        expect:
        MostFrequentChar.mostFrequentChar(str) == expectedResult

        where:
        str      | expectedResult
        'test'   | 't'
        'testee' | 'e'
    }
}
