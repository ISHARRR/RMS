case class Titles(primary: String, secondary: String, tertiary: Option[String])
case class Availability(from: String, to: String, label: String)

class Track(`type`: String, id: String, urn: String, titles: Titles, availability: Availability)

