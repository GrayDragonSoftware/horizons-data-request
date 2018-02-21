package io.graydragon.horizonsdatarequest

import io.graydragon.horizonsdatarequest.batchfile.BatchFileParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
class Application {
    static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    HdrProperties hdrProperties

    @Autowired
    BatchFileParser batchFileParser

    Application() {

    }

    @PostConstruct
    void init() {
        if (!hdrProperties.batchFileLocation) {
            println("A batch file location must be supplied through the hdr.batch.file property.")
            System.exit(1)
        }

        def batchFile = new File(hdrProperties.batchFileLocation)
        if (!batchFile.exists()) {
            println("The batch-file at location ${batchFile.getPath()} does not exist. Please provide a valid path.")
            System.exit(1)
        }

        final String batchFileText = batchFile.text
        final Map<String, String> batchFileArguments = batchFileParser.parse(batchFileText)

        StringBuilder urlBuilder = new StringBuilder()
        urlBuilder.append('https://ssd.jpl.nasa.gov/horizons_batch.cgi?batch=1')
        batchFileArguments.entrySet().each { Map.Entry<String, String> entry ->
            urlBuilder.append("&${entry.key}='${entry.value}'")
        }

        println(urlBuilder.toString())
    }
}
