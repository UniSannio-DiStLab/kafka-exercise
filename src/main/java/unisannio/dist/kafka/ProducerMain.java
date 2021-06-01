package unisannio.dist.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unisannio.dist.kafka.constants.KafkaConstants;
import unisannio.dist.kafka.producer.ProducerCreator;

import java.util.concurrent.ExecutionException;

public class ProducerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerMain.class);
    public static void main(String[] args){
        Producer<Long, String> producer = ProducerCreator.createProducer("producerId");

        for (int index = 0; index < KafkaConstants.MESSAGE_COUNT; index++) {
            final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("demo", (long) index,
                    "This is record " + index);
            try {
                RecordMetadata metadata = producer.send(record).get();
                LOGGER.info("Record sent to partition {} with offset {}", metadata.partition(),metadata.offset());
            } catch (ExecutionException | InterruptedException e) {
                LOGGER.error("Error in sending record",e);
            }
        }
    }
}
