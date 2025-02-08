package org.example.parallelDataProcessing;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs={"-Xms4G", "-Xmx4G"})
@Measurement(iterations = 2)
@Warmup(iterations = 3)
public class ParallelStream {

    private static final long N= 10_000_000L;

//    @Benchmark
//    public long sequentialSum(){
//        return LongStream.rangeClosed(1,N)
//                .reduce(0L,Long::sum);
//    }

    @Benchmark
    public long parallelSum(){
        return LongStream.rangeClosed(1,N)
                .parallel()
                .reduce(0L,Long::sum);
    }

    @TearDown(Level.Invocation)
    public void tearDown(){
        System.gc();
    }
}
