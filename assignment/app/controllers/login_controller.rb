class LoginController < ApplicationController
	  def index
    # @users = User.all

    respond_to do |format|
      format.html # index.html.erb
      format.json { render :json => @users }
    end
  end



  #Shows a user that signs in
  def show 
    @userinfo = params
    @username = params[:username]
    @test = jpt()
    respond_to do |format|
      format.html # index.html.erb
      format.json { render :json => @userinfo }
    end
  end

  def jpt
    @blah = "testing"
    return @blah
  end

  def new
  end

  def create
  end

  def edit
  end

  def update
  end

  def destroy
    @user = User.find(params[:id])
    @user.destroy

    respond_to do |format|
      format.html { redirect_to users_url }
      format.json { head :no_content }
    end
  end
end
end
