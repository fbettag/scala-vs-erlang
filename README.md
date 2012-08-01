# Scala (and Akka) vs Erlang Actors

Benchmark for testing Actor implementations on Scala, Akka and Erlang.
The Actor has 2 responsibilities:
1) Add a count X to the Actors current tally
2) Get the current count and reset the agent count to zero

I've used Scala 2.9.1 and Erlang 5.8.5.

My results can be found in my Blogpost [Scala, Akka and Erlang Actor Benchmarks](http://uberblo.gs/2011/12/scala-akka-and-erlang-actor-benchmarks).

To run the erlang OTP version compile the 3 files then run client:runTest(3000000).

To run the erlang bare receive version compile server2 as well and then run client:runTest2(3000000).

To run the scala version do ```./sbt run``` and select one of the benchmarks.


