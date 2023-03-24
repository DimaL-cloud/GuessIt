package ua.dimalustyuk.GuessIt;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GuessItApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	static void initJfxRuntime() {
		Platform.startup(() -> {});
	}
}
