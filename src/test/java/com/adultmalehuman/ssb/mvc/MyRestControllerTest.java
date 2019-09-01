package com.adultmalehuman.ssb.mvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Disabled("This is difficult to get working with the Micrometer meter registry")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Mock
	MeterRegistry.Config meterRegistryConfigMock;

	@Mock
	Clock clockMock;

	@MockBean
	MeterRegistry meterRegistryMock;

	@Test
	public void getHello() throws Exception {
		when(meterRegistryMock.config()).thenReturn(meterRegistryConfigMock);
		when(meterRegistryConfigMock.clock()).thenReturn(clockMock);
		when(clockMock.wallTime()).thenReturn((new Date()).getTime());
		when(clockMock.monotonicTime()).thenReturn((new Date()).getTime());

		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		   .andExpect(status().isOk())
		   .andExpect(content().string(equalTo(MyRestController.CANNED_REPLY_STRING)));
	}

}
