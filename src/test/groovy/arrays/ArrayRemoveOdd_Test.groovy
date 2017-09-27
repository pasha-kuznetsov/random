package arrays

import spock.lang.Specification
import spock.lang.Unroll

class ArrayRemoveOdd_Test extends Specification {
    @Unroll
    def "test #input"() {
        when:
        def arr = input as ArrayList<Integer>
        ArrayRemoveOdd.removeOdd(arr)

        then:
        arr == expected

        where:
        input | expected
        [1, 2, 3] | [2]
    }
}
