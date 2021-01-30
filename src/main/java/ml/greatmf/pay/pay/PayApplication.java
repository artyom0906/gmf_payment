package ml.greatmf.pay.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class PayApplication {

	public static void main(String[] args) {
		IntStream.range(0, 1_000_000)
				.filter(e->e%2==0)
				.reduce(Integer::sum)
				.ifPresent(System.out::println);

	}
	Comparator<Integer> comparator = this::test;
	private int test(Integer a, Integer b) {
		return a - b;
	}
}
