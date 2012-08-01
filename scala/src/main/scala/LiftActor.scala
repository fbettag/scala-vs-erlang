package ag.bett.scala.test.lift

import _root_.scala.compat.Platform
import ag.bett.scala.test._

import net.liftweb.actor._


object Application {
	val runs = 12000000
	val counter = CounterActor

	def main(args: Array[String]) {
		start()
		stop()
		sys.exit(0)
	}

	def start() { runTest(runs) }
	def stop() { }


	def runTest(msgCount: Long) {
		val start = Platform.currentTime
		val count = theTest(msgCount)
		val finish = Platform.currentTime
		val elapsedTime = (finish - start) / 1000.0

		printf("%n")
		printf("%n")
		printf("[lift] Count is %s%n",count)
		printf("[lift] Test took %s seconds%n", elapsedTime)
		printf("[lift] Throughput=%s per sec%n", msgCount / elapsedTime)
		printf("%n")
		printf("%n")
	}

	def theTest(msgCount: Long): Any = {
		val bytesPerMsg = 100
		val updates = (1L to msgCount).par.foreach((x: Long) => counter ! new AddCount(bytesPerMsg))

		val count = (counter !! GetAndReset).open_!
		return count
	}

}


object CounterActor extends LiftActor {
	var count: Long = 0

	def messageHandler = {
		case GetAndReset =>
			val current = count
			count = 0
			reply(current)
		case AddCount(extraCount) =>
			count=count+extraCount
	}
}

