package io.graydragon.horizonsdatarequest.batchfile

import org.springframework.stereotype.Component

@Component
class BatchFileParser {
    Map<String, String> parse(String batchFileText) {
        final Map<String, String> batchFileArguments = new HashMap<>()
        final List<String> lines = batchFileText.split('\n')

        boolean started = false
        for (final String rawLine : lines) {
            final String line = rawLine.trim();

            if (!started && line != '!$$SOF') {
                continue
            } else if (line == '!$$SOF') {
                started = true
                continue
            } else if (line == '!$$EOF') {
                break
            }

            final Tuple2<String, String> argument = parseArgument(line)
            batchFileArguments.put(argument.first, argument.second)
        }

        batchFileArguments
    }

    Tuple2<String, String> parseArgument(final String line) {
        final String[] lineParts = line.split('=')
        final String argumentName = lineParts[0].trim()
        final String argumentValue = lineParts[1].replace('\'', '').trim()

        new Tuple2<>(argumentName, argumentValue)
    }
}
