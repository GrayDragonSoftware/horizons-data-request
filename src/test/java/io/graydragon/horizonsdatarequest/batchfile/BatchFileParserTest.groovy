package io.graydragon.horizonsdatarequest.batchfile

class BatchFileParserTest extends GroovyTestCase {
    BatchFileParser batchFileParser = new BatchFileParser()

    void testParse() {
        String batchFileText = this.getClass().getResource('/../resources/batch-file.txt').text

        Map<String, String> batchFileArguments = batchFileParser.parse(batchFileText)

        assert batchFileArguments.get('COMMAND') == '301'
        assert batchFileArguments.get('CENTER') == 'coord'
        assert batchFileArguments.get('COORD_TYPE') == 'GEODETIC'
        assert batchFileArguments.get('SITE_COORD') == '-71.05690,+42.35670,0'
        assert batchFileArguments.get('MAKE_EPHEM') == 'YES'
        assert batchFileArguments.get('TABLE_TYPE') == 'OBSERVER'
        assert batchFileArguments.get('START_TIME') == '2018-02-20'
        assert batchFileArguments.get('STOP_TIME') == '2018-03-02'
        assert batchFileArguments.get('STEP_SIZE') == '15 m'
        assert batchFileArguments.get('CAL_FORMAT') == 'CAL'
        assert batchFileArguments.get('TIME_DIGITS') == 'MINUTES'
        assert batchFileArguments.get('ANG_FORMAT') == 'HMS'
        assert batchFileArguments.get('OUT_UNITS') == 'KM-S'
        assert batchFileArguments.get('RANGE_UNITS') == 'AU'
        assert batchFileArguments.get('APPARENT') == 'REFRACTED'
        assert batchFileArguments.get('SUPPRESS_RANGE_RATE') == 'NO'
        assert batchFileArguments.get('SKIP_DAYLT') == 'NO'
        assert batchFileArguments.get('EXTRA_PREC') == 'NO'
        assert batchFileArguments.get('R_T_S_ONLY') == 'NO'
        assert batchFileArguments.get('REF_SYSTEM') == 'J2000'
        assert batchFileArguments.get('CSV_FORMAT') == 'YES'
        assert batchFileArguments.get('OBJ_DATA') == 'YES'
        assert batchFileArguments.get('QUANTITIES') == '4'

        assert batchFileArguments.size() == 23
    }
}
