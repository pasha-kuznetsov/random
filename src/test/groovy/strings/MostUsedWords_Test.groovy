package strings

import spock.lang.Specification
import spock.lang.Unroll

class MostUsedWords_Test extends Specification {

    @Unroll
    def "test #text"() {
        def code = new MostUsedWords()

        expect:
        code.mostUsedWords(text.split(), n) == expected

        where:
        text                | n | expected
        ''                  | 3 | []
        "a b aa aa b cc cc" | 2 | ['cc', 'aa']
    }
}
