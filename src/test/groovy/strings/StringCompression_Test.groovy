package strings

import spock.lang.Specification
import spock.lang.Unroll

class StringCompression_Test extends Specification {

    @Unroll
    def "test compress #decompressed"() {
        expect:
        StringCompression.compress(decompressed) == compressed

        where:
        decompressed | compressed
        'aaabbbcc'   | 'a3b3cc'
        'abbbcc'     | 'ab3cc'
    }

    @Unroll
    def "test decompress #compressed"() {
        expect:
        StringCompression.decompress(compressed) == decompressed

        where:
        compressed | decompressed
        'a3b3cc'   | 'aaabbbcc'
        'ab3c2'    | 'abbbcc'
    }
}
