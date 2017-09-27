package arrays

import spock.lang.Specification
import spock.lang.Unroll

class ArraySpiral_Test extends Specification {

    @Unroll
    def "test #rows x #columns"() {
        expect:
        ArraySpiral.fill(rows, columns) == expectedOutput as int[][]

        where:
        rows || columns || expectedOutput
        0    || 0       || []
        1    || 1       || [[1]]
        1    || 3       || [[1, 2, 3]]
        3    || 1       || [[1],
                            [2],
                            [3]]
        2    || 3       || [[1, 2, 3],
                            [6, 5, 4]]
        3    || 2       || [[1, 2],
                            [6, 3],
                            [5, 4]]
        3    || 3       || [[1, 2, 3],
                            [8, 9, 4],
                            [7, 6, 5]]
    }
}
