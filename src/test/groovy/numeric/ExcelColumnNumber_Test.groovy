package numeric

import spock.lang.Specification
import spock.lang.Unroll

class ExcelColumnNumber_Test extends Specification {
    @Unroll
    def "test #n"() {
        expect:
        ExcelColumnNumber.toString(n) == expectedOutput

        where:
        n   || expectedOutput
        0   || ''
        1   || 'A'      // % 26 -> 1  'A'
        26  || 'Z'      // % 26 -> 0  'Z',  - 1, / 26 -> 0
        27  || 'AA'     // % 26 -> 1  'A',       / 26 -> 1   % 26 -> 1 'A'
        51  || 'AY'     // % 26 -> 25 'Y',       / 26 -> 1   % 26 -> 1 'A'
        52  || 'AZ'     // % 26 -> 0  'Z',  - 1, / 26 -> 1   % 26 -> 1 'A'
        80  || 'CB'
        676 || 'YZ'
        702 || 'ZZ'     // % 26 -> 0  'Z',  - 1, / 26 -> 26  % 26 -> 0 -> 'Z'   - 1, / 26 = 0
        705 || 'AAC'
    }
}
