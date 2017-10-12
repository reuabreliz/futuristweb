package models

case class Project(
  id: Long, name: String, githuburl: String, user: String)

object Project {

  var projects = Set(
    Project(1, "d3", "https://github.com/lepture/github-cards", "lepture"),
    Project(2, "steem", "https://github.com/steemit", "steemit")
  )
  def findAll = projects.toList.sortBy(_.name)

  def findByName(name: String) = projects.find(_.name == name)

  def add(project: Project) {
    projects = projects + project
  }
}
