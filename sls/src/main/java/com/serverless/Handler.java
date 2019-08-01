package com.serverless;

import calc.CalcFactory;
import calc.CalcJNA;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serverless.exceptions.InvalidJsonException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class Handler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = Logger.getLogger(Handler.class);

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: " + input);

		String json = input.get("body").toString();

		final String msg = getCalcResult(json);

		Response responseBody = new Response(msg);

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(responseBody)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}

	private String getCalcResult(String json) {

		try {
			final ObjectMapper mapper = new ObjectMapper();
			final Data data = mapper.readValue(json, Data.class);

			final int a = data.getA();
			final int b = data.getB();

			CalcJNA calcJNA = CalcFactory.create(System.getenv("LIB_CALC"));

			switch (data.getOperation()) {
				case sum:
					return String.format("%d + %d => %d", a, b, calcJNA.sum(a, b));
				case mult:
					return String.format("%d * %d => %d", a, b, calcJNA.mult(a, b));
				default:
					throw new UnsupportedOperationException(String.format("Unsupported operataion: %s", data.getOperation()));
			}


		} catch (IOException e) {
			LOG.error("Invalid json", e);
			throw new InvalidJsonException(json);
		}
	}
}
