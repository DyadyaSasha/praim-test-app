package app.jmh;

import app.Task;
import jmh.mbr.junit5.Microbenchmark;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

/**
 * Тест на производительность с использованием JMH
 *
 * @author Alex Serebryakov
 * @since 1.0
 */
@Microbenchmark
public class Benchmarks {

    @State(value = Scope.Benchmark)
    public static class Fixture {

        private String input;
        private Task task;

        public String getInput() {
            return input;
        }

        public Task getTask() {
            return task;
        }

        @Setup(value = Level.Trial)
        public void setup(){
            this.input = "23;44;458887;0;99991;-16;555521;999999994;-123;-24;1231;22;135;22;1;-6";
            this.task = new Task();
        }

        @TearDown(value = Level.Trial)
        public void tearDown(){
            this.input = null;
            this.task = null;
        }

    }

    @Benchmark
//    @BenchmarkMode(value = Mode.All)
    @BenchmarkMode(value = Mode.Throughput)
    @OutputTimeUnit(value = TimeUnit.NANOSECONDS)
    @Warmup(iterations = 3, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement(iterations = 3, timeUnit = TimeUnit.NANOSECONDS)
    @Fork(value = 2)
    public void testGetSortedArray(Fixture fixture, Blackhole blackhole){
        blackhole.consume(fixture.task.getSortedArray(fixture.input));
    }

}
