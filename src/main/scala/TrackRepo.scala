// repo to handle track storage
class TrackRepo {
  private var tracks: Map[String, Track] = Map.empty

  def getTrackById(id: String): Option[Track] =
    tracks.get(id)

  def addTrack(track: Track): Unit =
    tracks += (track.id -> track)
}