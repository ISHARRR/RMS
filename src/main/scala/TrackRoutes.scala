import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._


// API routes
class TrackRoutes(trackService: TrackMethods) {
  import spray.json._

  implicit val titlesFormat: RootJsonFormat[Titles] = jsonFormat3(Titles)
  implicit val availabilityFormat: RootJsonFormat[Availability] = jsonFormat3(Availability)
  implicit val trackFormat: RootJsonFormat[Track] = jsonFormat5(Track)

  val routes: Route =
    pathPrefix("tracks") {
      concat(
        path(Segment) { id =>
          get {
            // Handle GET request to fetch track by ID
            val track = trackService.getTrackById(id)
            track match {
              case Some(t) => complete(t)
              case None    => complete("Track not found")
            }
          }
        },
        post {
          // Handle POST request to add a new track
          entity(as[Track]) { track =>
            trackService.addTrack(track)
            complete("Track added")
          }
        }
      )
    }
}