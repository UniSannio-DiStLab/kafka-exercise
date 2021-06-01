package unisannio.dist.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import unisannio.dist.kafka.constants.KafkaConstants;
import unisannio.dist.kafka.consumer.ConsumerCreator;

import java.time.Duration;

public class ConsumerMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerMain.class);
    public static void main(String[] args){
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer("demo","consumerGroup1");

        int noMessageToFetch = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            if (consumerRecords.count() == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > KafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {
                LOGGER.info("Record Key {}",record.key());
                LOGGER.info("Record value {}",record.value());
                LOGGER.info("Record partition {}",record.partition());
                LOGGER.info("Record offset {}",record.offset());
            });
            consumer.commitAsync();
        }
        consumer.close();
    }
}
