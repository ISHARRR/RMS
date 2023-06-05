import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor


object Main {
  def main(args: Array[String]): Unit = {
    // Create an Actor System for Akka HTTP
    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "tracks-api")
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    // Create the track repo, methods, and routes
    val trackRepository: TrackRepo = new TrackRepo()
    val trackService: TrackMethods = new TrackMethods(trackRepository)
    val trackRoutes: TrackRoutes = new TrackRoutes(trackService)

    val host = "localhost"
    val port = 8080

    // Start the server and bind the routes to the specified host and port
    val bindingFuture = Http().newServerAt(host, port).bind(trackRoutes.routes)
    println(s"Server - http://$host:$port/")

    // Shutdown the server gracefully when terminated
    scala.sys.addShutdownHook {
      bindingFuture
        .flatMap(_.unbind())
        .onComplete(_ => system.terminate())
    }
  }
}
