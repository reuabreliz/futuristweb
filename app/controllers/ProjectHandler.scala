package controllers

import play.api.data.Form
import play.api.data.Forms.{mapping, longNumber, nonEmptyText}
import play.api.mvc.Flash
import play.api.mvc.{Action, Controller}
import models.Project

object ProjectHandler extends Controller {

  def list() = Action { implicit request =>
    val projects = Project.findAll //get all projects from the list
    Ok(views.html.hello(projects))
  }

  //binding old form data
  def newProject = Action { implicit request =>
    val form = if (flash.get("error").isDefined)
      projectForm.bind(flash.data) //bind the form data from the flash scope to the form
    else
      projectForm 

    Ok(views.html.project(form)) //render the newProject page with the old form data
  }

  //save the project in the list
  def save = Action { implicit request =>
    val newProjectForm = projectForm.bindFromRequest() //get the form data from the request

    newProjectForm.fold(
      hasErrors = { form =>
        Redirect(routes.ProjectHandler.newProject()).flashing(Flash(form.data) + ("error" -> "An error has occurred")) //add form data to the flash scope
      },

      success = { newProject =>
        Project.add(newProject) //add the project to the model

        val projects = Project.findAll //get all projects from the list
        Ok(views.html.hello(projects))
      }
    )
  }

  private val projectForm: Form[Project] = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "githuburl" -> nonEmptyText,
      "user" -> nonEmptyText
    )(Project.apply)(Project.unapply)
  )
}

