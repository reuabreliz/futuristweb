package models

case class Project(
  id: Long, name: String, githuburl: String, user: String)

object Project {

  var projects = Set(
    Project(1, "github-cards", "lepture/github-cards", "lepture"),
    Project(2, "steemit", "steem/steemit", "steem"),
    Project(3, "futurist.enterprises", "reuabreliz/futuristweb", "reuabreliz")
  )
  def findAll = projects.toList.sortBy(_.name)

  def findByName(name: String) = projects.find(_.name == name)

  def add(project: Project) {
    projects = projects + project
  }
}
