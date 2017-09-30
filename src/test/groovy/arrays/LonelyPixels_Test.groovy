package arrays

import spock.lang.Specification
import spock.lang.Unroll

class LonelyPixels_Test extends Specification {

    @Unroll
    def "test #image"() {
        expect:
        LonelyPixels.countLonelyBlackPixels(image as int[][]) == expectedCount

        where:
        image          | expectedCount
        []             | 0
        [[]]           | 0
        [[0, 0, 0],
         [0, 0, 0],
         [0, 0, 0]]    | 0
        [[0, 1, 0],
         [1, 1, 0],
         [0, 0, 1]]    | 1
        [[1, 0, 0, 1],
         [0, 1, 0, 1],
         [0, 0, 1, 0],
         [0, 0, 0, 1]] | 1
        [[1, 0, 0, 1],
         [0, 1, 0, 1],
         [1, 0, 1, 0],
         [0, 0, 0, 1]] | 0
        [[0, 1, 0, 0],
         [1, 0, 0, 0],
         [0, 0, 1, 0],
         [0, 0, 0, 1]] | 4
    }
}
