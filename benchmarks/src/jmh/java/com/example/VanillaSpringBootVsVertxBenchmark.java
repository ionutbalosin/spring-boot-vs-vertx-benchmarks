package com.example;

import org.openjdk.jmh.annotations.*;

@Measurement(iterations = 1)
@Warmup(iterations = 1)
@Fork(value = 2, warmups = 0)
@BenchmarkMode(Mode.AverageTime)
public class VanillaSpringBootVsVertxBenchmark {

	@Benchmark
	public void vanillaSpringBoot(VanillaSpringBootState state) throws Exception {
		state.run();
	}

    @Benchmark
    public void vanillaVertx(VanillaVertxState state) throws Exception {
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

	@State(Scope.Benchmark)
	public static class VanillaVertxState extends ProcessLauncherState {
		public VanillaVertxState() {
			super("vanilla-vertx/build/libs", "-jar", jarFile("org.ib:vanilla-vertx:0.0.1-SNAPSHOT"), "--server.port=9999");
		}

		@TearDown(Level.Iteration)
		public void stop() throws Exception {
			super.after();
		}
	}

}
