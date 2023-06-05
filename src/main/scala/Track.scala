final case class Track(`type`: String, id: String, urn: String, titles: Titles, availability: Availability)

final case class Titles(primary: String, secondary: String, tertiary: Option[String])

final case class Availability(from: String, to: String, label: String)