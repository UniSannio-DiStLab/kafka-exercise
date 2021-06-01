package unisannio.dist.kafka.constants;

public class KafkaConstants {
	public static String KAFKA_BROKERS = "172.18.10.144:31090,172.18.10.144:31091,172.18.10.144:31092";
	
	public static Integer MESSAGE_COUNT=100000;
	
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static String OFFSET_RESET_LATEST="latest";
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=1;
}
