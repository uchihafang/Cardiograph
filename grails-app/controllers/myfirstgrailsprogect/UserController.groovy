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
		boolean err = false
		if(params.user == "" || params.pass1 == "") 
		{
			flash.message = "Empty fild."
			err = true	
		} 
		if(params.pass1 != params.pass2) {
			flash.message = "Passwords are different! Try again."
			err = true
		}
		if(User.findByLogin(params.login)) {
			flash.message = "User already exist."
			err = true
		}
		
		if(!err) {
			def user = new User()
			user.login = params.login
			user.password = params.pass1
			user.name = params.name
			user.save()
			redirect(action:"login")
		} else 
			redirect(action:"register")
	}
  }