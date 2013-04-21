package myfirstgrailsprogect

class UserController {

    def index() { 
		redirect(action:login)
		}

	def scaffold = User
	
	def login = {}
	
	def main = {}
	
	def authenticate = {
	  def user = User.findByLoginAndPassword(params.login, params.password)
	  if(user){
		session.user = user
		flash.message = "Hello ${user.name}!"
		redirect(action:"main")
	  }else{
		flash.message = "Sorry, ${params.login}. Please try again."
		redirect(action:"login")
	  }
	}
	
	def logout = {
	  flash.message = "Goodbye ${session.user.name}"
	  session.user = null
	  redirect(action:"login")
	}
	
	def register = {}
	
	def adduser = {
		if(params.pass1 == params.pass2) {
			def user = new User()
			user.login = params.login
			user.password = params.pass1
			user.name = params.name
			user.save()
			redirect(action:"login")
		} else {
			flash.message = "Passwords are different! Try again."
			redirect(action:"register")
		}
	}
  }