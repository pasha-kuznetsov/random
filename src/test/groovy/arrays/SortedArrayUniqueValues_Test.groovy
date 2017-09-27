package arrays

import spock.lang.Specification
import spock.lang.Unroll

class SortedArrayUniqueValues_Test extends Specification {
    @Unroll
    def "test #array"() {
        expect:
        SortedArrayUniqueValues.uniqueValues(array as int[]) == unique

        where:
        array                          | unique
        [0, 1, 2, 3, 3, 4, 5, 5, 5, 6] | [0, 1, 2, 4, 6]
    }
}
