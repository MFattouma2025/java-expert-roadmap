package com.expertjava.jvm;

import java.util.ArrayList;
import java.util.List;

public class MemoryPressureDemo {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[1_000_000]); // 1MB
        }
    }
}

// resultat attendu : 


// active le log  : java -Xlog:gc -Xmx128m -Xms128m MemoryPressureDemo
//
//[0.021s][info][gc] Using G1
//[0.852s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 14M->1M(128M) 5.328ms
//[1.213s][info][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 15M->4M(128M) 9.773ms
//[1.508s][info][gc] GC(2) Pause Young (Concurrent Start) (G1 Humongous Allocation) 63M->59M(128M) 4.998ms
//[1.508s][info][gc] GC(3) Concurrent Undo Cycle
//[1.511s][info][gc] GC(3) Concurrent Undo Cycle 2.499ms
//[1.516s][info][gc] GC(4) Pause Young (Concurrent Start) (G1 Humongous Allocation) 64M->64M(128M) 4.466ms
//[1.517s][info][gc] GC(5) Concurrent Mark Cycle
//[1.534s][info][gc] GC(5) Pause Remark 95M->95M(128M) 1.406ms
//[1.538s][info][gc] GC(5) Pause Cleanup 103M->103M(128M) 0.110ms
//[1.541s][info][gc] GC(5) Concurrent Mark Cycle 24.470ms
//[1.546s][info][gc] GC(6) Pause Young (Concurrent Start) (G1 Humongous Allocation) 109M->109M(128M) 4.318ms
//[1.546s][info][gc] GC(7) Concurrent Mark Cycle
//[1.556s][info][gc] GC(8) Pause Young (Normal) (G1 Humongous Allocation) (Evacuation Failure: Allocation) 126M->127M(128M) 7.072ms
//[1.592s][info][gc] GC(15) Pause Full (G1 Compaction Pause) 127M->127M(128M) 8.730ms
//[1.602s][info][gc] GC(16) Pause Full (G1 Compaction Pause) 127M->127M(128M) 9.821ms
//[1.603s][info][gc] GC(11) Concurrent Mark Cycle 33.101ms
//[1.606s][info][gc] GC(17) Pause Young (Normal) (G1 Evacuation Pause) 127M->127M(128M) 0.729ms
//[1.615s][info][gc] GC(18) Pause Full (G1 Compaction Pause) 127M->2M(128M) 8.244ms
//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        at com.expertjava.jvm.MemoryPressureDemo.main(MemoryPressureDemo.java:10)
//        at java.base/java.lang.invoke.LambdaForm$DMH/0x000001dcc5024400.invokeStatic(LambdaForm$DMH)
//        at java.base/java.lang.invoke.LambdaForm$MH/0x000001dcc5149400.invoke(LambdaForm$MH)
//        at java.base/java.lang.invoke.Invokers$Holder.invokeExact_MT(Invokers$Holder)
//        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invokeImpl(DirectMethodHandleAccessor.java:154)
//
////
