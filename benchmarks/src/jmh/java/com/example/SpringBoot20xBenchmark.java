package com.example;

import org.openjdk.jmh.annotations.*;

@Measurement(iterations = 5)
@Warmup(iterations = 2)
@Fork(value = 2, warmups = 0)
@BenchmarkMode(Mode.AverageTime)
public class SpringBoot20xBenchmark {

	@Benchmark
	public void vanillaSpringBoot(VanillaSpringBootState state) throws Exception {
		state.run();
	}

	public static void main(String[] args) throws Exception {
		VanillaSpringBootState state = new VanillaSpringBootState();
		state.run();
	}

	@State(Scope.Benchmark)
	public static class VanillaSpringBootState extends ProcessLauncherState {
		public VanillaSpringBootState() {
            super("vanilla-spring-boot/build/libs", "-jar", jarFile("org.ib:vanilla-spring-boot:0.0.1-SNAPSHOT"), "--server.port=0");
		}

		@TearDown(Level.Iteration)
		public void stop() throws Exception {
			super.after();
		}
	}

}
