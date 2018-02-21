package io.graydragon.horizonsdatarequest

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
public class HdrProperties {
    @Value('${hdr.batch.file}')
    String batchFileLocation
}
