// create method to handle track operations
class TrackMethods(trackRepository: TrackRepo) {
  def getTrackById(id: String): Option[Track] =
    trackRepository.getTrackById(id)

  def addTrack(track: Track): Unit =
    trackRepository.addTrack(track)
}